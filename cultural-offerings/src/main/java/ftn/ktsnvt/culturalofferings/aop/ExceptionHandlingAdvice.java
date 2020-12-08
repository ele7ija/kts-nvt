package ftn.ktsnvt.culturalofferings.aop;

import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.model.exceptions.Error;
import ftn.ktsnvt.culturalofferings.model.exceptions.RequestBodyBindingFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> getError(EntityNotFoundException e){
        String message = "Entity " + e.getClassObject().getName() + " with id " + e.getId() + " not found! Make sure your request is valid!";
        return new ResponseEntity<Error>(new Error(message), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RequestBodyBindingFailedException.class)
    public ResponseEntity<Error> getError(RequestBodyBindingFailedException e){
        String message = "Request body binding failed for entity " + e.getClassObject().getName()
                + ". Failed field: " + e.getFieldName()
                + ". Message: " + e.getMessage();
        return new ResponseEntity<Error>(new Error(message), HttpStatus.NOT_FOUND);
    }
}