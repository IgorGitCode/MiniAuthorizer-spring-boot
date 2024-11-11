package com.security.authorizer.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String cardNumber;
    private String password;
    private BigDecimal balance; //BigDecimal para garantir precisao exata
}
