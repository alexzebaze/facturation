package com.zebs.facturation.commun.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseExceptionEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private ZonedDateTime timestamp;
    private HttpStatus httpStatus;
    private int statusCode;
    private String message;
    private List errors = new ArrayList();

    public ResponseExceptionEntity(String message, HttpStatus httpStatus, int statusCode, ZonedDateTime timestamp, List errors) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.errors = errors;
    }
}