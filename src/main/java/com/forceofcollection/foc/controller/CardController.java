package com.forceofcollection.foc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forceofcollection.foc.entity.Card;
import com.forceofcollection.foc.model.CardPreview;
import com.forceofcollection.foc.model.UserCardPreview;
import com.forceofcollection.foc.repository.CardRepository;
import com.forceofcollection.foc.service.CardService;

import jakarta.transaction.Transactional;


@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardService cardService;
    
    @GetMapping("/{id}")
    public Optional<Card> getCardById(@PathVariable Integer id) {
        return cardRepository.findById(id);
    }

    @GetMapping("/my_collection")
    @Transactional
    public List<UserCardPreview> getMyCollection(@AuthenticationPrincipal UserDetails userDetails) {
        return cardService.getUserCards(userDetails.getUsername());
    }
    
}
