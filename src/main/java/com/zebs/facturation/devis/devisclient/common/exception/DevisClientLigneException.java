package com.zebs.facturation.devis.devisclient.common.exception;

import org.springframework.http.HttpStatus;

public class DevisClientLigneException extends RuntimeException{
    private final HttpStatus status;

    public  DevisClientLigneException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public DevisClientLigneException(String message, Throwable cause, HttpStatus status){
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
