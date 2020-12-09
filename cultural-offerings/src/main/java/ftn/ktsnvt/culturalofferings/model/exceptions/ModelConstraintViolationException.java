package ftn.ktsnvt.culturalofferings.model.exceptions;

public class ModelConstraintViolationException extends RuntimeException{

    private String message;
    private Class classObject;

    public ModelConstraintViolationException(String message, Class classObject) {
        this.message = message;
        this.classObject = classObject;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Class getClassObject() {
        return classObject;
    }

    public void setClassObject(Class classObject) {
        this.classObject = classObject;
    }
}
