package com.security.authorizer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.security.authorizer.models.CardModel;

public interface CardRepository extends JpaRepository<CardModel, Long> {
    Optional<CardModel> findByCardNumber(String cardNumber);
    boolean existsByCardNumber(String cardNumber);

    @Modifying
    @Query(value = "UPDATE card_model SET balance = balance - :amount WHERE card_number = :cardNumber AND balance >= :amount", nativeQuery = true)
    int subtractBalance(@Param("cardNumber") String cardNumber, @Param("amount") double amount);
}