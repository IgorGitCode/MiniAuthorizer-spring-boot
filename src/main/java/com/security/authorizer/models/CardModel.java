package com.security.authorizer.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true, nullable = false)
    private String cardNumber;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Double balance; //BigDecimal para garantir precisao exata

    //codigo branch dev/test
}
