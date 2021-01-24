package ftn.ktsnvt.culturalofferings.integration.image;

import java.util.ArrayList;
import java.util.List;

public class ImageConstants {
    // repository constants
    static final long EXISTING_ENTITY_ID = 3l;
    static final long NON_EXISTING_ENTITY_ID = 525l;
    static final String EXISTING_ENTITY_NAME = "Image 1";
    static final String NON_EXISTING_ENTITY_NAME = "Spomenik kralju Perici";

    static final int FOUND_ELEMENTS_SUCCESS = 1;

    // service constants
    static final int LIST_SIZE = 3;
    static final int PAGE_NUMBER = 0;
    static final int PAGE_SIZE = 2;
    static final int TOTAL_PAGES = 2;
    static final int UNREACHABLE_PAGE_NUMBER = 5;
    
    static final long EXISTING_LOCATION_ID = 10l;
    static final String EXISTING_TYPE_NAME = "Kulturno dobro";
    static final String EXISTING_SUBTYPE_NAME = "Spomenik"; 

    static final String READ_AUTHORITY = "CULTURAL_OFFERING:read";
    

    static final String FILTER_TERM = "1936";
    static List<Long> FILTER_TERM_TYPES = null;
    static List<Long> FILTER_TERM_SUBTYPES = null;
    static List<Long> FILTER_TYPES = null;
    static List<Long> FILTER_SUBTYPES = null;
    static final int FILTERED_BY_TERM = 1;
    
    static final String NON_EXISTING_SUBTYPE_NAME = "Rezervat";

    static {
        List<Long> types = new ArrayList<Long>();
        types.add(10L);
        FILTER_TERM_TYPES = types;
        List<Long> subtypes = new ArrayList<Long>();
        subtypes.add(10L);
        FILTER_TERM_SUBTYPES = subtypes;

        List<Long> typesAll = new ArrayList<Long>();
        typesAll.add(10L);
        typesAll.add(11L);
        typesAll.add(12L);
        FILTER_TYPES = typesAll;
        List<Long> subtypesAll = new ArrayList<Long>();
        subtypesAll.add(10L);
        subtypesAll.add(11L);
        subtypesAll.add(12L);
        FILTER_SUBTYPES = subtypesAll;
    }
}
