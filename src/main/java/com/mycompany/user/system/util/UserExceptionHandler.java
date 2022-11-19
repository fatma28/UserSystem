package com.mycompany.user.system.util;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mycompany.user.system.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> exception(MethodArgumentNotValidException exception) {
        Response failureResponse = new Response();
        failureResponse.setCode(1004);
        failureResponse.setReason("Bad Request!");
        failureResponse.setMessage(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(failureResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> exception(HttpMessageNotReadableException exception) {
        Response failureResponse = new Response();
        failureResponse.setCode(1004);
        failureResponse.setReason("Bad Request!");
        if (exception.getCause() instanceof InvalidFormatException ||
                exception.getCause() instanceof DateTimeParseException) {
            failureResponse.setMessage("Invalid ExpiryDate format. ExpiryDate format should be yyyy-MM-dd HH:mm:ss!");
        } else {
            failureResponse.setMessage("Bad Request!");
        }
        return new ResponseEntity<>(failureResponse, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Response> getFailureResponse(int errorCode, String message, String reason, HttpStatus httpStatus) {
        Response failureResponse = new Response(errorCode, message, reason);
        return new ResponseEntity<>(failureResponse, httpStatus);
    }
}
