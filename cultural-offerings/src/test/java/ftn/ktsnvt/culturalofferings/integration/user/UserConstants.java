package ftn.ktsnvt.culturalofferings.integration.user;

import ftn.ktsnvt.culturalofferings.model.UserRole;

public class UserConstants {

    public static final int LIST_SIZE = 5;
    public static final int PAGE_NUMBER = 0;
    public static final int PAGE_SIZE = 2;

    public static final long EXISTING_ENTITY_ID = 1l;
    public static final String EXISTING_ENTITY_EMAIL = "mika.mika@gmail.com";
    public static final String EXISTING_ENTITY_PASSWORD = "Mika";
    public static final String EXISTING_ENTITY_FIRST_NAME = "Mika";
    public static final String EXISTING_ENTITY_LAST_NAME = "Mika";
    public static final UserRole EXISTING_ENTITY_USER_ROLE = UserRole.USER;

    public static final String NON_EXISTENT_ENTITY_EMAIL = "this email does not exist";

    public static final String NEW_ENTITY_EMAIL = "test.new@gmail.com";
    public static final String NEW_ENTITY_PASSWORD = "test new";
    public static final String NEW_ENTITY_FIRST_NAME = "test new";
    public static final String NEW_ENTITY_LAST_NAME = "test new";
    public static final UserRole NEW_ENTITY_USER_ROLE = UserRole.USER;

    public static final long NON_EXISTENT_ENTITY_ID = 12345l;

    public static final long UPDATE_ENTITY_ID = 1l;
    public static final String UPDATE_ENTITY_EMAIL = "test.update@gmail.com";
    public static final String UPDATE_ENTITY_PASSWORD = "test update";
    public static final String UPDATE_ENTITY_FIRST_NAME = "test update";
    public static final String UPDATE_ENTITY_LAST_NAME = "test update";
    public static final long REMOVE_COMMENT_ID = 10l;
    public static final long REMOVE_SUBSCRIPTION_ID = 10l;

    public static final long DELETE_ENTITY_ID = 1l;

    public static final String EXISTING_VERIFICATION_TOKEN_TEXT = "token";
    public static final String NON_EXISTENT_VERIFICATION_TOKEN_TEXT = "asfqwer";
    public static final long ENABLE_ENTITY_ID = 5l;
    public static final String ENABLE_ENTITY_EMAIL = "sanja.sanjic@gmail.com";

    public static final String READ_AUTHORITY = "USER:read";
    public static final String WRITE_AUTHORITY = "USER:write";
}
