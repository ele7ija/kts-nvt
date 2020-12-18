package ftn.ktsnvt.culturalofferings.model.exceptions;

public class SQLDeleteEntityException extends RuntimeException{

    private Class classObject;
    private Class referencedClassObject;

    public SQLDeleteEntityException(Class classObject, Class referencedClassObject) {
        this.classObject = classObject;
        this.referencedClassObject = referencedClassObject;
    }

    public Class getClassObject() {
        return classObject;
    }

    public void setClassObject(Class classObject) {
        this.classObject = classObject;
    }

    public Class getReferencedClassObject() {
        return referencedClassObject;
    }

    public void setReferencedClassObject(Class referencedClassObject) {
        this.referencedClassObject = referencedClassObject;
    }
}
