package com.transferz.exception;

import lombok.val;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        var errors = ex.getAllErrors().stream()
                .map( error -> getFieldValue(error) + " " + error.getDefaultMessage()).collect(Collectors.toList());
        return new ResponseEntity<>(
                    errors,
                    headers, status
                );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(RuntimeException ex) {
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleIncorrectClientInput(RuntimeException ex) {
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }


    private String getFieldValue(ObjectError objectError) {
       val fieldVal = ReflectionUtils.getFieldVal(false, objectError, "field");
       if (fieldVal == null) {
           return "";
       } else {
           return fieldVal.toString();
       }
    }
}
