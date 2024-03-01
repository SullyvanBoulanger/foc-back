package com.forceofcollection.foc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forceofcollection.foc.model.CardDetailsWithQuantityDTO;
import com.forceofcollection.foc.model.CardPreview;
import com.forceofcollection.foc.model.FilterResponse;
import com.forceofcollection.foc.model.ModifyUserCollectionRequest;
import com.forceofcollection.foc.model.UserCardPreview;
import com.forceofcollection.foc.service.CardService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/card")
public class CardController {

    private final int CARD_BY_PAGE = 18;

    @Autowired
    private CardService cardService;
    
    @GetMapping("/{id}")
    public CardDetailsWithQuantityDTO getCardById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer id) {
        return cardService.getCardDetails(userDetails.getUsername(), id);
    }

    @GetMapping("/my_collection")
    @Transactional
    public Page<UserCardPreview> getMyCollection(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(name = "page", required = true, defaultValue = "0") String page) {
        return cardService.getUserCards(userDetails.getUsername(), PageRequest.of(Integer.parseInt(page), CARD_BY_PAGE));
    }

    @PutMapping("/add_to_collection")
    public Integer addUserCardAmount(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ModifyUserCollectionRequest request) {
        return cardService.addCardToCollection(userDetails.getUsername(), request);
    }
    
    @PutMapping("/remove_to_collection")
    public Integer removeUserCardAmount(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ModifyUserCollectionRequest request) {
        return cardService.removeCardToCollection(userDetails.getUsername(), request);
    }

    @GetMapping("/search")
    public Page<CardPreview> search(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Map<String, String> searchCriterias, @RequestParam(name = "page", required = true, defaultValue = "0") String page) {
        return cardService.searchFilter(userDetails.getUsername(), searchCriterias, PageRequest.of(Integer.parseInt(page), CARD_BY_PAGE));
    }

    @GetMapping("/filters")
    public FilterResponse getFilters() {
        return cardService.getFilters();
    }
    
}
