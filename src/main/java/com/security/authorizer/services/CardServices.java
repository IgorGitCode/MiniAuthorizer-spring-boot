package com.security.authorizer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.authorizer.models.CardModel;
import com.security.authorizer.repository.CardRepository;

@Service
public class CardServices {

    @Autowired
    private CardRepository cardRepository;

    public CardModel getCard(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);
    }

    public CardModel addNewCard(CardModel cardModel) {
        System.out.println("Service");
        //CardModel card = new CardModel();
        // card.setId(40);
        // card.setCardNumber("666");
        // card.setPassword("senha");
        // card.setBalance(400.00);
        return cardRepository.save(cardModel);
    }
}
