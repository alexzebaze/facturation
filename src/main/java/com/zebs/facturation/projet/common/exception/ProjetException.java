package com.zebs.facturation.projet.common.exception;

import org.springframework.http.HttpStatus;

public class ProjetException extends RuntimeException{

    private final HttpStatus status;

    public  ProjetException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public ProjetException(String message, Throwable cause, HttpStatus status){
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}