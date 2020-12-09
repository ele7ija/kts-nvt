package ftn.ktsnvt.culturalofferings.model.exceptions;

public class VerificationTokenNotFoundException extends RuntimeException{

    private String token;

    public VerificationTokenNotFoundException(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
