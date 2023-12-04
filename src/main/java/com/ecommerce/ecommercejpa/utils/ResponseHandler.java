package com.ecommerce.ecommercejpa.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {   
    
    private ResponseHandler(){}

    public static <T> ResponseEntity<Object> response(T data, HttpStatus status, String message){
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("status", status.value());
        map.put("message", message != null ? message : "");
        return new ResponseEntity<>(map, status);
    }
}