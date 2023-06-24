package com.zebs.facturation.commun.exception;

import org.springframework.security.core.AuthenticationException;

public class NullTokenException extends AuthenticationException {
    public NullTokenException(String message) {
        super(message);
    }
}