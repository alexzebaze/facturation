package com.zebs.facturation.tva.common.exception;

import org.springframework.http.HttpStatus;

public class TvaException extends RuntimeException {

    private final HttpStatus status;

    public  TvaException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public TvaException(String message, Throwable cause, HttpStatus status){
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
