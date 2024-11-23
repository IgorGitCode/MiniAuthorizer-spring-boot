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

import com.security.authorizer.models.CardModel;
import com.security.authorizer.repository.CardRepository;
import com.security.authorizer.services.CardServices;

public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardServices cardServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCard_WhenCardExists() {
        String cardNumber = "12345678";
        CardModel mockCard = new CardModel();
        mockCard.setCardNumber(cardNumber);
        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(mockCard));

        CardModel result = cardServices.getCard(cardNumber);

        assertNotNull(result);
        assertEquals(cardNumber, result.getCardNumber());
        verify(cardRepository, times(1)).findByCardNumber(cardNumber);
    }

    @Test
    void testGetCard_WhenCardDoesNotExist() {

        String cardNumber = "987654321";
        when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.empty());

        CardModel result = cardServices.getCard(cardNumber);

        assertNotNull(result);
        assertNull(result.getCardNumber()); // CardModel() retornado possui campos nulos
        verify(cardRepository, times(1)).findByCardNumber(cardNumber);
    }

    @Test
    void testAddNewCard() {

        
        CardModel cardModel = new CardModel();
        cardModel.setCardNumber("12345678");
        cardModel.setBalance(0.0);
        
        when(cardRepository.save(any(CardModel.class))).thenReturn(cardModel);
        
        CardModel result = cardServices.addNewCard(cardModel);
        
        assertEquals(500.00, result.getBalance(), "O saldo do cartão deve ser 500.00");
        
        verify(cardRepository).save(cardModel);
    }
}