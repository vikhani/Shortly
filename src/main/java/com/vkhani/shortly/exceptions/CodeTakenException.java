package com.vkhani.shortly.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CodeTakenException extends RuntimeException{
    public CodeTakenException() {
        super();
    }

    public CodeTakenException(String message) {
        super(message);
    }

    public CodeTakenException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeTakenException(Throwable cause) {
        super(cause);
    }
}
