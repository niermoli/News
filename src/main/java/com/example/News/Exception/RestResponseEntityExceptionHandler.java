package com.example.News.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException c, WebRequest request) {
        List<String> errors = new ArrayList<String>();

        for(ConstraintViolation<?> v : c.getConstraintViolations()) {
            errors.add(v.getRootBeanClass().getName() + " " +
                    v.getPropertyPath() + ": " + v.getMessage());
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, c.getLocalizedMessage(), errors);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception x, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        errors.add(request.getDescription(false) + " , message = " + x.getMessage());
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, x.getLocalizedMessage(), errors);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
