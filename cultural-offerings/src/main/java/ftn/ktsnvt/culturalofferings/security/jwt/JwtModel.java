package ftn.ktsnvt.culturalofferings.security.jwt;

import ftn.ktsnvt.culturalofferings.model.UserRole;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public class JwtModel {
    private String email;
    private UserRole userRole;
    private Set<GrantedAuthority> grantedAuthorityList;

    public JwtModel(){}

    public JwtModel(String email, UserRole userRole, Set<GrantedAuthority> grantedAuthorityList) {
        this.email = email;
        this.userRole = userRole;
        this.grantedAuthorityList = grantedAuthorityList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Set<GrantedAuthority> getGrantedAuthorityList() {
        return grantedAuthorityList;
    }

    public void setGrantedAuthorityList(Set<GrantedAuthority> grantedAuthorityList) {
        this.grantedAuthorityList = grantedAuthorityList;
    }
}
