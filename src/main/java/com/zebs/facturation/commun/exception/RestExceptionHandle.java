package com.zebs.facturation.commun.exception;

import com.sun.istack.Nullable;
import com.zebs.facturation.projet.common.exception.ProjetException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandle extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ProjetException.class})
    public ResponseEntity<Object> handleRestRequestException(ProjetException ex){
        return this.handleException(ex, HttpStatus.NOT_FOUND, null);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.handleException(ex,status ,"Route introuvable");
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.handleException(ex,status ,null);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentTypeMismatchException ex) {
        String message = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        return this.handleException(ex, HttpStatus.NOT_FOUND, message);
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

    /* others handle */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleOthersException(Exception ex) {
        return this.handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    public ResponseEntity<Object> handleException(Exception ex, HttpStatus status, @Nullable String message) {
        ResponseExceptionEntity restException = new ResponseExceptionEntity(
                ex.getMessage(),
                status,
                status.value(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        if(message != null)
            restException.setMessage(message);

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            restException.setThrowable(ex);
        }
        return  new ResponseEntity<>(restException, status);
    }
}
