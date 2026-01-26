package com.dansmultipro.vemanis.handler;


import com.dansmultipro.vemanis.dto.ErrorResDTO;
import com.dansmultipro.vemanis.exception.BadRequestException;
import com.dansmultipro.vemanis.exception.DuplicateResourceException;
import com.dansmultipro.vemanis.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}
