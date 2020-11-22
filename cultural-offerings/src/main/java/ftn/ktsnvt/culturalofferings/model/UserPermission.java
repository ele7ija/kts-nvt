package ftn.ktsnvt.culturalofferings.model;

/*
NOTE:
    This set of user permissions in not final and will definitely change as the system evolves
    This is just a rough sketch for now
 */
public enum UserPermission {
    CULTURAL_OFFERING_READ("CULTURAL_OFFERING:read"),
    CULTURAL_OFFERING_WRITE("CULTURAL_OFFERING:write"),
    USER_READ("USER:read"),
    USER_WRITE("USER:write"),
    NEWS_READ("NEWS:read"),
    NEWS_WRITE("NEWS:write"),
    REVIEW_READ("REVIEW:read"),
    REVIEW_WRITE("REVIEW:write"),
    CULTURAL_OFFERING_TYPE_READ("CULTURAL_OFFERING_TYPE:read"),
    CULTURAL_OFFERING_TYPE_WRITE("CULTURAL_OFFERING_TYPE:write");

    private final String permission;

    UserPermission(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
