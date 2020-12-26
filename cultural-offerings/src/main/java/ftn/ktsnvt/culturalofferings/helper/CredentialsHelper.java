package ftn.ktsnvt.culturalofferings.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CredentialsHelper {

    public String getUserEmailFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = (String) authentication.getPrincipal();

        return userEmail;
    }
}
