package com.agilesolutions.client.controller;

import com.agilesolutions.client.domain.Client;
import com.agilesolutions.client.service.ClientService;
import io.micrometer.observation.annotation.Observed;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Tag(
        name = "CRUD REST APIs to CREATE, READ, UPDATE, DELETE clients",
        description = "CRUD REST APIs for managing clients using JPA"
)
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @Operation(
            summary = "Fetch all clients",
            description = "REST API to fetch all clients from the database"
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
    @GetMapping(produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    @Observed(name = "g",
            contextualName = "fetch-all-clients",
            lowCardinalityKeyValues = {"ClientController", "getAllClients"})
    public Flux<Client> getAllClients() {
        log.info("Fetching all clients");
        return clientService.findAllClients();
    }

}
