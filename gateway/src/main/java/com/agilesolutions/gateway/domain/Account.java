package com.agilesolutions.gateway.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Account {
    private String number;
    private Long clientId;
    private String description;
    private String lineOfBusiness;
    private Integer amount;
    private Float openingDayBalance;
    private LocalDateTime maturityDate;

    public OffsetDateTime getMaturityDate() {
        return DateTimeConverter.toOffsetDateTime(this.maturityDate);
    }

}
