package com.zebs.facturation.article.common.exception;

import org.springframework.http.HttpStatus;

public class ArticleException extends RuntimeException{
    private final HttpStatus status;

    public  ArticleException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public ArticleException(String message, Throwable cause, HttpStatus status){
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}


