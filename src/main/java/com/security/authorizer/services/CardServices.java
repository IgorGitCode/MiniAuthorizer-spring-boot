package com.security.authorizer.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.authorizer.dto.RequestTransferDto;
import com.security.authorizer.dto.ResponseTranferDto;

import jakarta.transaction.Transactional;

import com.security.authorizer.models.CardModel;
import com.security.authorizer.repository.CardRepository;

@Service
public class CardServices {

    @Autowired
    private CardRepository cardRepository;

    public CardModel getCard(String cardNumber) {
            Optional<CardModel> cardSaved = cardRepository.findByCardNumber(cardNumber);
            return cardSaved.orElse(new CardModel());
    }

    public CardModel addNewCard(CardModel cardModel) {
        cardModel.setBalance(500.00);
        return cardRepository.save(cardModel);
    }

    @Transactional
    public ResponseTranferDto makeTransfer(RequestTransferDto card) {
        if (!verifyExistCard(card.cardNumber())) {
            return new ResponseTranferDto(false, "Cartao inexistente!");
        }

        if (!verifyBalance(card.cardNumber(), card.value())) {
            return new ResponseTranferDto(false, "saldo insuficiente!");
        }

        if (!verifyPassword(card.cardNumber(), card.password())) {
            return new ResponseTranferDto(false, "Senha incorreta!");
        }

        cardRepository.subtractBalance(card.cardNumber(), card.value());
        return new ResponseTranferDto(true, "transferencia realizada!");
    }

    private boolean verifyExistCard(String cardNumber) {
        return cardRepository.existsByCardNumber(cardNumber);
    }

    private boolean verifyBalance(String cardNumber, double valor) {
        Optional<CardModel> card = cardRepository.findByCardNumber(cardNumber);
        return card.map(c -> c.getBalance() >= valor).orElse(false);        
    }
    

    private boolean verifyPassword(String cardNumber, String password) {
        Optional<CardModel> card = cardRepository.findByCardNumber(cardNumber);
        return card.map(c -> c.getPassword().equals(password)).orElse(false);
    }
};