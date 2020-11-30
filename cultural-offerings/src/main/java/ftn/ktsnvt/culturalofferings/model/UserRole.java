package ftn.ktsnvt.culturalofferings.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static ftn.ktsnvt.culturalofferings.model.UserPermission.*;

public enum UserRole {
    USER(new HashSet<>(Arrays.asList(
            CULTURAL_OFFERING_READ,
            NEWS_READ, REVIEW_READ,
            REVIEW_WRITE,
            CULTURAL_OFFERING_TYPE_READ))),

    ADMIN(new HashSet<>(Arrays.asList(
            CULTURAL_OFFERING_READ,
            CULTURAL_OFFERING_WRITE,
            USER_READ,
            NEWS_READ,
            NEWS_WRITE,
            REVIEW_READ,
            REVIEW_WRITE,
            CULTURAL_OFFERING_TYPE_READ,
            CULTURAL_OFFERING_TYPE_WRITE))),

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
            CULTURAL_OFFERING_TYPE_WRITE)));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions){
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

}