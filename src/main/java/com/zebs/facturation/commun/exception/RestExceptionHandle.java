package com.zebs.facturation.commun.exception;

import com.sun.istack.Nullable;
import com.zebs.facturation.tva.common.exception.TvaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandle {

    @ExceptionHandler({ AuthenticationException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleRestRequestException(AuthenticationException ex){
        System.out.println("is catch not found");
        return this.handleException(ex, HttpStatus.UNAUTHORIZED, null);
    }

    @ExceptionHandler(value = {TvaException.class})
    public ResponseEntity<Object> handleRestRequestException(TvaException ex){
        return this.handleException(ex, HttpStatus.CREATED, null);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleRestRequestException(AccessDeniedException ex){

        return this.handleException(ex, HttpStatus.CREATED, null);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentTypeMismatchException ex) {
        String message = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        return this.handleException(ex, HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        return this.handleException(ex, HttpStatus.NOT_FOUND, null);
    }

    @ExceptionHandler(TransactionSystemException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(Exception ex, WebRequest request) throws Exception {

        Throwable cause = ((TransactionSystemException) ex).getRootCause();
        if (cause instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
            String error = constraintViolations.stream()
                    .collect(Collectors.toMap(
                            e -> e.getPropertyPath().toString(),
                            e -> e.getMessage())
                    ).toString() ;

            return this.handleException(ex, HttpStatus.BAD_REQUEST, error);
        }

        return this.handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {

        String error = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        e -> e.getPropertyPath().toString(),
                        e -> e.getMessage())
                ).toString() ;

        return this.handleException(ex, HttpStatus.BAD_REQUEST, error);
    }


    public ResponseEntity<Object> handleException(Exception ex, HttpStatus status, @Nullable String message) {
        ResponseExceptionEntity restException = new ResponseExceptionEntity(
                ex.getMessage(),
                status,
                status.value(),
                ZonedDateTime.now(ZoneId.of("Z")),
                new ArrayList()
        );


        return  new ResponseEntity<>(restException, status);
    }
}