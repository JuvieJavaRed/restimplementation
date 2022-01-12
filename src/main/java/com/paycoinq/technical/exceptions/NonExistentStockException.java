package com.paycoinq.technical.exceptions;

public class NonExistentStockException extends RuntimeException{
    public  NonExistentStockException(String message) {
        super(message);
    }
    public  NonExistentStockException(String message, Throwable cause) {
        super(message, cause);
    }
    public  NonExistentStockException(final Throwable cause) {
        super(cause);
    }
}
