package ftn.ktsnvt.culturalofferings.model.exceptions;

public class UserNotFoundException extends RuntimeException {

    private String email;

    public UserNotFoundException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
