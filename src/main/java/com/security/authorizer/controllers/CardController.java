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

import com.security.authorizer.dto.RequestTransferDto;
import com.security.authorizer.dto.ResponseTranferDto;
import com.security.authorizer.models.Card;
import com.security.authorizer.services.CardService;

@RestController
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/cartoes/{cardNumber}")
    public ResponseEntity<Card> getCard(@PathVariable String cardNumber) {
        Card card = cardService.getCard(cardNumber);
        if (card.getCardNumber() != null) {
            return ResponseEntity.ok(card);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/cartoes")
    public ResponseEntity<Card> addNewCard(@Valid @RequestBody Card cardModel) {
        try {
            Card newCard = cardService.addNewCard(cardModel);
            URI location = URI.create("/cartoes/" + newCard.getCardNumber());
            return ResponseEntity.created(location).body(newCard);
        } catch(DataIntegrityViolationException e) {
            return ResponseEntity.status(422).body(cardModel);
        }
    }

    @PostMapping("/transacao")
    public ResponseEntity<String> makeTransfer(@RequestBody RequestTransferDto card) {
        ResponseTranferDto response = cardService.makeTransfer(card);
        if (response.sucess()) {
            return ResponseEntity.ok(response.message());
        }
        return ResponseEntity.status(422).body(response.message());
    }
}