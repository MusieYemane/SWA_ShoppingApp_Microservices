package com.product.productservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class ProductNotfoundException extends RuntimeException {
    private String message;
    public ProductNotfoundException(String message) {
        super(message);
        this.message = message;
    }
    public ProductNotfoundException() {
    }
}