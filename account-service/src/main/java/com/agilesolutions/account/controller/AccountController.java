package com.agilesolutions.account.controller;

import com.agilesolutions.account.domain.Account;
import com.agilesolutions.account.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@Tag(
        name = "CRUD REST APIs to CREATE, READ, UPDATE, DELETE clients",
        description = "CRUD REST APIs for managing clients using JPA"
)
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Operation(
            summary = "Fetch all accounts",
            description = "REST API to fetch all accounts from the database"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    }
    )
    @PostMapping(produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public Flux<Account> getAllAccounts(@RequestBody List<Long> clientIds) {
        log.info("Fetching all accounts");
        return accountService.findAllAccounts(clientIds);
    }

}
