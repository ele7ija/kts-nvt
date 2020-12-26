package ftn.ktsnvt.culturalofferings.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static ftn.ktsnvt.culturalofferings.model.UserPermission.*;

public enum UserRole {
    USER(new HashSet<>(Arrays.asList(
            CULTURAL_OFFERING_READ,
            NEWS_READ, REVIEW_READ,
            REVIEW_WRITE,
            CULTURAL_OFFERING_TYPE_READ,
            CULTURAL_OFFERING_SUB_TYPE_READ,
            SUBSCRIPTION_READ,
            SUBSCRIPTION_WRITE))),

    ADMIN(new HashSet<>(Arrays.asList(
            CULTURAL_OFFERING_READ,
            CULTURAL_OFFERING_WRITE,
            USER_READ,
            NEWS_READ,
            NEWS_WRITE,
            REVIEW_READ,
            REVIEW_WRITE,
            CULTURAL_OFFERING_TYPE_READ,
            CULTURAL_OFFERING_TYPE_WRITE,
            CULTURAL_OFFERING_SUB_TYPE_READ,
            CULTURAL_OFFERING_SUB_TYPE_WRITE,
            SUBSCRIPTION_READ,
            SUBSCRIPTION_WRITE))),

    SUPER_ADMIN(new HashSet<>(Arrays.asList(
            CULTURAL_OFFERING_READ,
            CULTURAL_OFFERING_WRITE,
            USER_READ,
            USER_WRITE,
            NEWS_READ,
            NEWS_WRITE,
            REVIEW_READ,
            REVIEW_WRITE,
            CULTURAL_OFFERING_TYPE_READ,
            CULTURAL_OFFERING_TYPE_WRITE,
            CULTURAL_OFFERING_SUB_TYPE_READ,
            CULTURAL_OFFERING_SUB_TYPE_WRITE,
            SUBSCRIPTION_READ,
            SUBSCRIPTION_WRITE)));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions){
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

}
