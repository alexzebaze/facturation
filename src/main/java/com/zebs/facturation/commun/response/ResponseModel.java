package com.zebs.facturation.commun.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseModel<T> {

    private T data;
    private final HttpStatus status;
    private final String message;

    public ResponseModel(T data, HttpStatus status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }
}