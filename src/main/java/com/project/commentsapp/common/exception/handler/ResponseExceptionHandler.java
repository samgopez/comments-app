package com.project.commentsapp.common.exception.handler;

import com.project.commentsapp.common.exception.AbstractException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = AbstractException.class)
    public ResponseEntity<MessageResponse> handleCustomException(AbstractException exception) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(new MessageResponse(exception.getMessage()));
    }
}
