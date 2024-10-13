package com.traini8.registry.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handles validation errors from @Valid/@Validated annotations in request bodies
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logger.error("Validation error encountered: {}", ex.getMessage());
        Map<String, List<String>> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Handles validation errors occurring at the individual parameter level
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, List<String>>> handleConstraintViolationException(ConstraintViolationException ex) {
        logger.error("Constraint violation encountered: {}", ex.getMessage());
        Map<String, List<String>> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldPath = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.computeIfAbsent(fieldPath, k -> new ArrayList<>()).add(errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Exception handling in case of incoming request empty in case of batch saving
    @ExceptionHandler(EmptyRequestBodyException.class)
    public ResponseEntity<String> handleEmptyRequestBodyException(EmptyRequestBodyException ex) {
        logger.error("Empty request body: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Handles missing or malformed request bodies
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        logger.error("Request body is missing or unreadable: {}", ex.getMessage());
        String errorMessage = "Request body is required and cannot be empty or malformed.";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    // General Exception Handling
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllUncaughtException(Exception ex, WebRequest request) {
        logger.error("Unhandled exception: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred. Please try again later.");
    }

}
