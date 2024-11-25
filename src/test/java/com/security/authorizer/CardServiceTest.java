package com.security.authorizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

<<<<<<< HEAD
import com.security.authorizer.models.CardModel;
import com.security.authorizer.repository.CardRepository;
import com.security.authorizer.services.CardServices;
=======
import com.security.authorizer.models.Card;
import com.security.authorizer.repository.CardRepository;
import com.security.authorizer.services.CardService;
>>>>>>> dev/test

public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
<<<<<<< HEAD
    private CardServices cardServices;
=======
    private CardService cardServices;
>>>>>>> dev/test

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCard_WhenCardExists() {
        String cardNumber = "12345678";
<<<<<<< HEAD
        CardModel mockCard = new CardModel();
        mockCard.setCardNumber(cardNumber);
        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(mockCard));

        CardModel result = cardServices.getCard(cardNumber);
=======
        Card mockCard = new Card();
        mockCard.setCardNumber(cardNumber);
        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(mockCard));

        Card result = cardServices.getCard(cardNumber);
>>>>>>> dev/test

        assertNotNull(result);
        assertEquals(cardNumber, result.getCardNumber());
        verify(cardRepository, times(1)).findByCardNumber(cardNumber);
    }

    @Test
    void testGetCard_WhenCardDoesNotExist() {

        String cardNumber = "987654321";
        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.empty());

<<<<<<< HEAD
        CardModel result = cardServices.getCard(cardNumber);
=======
        Card result = cardServices.getCard(cardNumber);
>>>>>>> dev/test

        assertNotNull(result);
        assertNull(result.getCardNumber()); // CardModel() retornado possui campos nulos
        verify(cardRepository, times(1)).findByCardNumber(cardNumber);
    }

    @Test
    void testAddNewCard() {

        
<<<<<<< HEAD
        CardModel cardModel = new CardModel();
        cardModel.setCardNumber("12345678");
        cardModel.setBalance(0.0);
        
        when(cardRepository.save(any(CardModel.class))).thenReturn(cardModel);
        
        CardModel result = cardServices.addNewCard(cardModel);
=======
        Card cardModel = new Card();
        cardModel.setCardNumber("12345678");
        cardModel.setBalance(0.0);
        
        when(cardRepository.save(any(Card.class))).thenReturn(cardModel);
        
        Card result = cardServices.addNewCard(cardModel);
>>>>>>> dev/test
        
        assertEquals(500.00, result.getBalance(), "O saldo do cartão deve ser 500.00");
        
        verify(cardRepository).save(cardModel);
    }
}