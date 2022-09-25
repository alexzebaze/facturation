package com.zebs.facturation.commun.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
public class ResponseExceptionEntity {
    private String message;
    private HttpStatus httpStatus;
    private int statusCode;
    private ZonedDateTime timestamp;
    private Throwable throwable;

    public ResponseExceptionEntity(String message, HttpStatus httpStatus, int statusCode, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
    }

    public ResponseExceptionEntity(String message, HttpStatus httpStatus, int statusCode, ZonedDateTime timestamp, Throwable throwable) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.throwable = throwable;
    }
}
