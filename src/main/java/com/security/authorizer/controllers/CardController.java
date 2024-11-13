package com.security.authorizer.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<CardModel> addNewCard(@RequestBody CardModel cardModel) {
        CardModel newCard = cardService.addNewCard(cardModel);
        System.out.println("controller");
        return ResponseEntity.ok(newCard);
    }
}