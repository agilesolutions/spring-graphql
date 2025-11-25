package com.agilesolutions.account.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@Builder
public class Account {
    @Id
    private String id;

    @NotBlank(message = "Account.number must be present")
    private String number;

    @NotBlank(message = "Account.description must be present")
    private String description;

    @NotBlank(message = "Account.lineOfBusiness must be present")
    private String lineOfBusiness;

    @NotNull
    @Positive(message = "movieInfo.amount must be a positive")
    private Integer amount;

    @NotNull
    @Positive(message = "movieInfo.openingDayBalance must be a positive")
    private Float openingDayBalance;

    private LocalDate maturityDate;
}