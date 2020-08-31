package com.tydic.jg.portal.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.FatalBeanException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Data
    static class Error{
        private int status;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime timestamp;
        private String message;
        private Error(){
            timestamp = LocalDateTime.now();
        }
        static Error of(int status , String message){
            final Error error = new Error();
            error.status = status;
            error.message = message;
            return error;
        }
    }
    private ResponseEntity<Error> build(HttpStatus status, Throwable throwable){
        return ResponseEntity.status(status).body(Error.of(status.value(), throwable.getLocalizedMessage()));
    }
    @ExceptionHandler(Throwable.class)
    public ResponseEntity handle(Throwable throwable){
        log.error("未知错误", throwable);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, throwable);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> handle(EntityNotFoundException e){
        return build(HttpStatus.NOT_FOUND, e);
    }
    @ExceptionHandler(FatalBeanException.class)
    public ResponseEntity handle(FatalBeanException e){
        final Throwable rootCause = NestedExceptionUtils.getRootCause(e);
        if(rootCause instanceof EntityNotFoundException){
            return handle((EntityNotFoundException)rootCause);
        }
        return handle(rootCause);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity handle(MethodArgumentTypeMismatchException e){
        return build(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity handle(InvalidDataAccessApiUsageException e){
        return build(HttpStatus.BAD_REQUEST, e);
    }

}
