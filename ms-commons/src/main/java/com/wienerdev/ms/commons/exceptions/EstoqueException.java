package com.wienerdev.ms.commons.exceptions;

public class EstoqueException extends RuntimeException {

    public EstoqueException(final String message) {
        super(message);
    }

    public EstoqueException(final String message, Throwable throwable) {
        super(message, throwable);
    }

    public EstoqueException(Throwable throwable) {
        super(throwable);
    }
}
