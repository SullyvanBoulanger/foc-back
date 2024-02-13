package com.forceofcollection.foc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forceofcollection.foc.entity.Card;
import com.forceofcollection.foc.repository.CardRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    private CardRepository cardRepository;
    
    @GetMapping("/{id}")
    public Optional<Card> getCardById(@PathVariable Integer id) {
        return cardRepository.findById(id);
    }
}
