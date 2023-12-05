package com.example.webfejlesztes_beadando.car;

public class CarNotFoundException extends Throwable {
    public CarNotFoundException(String message) {
        super(message);
    }
}