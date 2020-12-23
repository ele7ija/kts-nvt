package ftn.ktsnvt.culturalofferings.integration.user;

import ftn.ktsnvt.culturalofferings.model.UserRole;

public class UserConstants {

    public static final int LIST_SIZE = 4;
    public static final int PAGE_NUMBER = 0;
    public static final int PAGE_SIZE = 2;

    public static final long EXISTING_ENTITY_ID = 1l;
    public static final String EXISTING_ENTITY_EMAIL = "mika.mika@gmail.com";
    public static final String EXISTING_ENTITY_PASSWORD = "Mika";
    public static final String EXISTING_ENTITY_FIRST_NAME = "Mika";
    public static final String EXISTING_ENTITY_LAST_NAME = "Mika";
    public static final UserRole EXISTING_ENTITY_USER_ROLE = UserRole.USER;

    public static final String NON_EXISTENT_ENTITY_EMAIL = "this email does not exist";

    public static final String NEW_ENTITY_TYPE_NAME = "Institucija 2";

    public static final long NON_EXISTENT_ENTITY_ID = 12345l;

    public static final long UPDATE_ENTITY_ID = 10l;
    public static final String UPDATE_ENTITY_TYPE_NAME = "Institucija 3";

    public static final long ENTITY_ID_WITH_REFERENCES = 12l;
    public static final long DELETE_ENTITY_ID = 13l;

    public static final String READ_AUTHORITY = "CULTURAL_OFFERING_TYPE:read";
    public static final String WRITE_AUTHORITY = "CULTURAL_OFFERING_TYPE:write";

    public static final String NON_EXISTING_ENTITY_TYPE_NAME = "this should not exist";

}
