package com.ecommerce.ecommercejpa.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.ValidationException;

@RestControllerAdvice 
public class RestExceptionHandler {

    public Map<String, Object> createResponse(String message, Integer statusCode){
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", statusCode);
        return map;
    }
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleBadRequestException(ResponseStatusException ex) {
        Map<String, Object> response = createResponse(ex.getReason(), ex.getStatusCode().value());
        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex){
        Map<String, Object> response = createResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(KeyAlreadyExistsException.class)
    public ResponseEntity<Object> handleKeyAlreadyExistsException(KeyAlreadyExistsException ex){
        Map<String, Object> response = createResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex){
        Map<String, Object> response = createResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
}