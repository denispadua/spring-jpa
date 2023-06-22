package com.ecommerce.ecommercejpa.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.ValidationException;

@RestControllerAdvice 
public class RestExceptionHandler {

    public Map<String, Object> createResponse(String message, Integer statusCode){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", statusCode);
        return map;
    }
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleBadRequestException(ResponseStatusException ex) {
        Map<String, Object> response = createResponse(ex.getReason(), ex.getStatusCode().value());
        return new ResponseEntity<Object>(response, ex.getStatusCode());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex){
        Map<String, Object> response = createResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }
}