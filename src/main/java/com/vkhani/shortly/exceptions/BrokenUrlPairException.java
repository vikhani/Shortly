package com.vkhani.shortly.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Url pair is invalid.")
public class BrokenUrlPairException extends RuntimeException {
    public BrokenUrlPairException() {
    }

    public BrokenUrlPairException(String message) {
        super(message);
    }

    public BrokenUrlPairException(Throwable cause) {
        super(cause);
    }

    public BrokenUrlPairException(String message, Throwable cause) {
        super(message, cause);
    }
}