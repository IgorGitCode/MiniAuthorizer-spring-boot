package com.security.authorizer.dto;

public record RequestTransferDto(
    String cardNumber,
    double value,
    String password
) {}