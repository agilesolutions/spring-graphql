package com.agilesolutions.gateway.domain;

public record Client (
        Long id,
        String firstName,
        String middleName,
        String lastName
) {}