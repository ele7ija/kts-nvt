package ftn.ktsnvt.culturalofferings.model.exceptions;

import org.springframework.http.HttpStatus;

public class Error {
    private String message;

    public Error(){}

    public Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
