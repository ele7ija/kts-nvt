package ftn.ktsnvt.culturalofferings.dto;

/*
    We can later pass additional information, let it be the JWT for now
 */
public class LoginDTO {
    private String jwt;

    public LoginDTO(){

    }

    public LoginDTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
