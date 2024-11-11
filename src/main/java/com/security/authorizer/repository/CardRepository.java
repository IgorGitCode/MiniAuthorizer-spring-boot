package com.security.authorizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.authorizer.models.CardModel;

public interface CardRepository extends JpaRepository<CardModel, Long> {

}