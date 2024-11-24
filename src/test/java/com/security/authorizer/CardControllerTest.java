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
import com.security.authorizer.models.CardModel;
import com.security.authorizer.services.CardServices;

@WebMvcTest(CardController.class)
class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardServices cardService;

    @Test
    void testGetCard_Found() throws Exception {

        when(cardService.getCard(any(String.class))).thenReturn(new CardModel(23, "111", "222", 100.50));

        mockMvc.perform(get("/cartoes/111")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(cardService, times(1)).getCard(any(String.class));
    };

    @Test
    void testGetCard_NotFound() throws Exception {

        when(cardService.getCard(any(String.class))).thenReturn(new CardModel());

        mockMvc.perform(get("/cartoes/111")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());

        verify(cardService, times(1)).getCard(any(String.class));
    }

    @Test
    void testAddNewCard() throws Exception {
        when(cardService.addNewCard(any(CardModel.class))).thenReturn(new CardModel(1, "111", "222", 200.5));


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