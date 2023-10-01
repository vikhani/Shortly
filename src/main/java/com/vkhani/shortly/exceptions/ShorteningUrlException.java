package com.vkhani.shortly.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ShorteningUrlException extends RuntimeException {
    public ShorteningUrlException() {
        super();
    }

    public ShorteningUrlException(String message) {
        super(message);
    }

    public ShorteningUrlException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShorteningUrlException(Throwable cause) {
        super(cause);
    }

    protected ShorteningUrlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
