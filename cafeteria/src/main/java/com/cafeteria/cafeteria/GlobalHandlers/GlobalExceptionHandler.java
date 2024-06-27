package com.cafeteria.cafeteria.GlobalHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cafeteria.cafeteria.CustomExceptions.UnauthorizedException;
import com.cafeteria.cafeteria.MyConstants;
import com.cafeteria.cafeteria.CustomExceptions.InternalServerErrorException;
import com.cafeteria.cafeteria.CustomExceptions.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        // You can create a custom error response or use a default one
        String bodyOfResponse = MyConstants.Unauthorized + ex.getMessage();
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        // You can create a custom error response or use a default one
        String bodyOfResponse = MyConstants.NotFound + ex.getMessage();
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> handleInternalServerErrorException(InternalServerErrorException ex, WebRequest request) {
        // You can create a custom error response or use a default one
        String bodyOfResponse = MyConstants.InternalServerError + ex.getMessage();
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        // You can create a custom error response or use a default one
        String bodyOfResponse = MyConstants.InternalServerError + MyConstants.ERROR_MESSAGE_UKNOWN_ERROR;
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}