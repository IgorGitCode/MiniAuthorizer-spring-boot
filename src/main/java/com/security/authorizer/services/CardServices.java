package com.security.authorizer.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.authorizer.models.CardModel;
import com.security.authorizer.repository.CardRepository;

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
        return cardRepository.save(cardModel);
    }
}
