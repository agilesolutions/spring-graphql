package com.agilesolutions.gateway.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Problem {

    private String code;
    private String message;

}