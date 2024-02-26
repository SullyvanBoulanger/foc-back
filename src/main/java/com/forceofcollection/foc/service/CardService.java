package com.forceofcollection.foc.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.forceofcollection.foc.entity.Card;
import com.forceofcollection.foc.entity.CardSpecs;
import com.forceofcollection.foc.entity.User;
import com.forceofcollection.foc.entity.UserCard;
import com.forceofcollection.foc.entity.UserCardId;
import com.forceofcollection.foc.model.CardDetailsWithQuantityDTO;
import com.forceofcollection.foc.model.CardPreview;
import com.forceofcollection.foc.model.FilterResponse;
import com.forceofcollection.foc.model.ModifyUserCollectionRequest;
import com.forceofcollection.foc.model.UserCardPreview;
import com.forceofcollection.foc.repository.AttributeRepository;
import com.forceofcollection.foc.repository.CardRepository;
import com.forceofcollection.foc.repository.RaceTraitRepository;
import com.forceofcollection.foc.repository.RarityRepository;
import com.forceofcollection.foc.repository.SetRepository;
import com.forceofcollection.foc.repository.TypeRepository;
import com.forceofcollection.foc.repository.UserCardRepository;
import com.forceofcollection.foc.repository.UserRepository;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCardRepository userCardRepository;

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private RaceTraitRepository raceTraitRepository;

    @Autowired
    private RarityRepository rarityRepository;

    @Autowired
    private SetRepository setRepository;

    @Autowired
    private TypeRepository typeRepository;

    private User findUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private Card findCardById(Integer id){
        return cardRepository.findById(id).orElseThrow(() -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found"));
    }

    public CardDetailsWithQuantityDTO getCardDetails(String username, Integer cardId){
        Card card = findCardById(cardId);
        
        CardDetailsWithQuantityDTO response = new CardDetailsWithQuantityDTO();
        response.setCard(card);
        
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isEmpty()){
            UserCardId userCardId = new UserCardId(user.get().getId(), card.getId());
            Optional<UserCard> userCard = userCardRepository.findById(userCardId);
            response.setQuantity(!userCard.isEmpty() 
                ? userCard.get().getQuantity() 
                : 0
            );
        }

        return response;
    }

    @Transactional
    public List<UserCardPreview> getUserCards(String username){
        User user = findUserByUsername(username);
        
        List<UserCardPreview> response = user.getUserCards().stream()
            .map(userCard -> new UserCardPreview(
                userCard.getCard().getId(), 
                userCard.getCard().getUrl_picture(),
                userCard.getQuantity()
            ))
            .toList();
        return response;
    }

    public Integer addCardToCollection(String username, ModifyUserCollectionRequest request){
        User user = findUserByUsername(username);
        Card card = findCardById(request.getCardId());
        UserCardId id = new UserCardId(user.getId(), card.getId());

        UserCard userCard = userCardRepository.findById(id).orElse(new UserCard(id, user, card, 0));

        userCard.setQuantity(userCard.getQuantity() + request.getNumberToApply());

        userCardRepository.saveAndFlush(userCard);
        return userCard.getQuantity();
    }

    public Integer removeCardToCollection(String username, ModifyUserCollectionRequest request){
        User user = findUserByUsername(username);
        Card card = findCardById(request.getCardId());
        UserCardId id = new UserCardId(user.getId(), card.getId());

        UserCard userCard = userCardRepository.findById(id).orElse(new UserCard(id, user, card, 0));

        userCard.setQuantity(userCard.getQuantity() - request.getNumberToApply());

        if(userCard.getQuantity() <= 0){
            userCardRepository.delete(userCard);
            return 0;
        }

        userCardRepository.saveAndFlush(userCard);
        return userCard.getQuantity();
    }

    public List<CardPreview> searchFilter(String username, Map<String, String> criterias){
        User user = findUserByUsername(username);

        List<Card> cards = cardRepository.findAll(
            CardSpecs.nameLike(criterias.get("name"))
            .and(CardSpecs.atkLike(criterias.get("atk")))
            .and(CardSpecs.defLike(criterias.get("def")))
            .and(CardSpecs.typeLike(criterias.get("type")))
            .and(CardSpecs.setLike(criterias.get("set")))
            .and(CardSpecs.rarityLike(criterias.get("rarity")))
            .and(CardSpecs.attributesLike(criterias.get("attributes")))
            .and(CardSpecs.raceTraitLike(criterias.get("raceTraits")))
        );
        
        if(criterias.get("collection") != null && criterias.get("collection").equalsIgnoreCase("user")){
            List<Card> useCards = userCardRepository.findByUser_Id(user.getId())
                .stream().map(userCard -> userCard.getCard()).toList();
            cards = cards.stream().filter(card -> useCards.contains(card)).toList();
        }

        List<CardPreview> cardPreviews = cards.stream().map(card -> 
            new CardPreview(card.getId(), card.getUrl_picture())
        ).toList();

        return cardPreviews;
    }

    public FilterResponse getFilters(){
        return new FilterResponse(
            typeRepository.findAll(),
            attributeRepository.findAll(), 
            raceTraitRepository.findAll(), 
            setRepository.findAll(), 
            rarityRepository.findAll()
        );
    }
}
