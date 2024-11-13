package com.security.authorizer.models;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;


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
    //@NotNull
    private String cardNumber;
    //@NotNull
    private String password;
    //@NotNull
    private Double balance; //BigDecimal para garantir precisao exata

    //codigo branch dev/test
}
