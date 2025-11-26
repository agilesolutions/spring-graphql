package com.agilesolutions.gateway.domain;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record Account (
        String number,
        Long clientId,
        String description,
        String lineOfBusiness,
        Integer amount,
        Float openingDayBalance,
        LocalDate maturityDate
) {}
