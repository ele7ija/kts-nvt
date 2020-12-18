package ftn.ktsnvt.culturalofferings.integration.cultural_offering_type;

import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.repository.CulturalOfferingTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static ftn.ktsnvt.culturalofferings.integration.cultural_offering_type.CulturalOfferingTypeConstants.EXISTING_ENTITY_TYPE_NAME;
import static ftn.ktsnvt.culturalofferings.integration.cultural_offering_type.CulturalOfferingTypeConstants.NON_EXISTING_ENTITY_TYPE_NAME;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferingTypeRepositoryTest {

    @Autowired
    CulturalOfferingTypeRepository culturalOfferingTypeRepository;

    @Test
    public void findByTypeNameTestFail(){
        assertNull(this.culturalOfferingTypeRepository.findByTypeName(NON_EXISTING_ENTITY_TYPE_NAME).orElse(null));
    }

    @Test
    public void findByTypeNameTestSucceed(){
        Optional<CulturalOfferingType> culturalOfferingTypeOptional = this.culturalOfferingTypeRepository.findByTypeName(EXISTING_ENTITY_TYPE_NAME);
        assertNotNull(culturalOfferingTypeOptional.get());
        assertEquals(culturalOfferingTypeOptional.get().getTypeName(), EXISTING_ENTITY_TYPE_NAME);
    }
}
