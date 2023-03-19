package com.zebs.facturation.commun.exception;

import org.springframework.http.HttpStatus;

public class FileException extends RuntimeException {

    private final HttpStatus status;

    public FileException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
    
    public FileException(String message, Throwable cause, HttpStatus status){
        super(message, cause);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }

}
