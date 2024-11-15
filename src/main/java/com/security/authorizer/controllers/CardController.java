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

    @GetMapping("cartoes/{numeroCartao}")
    public ResponseEntity<CardModel> getCard(@PathVariable String cardNumber) {
        CardModel card = cardService.getCard(cardNumber);
        return ResponseEntity.ok(card);
    }

    @PostMapping("/cartoes")
    public ResponseEntity<CardModel> addNewCard(@Valid @RequestBody CardModel cardModel) {
        try {
            CardModel newCard = cardService.addNewCard(cardModel);
            URI location = URI.create("/cartoes/" + newCard.getCardNumber());
            return ResponseEntity.created(location).body(newCard);
        } catch(DataIntegrityViolationException e) {
            return ResponseEntity.status(422).body(cardModel);
        }
    }
}