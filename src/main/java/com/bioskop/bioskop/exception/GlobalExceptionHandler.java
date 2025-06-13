package com.bioskop.bioskop.exception; // atau controller, sesuaikan

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace(); // untuk log error di console
        return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
    }
}
