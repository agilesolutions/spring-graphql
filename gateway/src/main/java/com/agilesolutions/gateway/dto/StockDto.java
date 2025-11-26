package com.agilesolutions.gateway.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

@Builder
public record StockDto(
        @PositiveOrZero(message = "Price must be zero or positive")
        Float price) {
}