package com.dansmultipro.vemanis.handler;


import com.dansmultipro.vemanis.dto.ErrorResDTO;
import com.dansmultipro.vemanis.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResDTO<List<String>>> handleMethodArgumentNotValidException (MethodArgumentNotValidException ex){
        var errors =  ex.getBindingResult().getAllErrors().stream().map((ObjectError oe) -> oe.getDefaultMessage()).toList();

        return new ResponseEntity<>(new ErrorResDTO<>(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResDTO<String>> handleNotFound(NotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResDTO<>(ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResDTO<String>> badRequestException(BadRequestException ex){
        return new ResponseEntity<>(new ErrorResDTO<>(ex.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResDTO<String>> duplicateResourceException(DuplicateResourceException ex){
        return new ResponseEntity<>(new ErrorResDTO<>(ex.getMessage()),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotAllowedStateException.class)
    public ResponseEntity<ErrorResDTO<String>> notAllowedStateException (NotAllowedStateException ex){
        return new ResponseEntity<>(new ErrorResDTO<>(ex.getMessage()),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResDTO<String>> HttpRequestMethodNotSupportedException (HttpRequestMethodNotSupportedException ex){
        return new ResponseEntity<>(new ErrorResDTO<>(ex.getMessage()),HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResDTO<String>> DataIntegrityViolationException (){
        return new ResponseEntity<>(new ErrorResDTO<>("Data Integrity Violation Expected"),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResDTO<String>> NoResourceFoundException (NoResourceFoundException ex){
        return new ResponseEntity<>(new ErrorResDTO<>(ex.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResDTO<String>> HttpMessageNotReadableException (){
        return new ResponseEntity<>(new ErrorResDTO<>("Invalid Format Json, Please Check Again"),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ErrorResDTO<String>> ResourceConflictException (ResourceConflictException ex){
        return new ResponseEntity<>(new ErrorResDTO<>(ex.getMessage()),HttpStatus.CONFLICT);
    }
}
