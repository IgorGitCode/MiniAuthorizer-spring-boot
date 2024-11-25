package com.security.authorizer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.security.authorizer.models.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByCardNumber(String cardNumber);
    boolean existsByCardNumber(String cardNumber);

    @Modifying //@Query makes the change directly on the DB, preventing race conditions!
    @Query(value = "UPDATE card_model SET balance = balance - :amount WHERE card_number = :cardNumber AND balance >= :amount", nativeQuery = true)
    int subtractBalance(@Param("cardNumber") String cardNumber, @Param("amount") double amount);
}