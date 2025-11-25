package com.agilesolutions.gateway.domain;

import java.time.LocalDate;

public record Account (
        String number,
        String description,
        String lineOfBusiness,
        Integer amount,
        Float openingDayBalance,
        LocalDate maturityDate
) {}
