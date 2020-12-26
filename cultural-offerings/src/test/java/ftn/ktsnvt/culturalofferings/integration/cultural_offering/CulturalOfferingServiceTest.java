package ftn.ktsnvt.culturalofferings.integration.cultural_offering;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundByNameException;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingService;
import static ftn.ktsnvt.culturalofferings.integration.cultural_offering.CulturalOfferingConstants.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferingServiceTest {
	
	@Autowired
	CulturalOfferingService culturalOfferingService;
	
	@Test
    public void findAllTest(){
		List<CulturalOffering> culturalOferingList = culturalOfferingService.findAll();
        assertEquals(culturalOferingList.size(), LIST_SIZE);
    }
	
	@Test
    public void findAllByPageTest(){
        Page<CulturalOffering> culturalOferingPage = culturalOfferingService.findAll(PageRequest.of(PAGE_NUMBER, PAGE_SIZE));
        assertEquals(culturalOferingPage.getNumber(), PAGE_NUMBER);
        assertEquals(culturalOferingPage.getTotalPages(), TOTAL_PAGES);
        assertEquals(culturalOferingPage.getTotalElements(), LIST_SIZE);
        assertEquals(culturalOferingPage.getNumberOfElements(), PAGE_SIZE);
    }
	
	@Test
    public void findAllByPageTestFails(){
        Page<CulturalOffering> culturalOferingPage = culturalOfferingService.findAll(PageRequest.of(UNREACHABLE_PAGE_NUMBER, PAGE_SIZE));
        assertEquals(culturalOferingPage.getNumber(), UNREACHABLE_PAGE_NUMBER);
        assertEquals(culturalOferingPage.getTotalPages(), TOTAL_PAGES);
        assertEquals(culturalOferingPage.getTotalElements(), LIST_SIZE);
        assertEquals(culturalOferingPage.getNumberOfElements(), 0);
    }

    @Test
    public void findOneTest(){
        CulturalOffering culturalOffering = culturalOfferingService.findOne(EXISTING_ENTITY_ID);
        assertNotNull(culturalOffering);
        assertEquals(culturalOffering.getName(), EXISTING_ENTITY_NAME);
        assertEquals(culturalOffering.getId().longValue(), EXISTING_ENTITY_ID);
    }

    @Test
    public void findOneTestFails(){
        assertThrows(EntityNotFoundException.class, () -> culturalOfferingService.findOne(NON_EXISTING_ENTITY_ID));
    }
    
    @Test
    public void findByNameTest(){
        CulturalOffering culturalOffering = culturalOfferingService.findName(EXISTING_ENTITY_NAME);
        assertNotNull(culturalOffering);
        assertEquals(culturalOffering.getName(), EXISTING_ENTITY_NAME);
        assertEquals(culturalOffering.getId().longValue(), EXISTING_ENTITY_ID);
    }

    @Test
    public void findByNameTestFails(){
        assertThrows(EntityNotFoundByNameException.class, () -> culturalOfferingService.findName(NON_EXISTING_ENTITY_NAME));
    }

}
