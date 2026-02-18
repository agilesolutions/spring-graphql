package com.agilesolutions.account.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Account (
        @NotEmpty(message = "Account ID can not be a null or empty")
        @Schema(
                description = "Unique id of account", example = "1"
        )
        String id,
        @NotNull(message = "clientId can not be null")
        @Schema(
                description = "Client ID of account", example = "1"
        )
        Long clientId,
        @NotEmpty(message = "number can not be a null or empty")
        @Schema(
                description = "Number of account", example = "AMS"
        )
        String number,
        @NotEmpty(message = "description can not be a null or empty")
        @Schema(
                description = "Description of account", example = "Account for business"
        )
        String description,
        @NotEmpty(message = "lineOfBusiness can not be a null or empty")
        @Schema(
                description = "LineOfBusiness of account", example = "BUSINESS"
        )
        String lineOfBusiness,
        @NotNull(message = "Amount can not be null")
        @Schema(
                description = "Amount of account", example = "1000"
        )
        Integer amount,
        @NotNull(message = "OpeningDayBalance can not be null")
        @Schema(
                description = "OpeningDayBalance of account", example = "5000.50"
        )
        Float openingDayBalance,
        @Schema(
                description = "MaturityDate of account", example = "2024-12-31"
        )
        LocalDateTime maturityDate
) {}
