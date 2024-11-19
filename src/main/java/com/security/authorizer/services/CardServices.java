package com.security.authorizer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.authorizer.dto.RequestTransferDto;
import com.security.authorizer.models.CardModel;
import com.security.authorizer.repository.CardRepository;

import jakarta.transaction.Transactional;

@Service
public class CardServices {

    @Autowired
    private CardRepository cardRepository;

    public CardModel getCard(String cardNumber) {
            CardModel cardSaved = cardRepository.findByCardNumber(cardNumber);
            if (cardSaved != null) {
                return cardSaved;
            }
            return new CardModel();
    }

    public CardModel addNewCard(CardModel cardModel) {
        cardModel.setBalance(500.00);
        return cardRepository.save(cardModel);
    }

    @Transactional
    public String makeTransfer(RequestTransferDto card) {
        if (!verifyExistCard(card.cardNumber())) {
            return "Cartao inexistente!";
        }

        if (!verifyBalance(card.cardNumber(), card.value())) {
            return "saldo insuficientte";
        }

        if (!verifyPassword(card.cardNumber(), card.password())) {
            return "Senha incorreta";
        }

        cardRepository.subtractBalance(card.cardNumber(), card.value());
        return "transferencia realizada";
    }

    private boolean verifyExistCard(String cardNumber) {
        return cardRepository.existsByCardNumber(cardNumber);
    }

    private boolean verifyBalance(String cardNumber, double valor) {
        CardModel card = cardRepository.findByCardNumber(cardNumber);
        if (card == null) {
            return false;
        }

        return card.getBalance() >= valor;
    }
    

    private boolean verifyPassword(String cardNumber, String password) {
        CardModel card = cardRepository.findByCardNumber(cardNumber);
        return card.getPassword().equals(password);
    }
};