package com.example.oryeqrzh.exceptions;

public class CarNotFoundException extends Exception {
    public CarNotFoundException() {
    }
    public CarNotFoundException(String message) {
        super(message);
    }
    public CarNotFoundException(String message, Throwable cause) {
        super(message,cause);
    }
}
