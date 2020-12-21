package ftn.ktsnvt.culturalofferings.integration.cultural_offering_subtype;

import ftn.ktsnvt.culturalofferings.repository.CulturalOfferingSubtypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static ftn.ktsnvt.culturalofferings.integration.cultural_offering_subtype.CulturalOfferingSubTypeConstants.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferingSubTypeRepositoryTest {

    @Autowired
    private CulturalOfferingSubtypeRepository culturalOfferingSubtypeRepository;

    @Test
    public void findBySubTypeNameFail(){
        assertNull(this.culturalOfferingSubtypeRepository.findBySubTypeName(NON_EXISTING_ENTITY_SUB_TYPE_NAME).orElse(null));
    }

    @Test
    public void findBySubTypeNameSucceed(){
        assertNotNull(this.culturalOfferingSubtypeRepository.findBySubTypeName(EXISTING_ENTITY_SUB_TYPE_NAME).get());
    }

}
