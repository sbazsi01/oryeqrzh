package com.example.oryeqrzh.exceptions;

public class CarAlreadyExistException extends Exception {
    public CarAlreadyExistException() {
    }
    public CarAlreadyExistException(String message) {
        super(message);
    }
    public CarAlreadyExistException(String message, Throwable cause) {
        super(message,cause);
    }
}
