package com.security.authorizer.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.authorizer.models.CardModel;
import com.security.authorizer.services.CardServices;

@RestController
public class CardController {

    @Autowired
    private CardServices cardService;

    @GetMapping("cartoes/{cardNumber}")
    public ResponseEntity<CardModel> getCard(@PathVariable String cardNumber) {
        System.out.println("get");
        CardModel card = cardService.getCard(cardNumber);
        if (card.getCardNumber() != null) {
            return ResponseEntity.ok(card);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/cartoes")
    public ResponseEntity<CardModel> addNewCard(@Valid @RequestBody CardModel cardModel) {
        System.out.println("post");
        try {
            CardModel newCard = cardService.addNewCard(cardModel);
            URI location = URI.create("/cartoes/" + newCard.getCardNumber());
            return ResponseEntity.created(location).body(newCard);
        } catch(DataIntegrityViolationException e) {
            return ResponseEntity.status(422).body(cardModel);
        }
    }
}