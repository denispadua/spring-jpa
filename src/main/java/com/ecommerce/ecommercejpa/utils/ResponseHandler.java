package com.ecommerce.ecommercejpa.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    
    public static <T> ResponseEntity<Object> response(List<T> lsData, HttpStatus status){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", lsData);
        map.put("status", status.value());
        return new ResponseEntity<Object>(map, status);
    }

    public static <T> ResponseEntity<Object> response(T data, HttpStatus status){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", data);
        map.put("status", status.value());
        return new ResponseEntity<Object>(map, status);
    }

    public static <T> ResponseEntity<Object> response(String data, HttpStatus status){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", data);
        map.put("status", status.value());
        return new ResponseEntity<Object>(map, status);
    }
}