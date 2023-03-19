package com.zebs.facturation.personne.client.common.exception;

import org.springframework.http.HttpStatus;

public class ClientException extends RuntimeException{

    private final HttpStatus status;

    public  ClientException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public ClientException(String message, Throwable cause, HttpStatus status){
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}