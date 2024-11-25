package com.security.authorizer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.security.authorizer.controllers.CardController;
import com.security.authorizer.dto.RequestTransferDto;
import com.security.authorizer.dto.ResponseTranferDto;
<<<<<<< HEAD
import com.security.authorizer.models.CardModel;
import com.security.authorizer.services.CardServices;
=======
import com.security.authorizer.models.Card;
import com.security.authorizer.services.CardService;
>>>>>>> dev/test

@WebMvcTest(CardController.class)
class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
<<<<<<< HEAD
    private CardServices cardService;
=======
    private CardService cardService;
>>>>>>> dev/test

    @Test
    void testGetCard_Found() throws Exception {

<<<<<<< HEAD
        when(cardService.getCard(any(String.class))).thenReturn(new CardModel(23, "111", "222", 100.50));
=======
        when(cardService.getCard(any(String.class))).thenReturn(new Card(23, "111", "222", 100.50));
>>>>>>> dev/test

        mockMvc.perform(get("/cartoes/111")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(cardService, times(1)).getCard(any(String.class));
    };

    @Test
    void testGetCard_NotFound() throws Exception {

<<<<<<< HEAD
        when(cardService.getCard(any(String.class))).thenReturn(new CardModel());
=======
        when(cardService.getCard(any(String.class))).thenReturn(new Card());
>>>>>>> dev/test

        mockMvc.perform(get("/cartoes/111")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());

        verify(cardService, times(1)).getCard(any(String.class));
    }

    @Test
    void testAddNewCard() throws Exception {
<<<<<<< HEAD
        when(cardService.addNewCard(any(CardModel.class))).thenReturn(new CardModel(1, "111", "222", 200.5));
=======
        when(cardService.addNewCard(any(Card.class))).thenReturn(new Card(1, "111", "222", 200.5));
>>>>>>> dev/test


        String cardJson = """
            {
                "cardNumber": "1234567890123456",
                "password": "1234",
                "balance": 100.0
            }
            """;

        mockMvc.perform(post("/cartoes")
        .content(cardJson)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
    }

    @Test
    void testMakeTransfer() throws Exception {
        when(cardService.makeTransfer(any(RequestTransferDto.class))).thenReturn(new ResponseTranferDto(true, null));
    }
}