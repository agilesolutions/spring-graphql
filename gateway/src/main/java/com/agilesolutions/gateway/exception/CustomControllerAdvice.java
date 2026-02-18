package com.agilesolutions.gateway.exception;

import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import lombok.NonNull;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomControllerAdvice extends ResponseStatusExceptionHandler {

    Map<String, Object> extMap = new HashMap<>();

    @GraphQlExceptionHandler
    public GraphQLError handleClientExceptions(@NonNull ClientNotFoundException ex, @NonNull DataFetchingEnvironment environment) {
        extMap.put("errorCode", "CLIENT_NOT_FOUND");
        extMap.put("userMessage", "The client you are trying to access does not exist.");
        extMap.put("timestamp", Instant.now().toString());
        extMap.put("actionableSteps", "Please verify the account ID and try again.");

        return GraphQLError
                .newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message("Sorry, we couldn't find the requested client. Please check the client ID and try again : " + ex.getMessage())
                .path(environment.getExecutionStepInfo().getPath())
                .location(environment.getField().getSourceLocation())
                .extensions(extMap)
                .build();
    }


    @ExceptionHandler(Throwable.class)
    public ErrorResponse toProblemDetail(Throwable throwable) {

        return ErrorResponse.builder(throwable, HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage())
                .title(throwable.getMessage())
                .type(URI.create("https://mycomp.com/whatever.html"))
                .detail(throwable.getMessage())
                .property("errorCategory", "Generic")
                .property("timestamp", Instant.now())
                .build();

    }

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("business validation exception");
        problemDetail.setType(URI.create("https://mycomp.com/whatever.html"));
        ex.getProblems().stream().forEach(pr -> problemDetail.setProperty(pr.getCode(), pr.getMessage()));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Mono<ResponseEntity<ProblemDetail>> handleBadRequestException(MethodArgumentNotValidException ex, ServerWebExchange exchange) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("bean validation error");
        problemDetail.setType(URI.create("https://mycomp.com/whatever.html"));
        ex.getBindingResult().getFieldErrors().stream().forEach(fe -> problemDetail.setProperty(fe.getField(), fe.getDefaultMessage()));
        problemDetail.setProperty("timestamp", Instant.now());

        return Mono.just(ResponseEntity.status(problemDetail.getStatus()).body(problemDetail));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Mono<ResponseEntity<ProblemDetail>> handleHttpMessageNotReadable(MethodArgumentNotValidException ex, ServerWebExchange exchange) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("jackson de-serialization error");
        problemDetail.setType(URI.create("https://mycomp.com/whatever.html"));
        problemDetail.setProperty("details", ex.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());

        return Mono.just(ResponseEntity.status(problemDetail.getStatus()).body(problemDetail));
    }

}
