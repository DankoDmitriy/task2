package com.danko.greenhouse.exception;

public class FlowerException extends Exception {
    public FlowerException() {
        super();
    }

    public FlowerException(String message) {
        super(message);
    }

    public FlowerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowerException(Throwable cause) {
        super(cause);
    }
}
