package ftn.ktsnvt.culturalofferings.aop;

import ftn.ktsnvt.culturalofferings.model.exceptions.*;
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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> getError(UserNotFoundException e){
        String message = "User with email " + e.getEmail() + " not found! Are you sure the user is registered?";
        return new ResponseEntity<Error>(new Error(message), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserEmailAlreadyVerifiedException.class)
    public ResponseEntity<Error> getError(UserEmailAlreadyVerifiedException e){
        String message = "User with email " + e.getEmail() + " has already had his account verified.";
        return new ResponseEntity<Error>(new Error(message), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VerificationTokenNotFoundException.class)
    public ResponseEntity<Error> getError(VerificationTokenNotFoundException e){
        String message = "Token " + e.getToken() + " has not been found. Are you sure the user is registered?";
        return new ResponseEntity<Error>(new Error(message), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RequestBodyBindingFailedException.class)
    public ResponseEntity<Error> getError(RequestBodyBindingFailedException e){
        String message = "Request body binding failed for entity " + e.getClassObject().getName()
                + ". Failed field: " + e.getFieldName()
                + ". Message: " + e.getMessage();
        return new ResponseEntity<Error>(new Error(message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ModelConstraintViolationException.class)
    public ResponseEntity<Error> getError(ModelConstraintViolationException e){
        String message = "Model violation for entity " + e.getClassObject().getName()
                + ". Message: " + e.getMessage();
        return new ResponseEntity<Error>(new Error(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundByNameException.class)
    public ResponseEntity<Error> getError(EntityNotFoundByNameException e){
        String message =  "Entity " + e.getClassObject().getName() + " with id " + e.getName() + " not found! Make sure your request is valid!";
        return new ResponseEntity<Error>(new Error(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SQLDeleteEntityException.class)
    public ResponseEntity<Error> getError(SQLDeleteEntityException e){
        String message = "Error deleting entity of class " + e.getClassObject().getName() + " due to existing reference of type " + e.getReferencedClassObject().getName();
        return new ResponseEntity<Error>(new Error(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(PasswordsNotMatchException.class)
    public ResponseEntity<Error> getError(PasswordsNotMatchException e){
    	String message = "Wrong current password. Changing password denied for user with email:" + e.getEmail();
    	return new ResponseEntity<Error>(new Error(message), HttpStatus.BAD_REQUEST);
    }
    
}