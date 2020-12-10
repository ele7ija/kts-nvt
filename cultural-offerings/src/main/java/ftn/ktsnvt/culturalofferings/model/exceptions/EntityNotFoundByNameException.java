package ftn.ktsnvt.culturalofferings.model.exceptions;

public class EntityNotFoundByNameException extends RuntimeException {
    private String name;
    private Class classObject;

    public EntityNotFoundByNameException(String name, Class classObject) {
        this.name = name;
        this.classObject = classObject;
    }

    

    public Class getClassObject() {
        return classObject;
    }

    public void setClassObject(Class classObject) {
        this.classObject = classObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
