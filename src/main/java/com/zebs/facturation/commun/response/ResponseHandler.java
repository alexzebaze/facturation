package com.zebs.facturation.commun.response;

import com.sun.istack.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    public static <T> ResponseEntity<ResponseModel<T>> generateResponse(T dataResponse, HttpStatus status, String message){

        ResponseModel<T> response = new ResponseModel<>(dataResponse, status, message);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}