package com.scorsaro.hellorc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DictionaryApiException extends ResponseStatusException {

    public DictionaryApiException(String message, HttpStatus status) {
        super(status, message);
    }

    public DictionaryApiException(String message, HttpStatus status, Throwable cause) {
        super(status, message, cause);
    }
}
