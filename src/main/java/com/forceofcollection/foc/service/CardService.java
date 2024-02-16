package com.forceofcollection.foc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.forceofcollection.foc.entity.User;
import com.forceofcollection.foc.model.CardPreview;
import com.forceofcollection.foc.repository.UserRepository;

@Service
public class CardService {
    
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<CardPreview> getUserCards(String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        
        List<CardPreview> response = user.getUserCards().stream()
            .map(userCard -> new CardPreview(
                userCard.getCard().getId(), 
                userCard.getCard().getUrl_picture()
            ))
            .toList();
        return response;
    }
}
