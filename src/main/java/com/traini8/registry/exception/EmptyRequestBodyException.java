package com.traini8.registry.exception;

public class EmptyRequestBodyException extends RuntimeException {

    public EmptyRequestBodyException() {
        super();
    }

    public EmptyRequestBodyException(String message) {
        super(message);
    }

}
