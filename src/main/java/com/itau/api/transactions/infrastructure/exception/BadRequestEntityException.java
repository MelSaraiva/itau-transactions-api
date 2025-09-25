package com.itau.api.transactions.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestEntityException extends RuntimeException {

    public BadRequestEntityException(String message) {
        super(message);
    }
}
