package com.security.authorizer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.authorizer.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}