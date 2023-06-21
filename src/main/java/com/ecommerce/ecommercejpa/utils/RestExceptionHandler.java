package com.ecommerce.ecommercejpa.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice 
public class RestExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleBadRequestException(ResponseStatusException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", ex.getReason());
        map.put("status", ex.getStatusCode().value());
        return new ResponseEntity<Object>(map, ex.getStatusCode());
    }
}