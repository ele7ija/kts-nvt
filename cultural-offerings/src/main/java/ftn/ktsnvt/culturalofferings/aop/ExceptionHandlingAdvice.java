package ftn.ktsnvt.culturalofferings.aop;

import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.model.exceptions.Error;
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
}
