package ftn.ktsnvt.culturalofferings.model.exceptions;

public class RequestBodyBindingFailedException extends RuntimeException{

    private String fieldName;
    private String message;
    private Class classObject;

    public RequestBodyBindingFailedException(){}

    public RequestBodyBindingFailedException(String fieldName, String message, Class classObject) {
        this.message = message;
        this.fieldName = fieldName;
        this.classObject = classObject;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

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
