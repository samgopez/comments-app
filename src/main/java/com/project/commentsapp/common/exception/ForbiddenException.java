package com.project.commentsapp.common.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends AbstractException {
    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
