package ftn.ktsnvt.culturalofferings.integration.cultural_offering_type;

import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CulturalOfferingTypeConstants {

    static final int LIST_SIZE = 4;
    static final int PAGE_NUMBER = 0;
    static final int PAGE_SIZE = 2;

    static final long EXISTING_ENTITY_ID = 10l;
    static final String EXISTING_ENTITY_TYPE_NAME = "Institucija";
    static final long EXISTING_ENTITY_IMAGE_MODEL_ID = 1l;

    static final String NEW_ENTITY_TYPE_NAME = "Institucija 2";

    static final long NON_EXISTENT_ENTITY_ID = 12345l;

    static final long UPDATE_ENTITY_ID = 10l;
    static final String UPDATE_ENTITY_TYPE_NAME = "Institucija 3";

    static final long ENTITY_ID_WITH_REFERENCES = 12l;
    static final long DELETE_ENTITY_ID = 13l;

    static final String READ_AUTHORITY = "CULTURAL_OFFERING_TYPE:read";
    static final String WRITE_AUTHORITY = "CULTURAL_OFFERING_TYPE:write";

    static final String NON_EXISTING_ENTITY_TYPE_NAME = "this should not exist";
}
