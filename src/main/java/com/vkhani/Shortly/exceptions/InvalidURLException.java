package com.vkhani.Shortly.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid URL passed.")
public class InvalidURLException extends RuntimeException {
    public InvalidURLException() {
    }

    public InvalidURLException(String message) {
        super(message);
    }

    public InvalidURLException(Throwable cause) {
        super(cause);
    }

    public InvalidURLException(String message, Throwable cause) {
        super(message, cause);
    }
}
