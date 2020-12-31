package ftn.ktsnvt.culturalofferings.integration.cultural_offering;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.repository.CulturalOfferingRepository;
import static ftn.ktsnvt.culturalofferings.integration.cultural_offering.CulturalOfferingConstants.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferingRepositoryTest {
	
	@Autowired
	CulturalOfferingRepository culturalOfferingRepository;
	
	@Test
    public void findByNameSucceed(){
        assertNotNull(this.culturalOfferingRepository.findByName(EXISTING_ENTITY_NAME).get());
    }
	
	@Test
    public void findByNameFail(){
        assertNull(this.culturalOfferingRepository.findByName(NON_EXISTING_ENTITY_NAME).orElse(null));
    }
	
	@Test
	public void findAllByCulturalOfferingTypeId() {
		List<CulturalOffering> list = this.culturalOfferingRepository.findAllByCulturalOfferingTypeId(EXISTING_ENTITY_TYPE_ID);
        assertNotNull(list);
        assertEquals(list.size(), FOUND_ELEMENTS_SUCCESS);
	}

	@Test
	public void findAllByCulturalOfferingTypeIdFail() {
		List<CulturalOffering> list = this.culturalOfferingRepository.findAllByCulturalOfferingTypeId(NON_EXISTING_ENTITY_TYPE_ID);
        assertNotNull(list);
        assertEquals(list.size(), 0);
	}
	
	@Test
	public void findAllByCulturalOfferingSubTypeId() {
		List<CulturalOffering> list = this.culturalOfferingRepository.findAllByCulturalOfferingSubTypeId(EXISTING_ENTITY_SUBTYPE_ID);
        assertNotNull(list);
        assertEquals(list.size(), FOUND_ELEMENTS_SUCCESS);
	}

	@Test
	public void findAllByCulturalOfferingSubTypeIdFail() {
		List<CulturalOffering> list = this.culturalOfferingRepository.findAllByCulturalOfferingSubTypeId(NON_EXISTING_ENTITY_SUBTYPE_ID);
        assertNotNull(list);
        assertEquals(list.size(), 0);
	}

}
