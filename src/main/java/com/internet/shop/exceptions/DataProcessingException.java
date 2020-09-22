package com.internet.shop.exceptions;

public class DataProcessingException extends RuntimeException {
    private Throwable ex;
    private String message;

    public DataProcessingException(String message) {
        super(message);

    }

    public DataProcessingException(String message, Throwable ex) {
        super(message, ex);
    }
}
