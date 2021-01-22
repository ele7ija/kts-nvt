package ftn.ktsnvt.culturalofferings.integration.cultural_offering;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ftn.ktsnvt.culturalofferings.dto.SearchFilterDTO;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.model.ImageModel;
import ftn.ktsnvt.culturalofferings.model.Location;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundByNameException;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.model.exceptions.UniqueEntityConstraintViolationException;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingService;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingSubtypeService;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingTypeService;
import ftn.ktsnvt.culturalofferings.service.LocationService;

import static ftn.ktsnvt.culturalofferings.integration.cultural_offering.CulturalOfferingConstants.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferingServiceTest {
	
	@Autowired
	CulturalOfferingService culturalOfferingService;
	
	@Autowired
	LocationService locationService; 
	
	@Autowired
	CulturalOfferingTypeService typeService;
	
	@Autowired
	CulturalOfferingSubtypeService subtypeService;
	
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
    
    @Test
    @Transactional
    @Rollback
    public void createTest(){
    	Location location = locationService.findOne(EXISTING_LOCATION_ID);
    	CulturalOfferingType type = typeService.findName(EXISTING_TYPE_NAME);
    	CulturalOfferingSubType subtype = subtypeService.findName(EXISTING_SUBTYPE_NAME);
    	
        CulturalOffering culturalOffering = new CulturalOffering(NON_EXISTING_ENTITY_NAME, "description", location, 
        		new HashSet<ImageModel>(), type, subtype);
        CulturalOffering offeringCreated = culturalOfferingService.create(culturalOffering);
        assertEquals(offeringCreated.getName(), NON_EXISTING_ENTITY_NAME);
    }
    
    @Test
    @Transactional
    @Rollback
    public void createTestFailsUniqName(){
    	Location location = locationService.findOne(EXISTING_LOCATION_ID);
    	CulturalOfferingType type = typeService.findName(EXISTING_TYPE_NAME);
    	CulturalOfferingSubType subtype = subtypeService.findName(EXISTING_SUBTYPE_NAME);
    	
        CulturalOffering culturalOffering = new CulturalOffering(EXISTING_ENTITY_NAME, "description", location, 
        		new HashSet<ImageModel>(), type, subtype);
        assertThrows(UniqueEntityConstraintViolationException.class, () -> culturalOfferingService.create(culturalOffering));
    }
    
    @Test
    @Transactional
    @Rollback
    public void updateTest(){
        CulturalOffering culturalOffering = culturalOfferingService.findOne(EXISTING_ENTITY_ID);
        String description = culturalOffering.getDescription();
        culturalOffering.setDescription("New updated description.");
        String name = culturalOffering.getName();
        culturalOffering.setName(NON_EXISTING_ENTITY_NAME);

        CulturalOffering culturalOfferingUpdated = culturalOfferingService.update(culturalOffering, EXISTING_ENTITY_ID);
        assertNotNull(culturalOfferingUpdated);
        assertEquals(culturalOfferingUpdated.getName(), NON_EXISTING_ENTITY_NAME);
        assertNotEquals(name, NON_EXISTING_ENTITY_NAME);
        assertNotEquals(description, culturalOfferingUpdated.getDescription());
    }
    
    @Test
    @Transactional
    @Rollback
    public void updateTestFailsNotFound(){
        CulturalOffering culturalOffering = new CulturalOffering();
        assertThrows(EntityNotFoundException.class, () -> culturalOfferingService.update(culturalOffering, NON_EXISTING_ENTITY_ID));
    }

    @Test
    @Transactional
    @Rollback
    public void deleteTest(){
        List<CulturalOffering> culturalOfferingList1 = culturalOfferingService.findAll();
        CulturalOffering culturalOffering = culturalOfferingService.findOne(EXISTING_ENTITY_ID);
        culturalOfferingService.delete(EXISTING_ENTITY_ID);
        List<CulturalOffering> culturalOfferingList2 = culturalOfferingService.findAll();

        assertNotNull(culturalOffering);
        assertThrows(EntityNotFoundException.class, () -> culturalOfferingService.findOne(EXISTING_ENTITY_ID));
        assertEquals(culturalOfferingList1.size(), culturalOfferingList2.size() + 1);
    }
    
    @Test
    @Transactional
    @Rollback
    public void deleteTestFails(){
        assertThrows(EntityNotFoundException.class, () -> culturalOfferingService.delete(NON_EXISTING_ENTITY_ID));
    }
    

    @Test
    @Transactional
    @Rollback
    public void searchFilterTestTerm(){
        SearchFilterDTO dto = new SearchFilterDTO(FILTER_TERM, FILTER_TYPES, FILTER_SUBTYPES, false);
        
        Page<CulturalOffering> res = culturalOfferingService.searchFilter(PageRequest.of(0, 10), dto);
        assertNotNull(res);
        assertEquals(1, res.getNumberOfElements());        
    }

    @Test
    @Transactional
    @Rollback
    public void searchFilterTestType(){
        SearchFilterDTO dto = new SearchFilterDTO("", FILTER_TERM_TYPES, FILTER_SUBTYPES, false);

        Page<CulturalOffering> res = culturalOfferingService.searchFilter(PageRequest.of(0, 10), dto);
        assertNotNull(res);
        assertEquals(1, res.getNumberOfElements());        
    }

    @Test
    @Transactional
    @Rollback
    public void searchFilterTestSubtype(){
        SearchFilterDTO dto = new SearchFilterDTO("", FILTER_TYPES, FILTER_TERM_SUBTYPES, false);
        
        Page<CulturalOffering> res = culturalOfferingService.searchFilter(PageRequest.of(0, 10), dto);
        assertNotNull(res);
        assertEquals(1, res.getNumberOfElements());        
    }

    @Test
    @Transactional
    @Rollback
    public void searchFilterTestTermType(){
        List<Long> types = new ArrayList<Long>();
        types.add(11L);
        SearchFilterDTO dto = new SearchFilterDTO(FILTER_TERM, new ArrayList<Long>(), types, false);
        
        Page<CulturalOffering> res = culturalOfferingService.searchFilter(PageRequest.of(0, 10), dto);
        assertNotNull(res);
        assertEquals(0, res.getNumberOfElements());        
    }
}
