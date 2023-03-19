package com.zebs.facturation.devis.devisclient.common.exception;

import org.springframework.http.HttpStatus;

public class DevisClientException extends RuntimeException{
    private final HttpStatus status;

    public  DevisClientException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public DevisClientException(String message, Throwable cause, HttpStatus status){
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
