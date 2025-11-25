package com.agilesolutions.client.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record Client (
        @NotEmpty(message = "Client ID can not be a null or empty")
        @Schema(
                description = "Unique id of client", example = "1"
        )
        Long id,
        @NotEmpty(message = "firstName can not be a null or empty")
        @Schema(
                description = "First name of client", example = "Rob"
        )
        String firstName,
        @Schema(
                description = "Middle name of client", example = ""
        )
        String middleName,
        @NotEmpty(message = "lastName can not be a null or empty")
        @Schema(
                description = "Last name of client", example = "Rong"
        )
        String lastName
) {}