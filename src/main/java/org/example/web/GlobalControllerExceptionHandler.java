package org.example.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * will make sure that Spring will apply whatever this class contains to all of your <br /> @Controllers or @RestControllers it knows
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handlemethodArgumentNotValid(MethodArgumentNotValidException exception){
        //TODO you can choose to return your custom object here, which will then get transformed to json 
        return "Sorry, that was not quite right: " + exception.getMessage(); 
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolation(ConstraintViolationException exception) { // 
        // TODO you can choose to return your custom object here, which will then get transformed to json/xml etc.
        return "Sorry, that was not quite right: " + exception.getMessage();
    }
    
}
