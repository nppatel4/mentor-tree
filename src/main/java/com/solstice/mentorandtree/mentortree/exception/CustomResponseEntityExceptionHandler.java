package com.solstice.mentorandtree.mentortree.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleUncaughtExceptions(Exception e, WebRequest request) {
        return new ResponseEntity<>(getExceptionResponse(e, request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException nfe, WebRequest request) {
        return new ResponseEntity<>(getExceptionResponse(nfe, request), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestException(
            BadRequestException e, WebRequest request) {
        return new ResponseEntity<>(getExceptionResponse(e, request), HttpStatus.BAD_REQUEST);
    }

    private ExceptionResponse getExceptionResponse(Exception e, WebRequest request) {
        return new ExceptionResponse(
                LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false));
    }

}
