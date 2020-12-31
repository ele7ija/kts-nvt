package ftn.ktsnvt.culturalofferings.integration.location;

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

import ftn.ktsnvt.culturalofferings.model.Location;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.service.LocationService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static ftn.ktsnvt.culturalofferings.integration.location.LocationConstants.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class LocationServiceTest {
	
	@Autowired
	LocationService locationService;
	
	@Test
    public void findAllTest(){
		List<Location> locationList = locationService.findAll();
        assertEquals(locationList.size(), LIST_SIZE);
    }
	
	@Test
    public void findAllByPageTest(){
        Page<Location> locationsPage = locationService.findAll(PageRequest.of(PAGE_NUMBER, PAGE_SIZE));
        assertEquals(locationsPage.getNumber(), PAGE_NUMBER);
        assertEquals(locationsPage.getTotalPages(), TOTAL_PAGES);
        assertEquals(locationsPage.getTotalElements(), LIST_SIZE);
        assertEquals(locationsPage.getNumberOfElements(), PAGE_SIZE);
    }
	
	@Test
    public void findAllByPageTestFails(){
		Page<Location> locationsPage = locationService.findAll(PageRequest.of(UNREACHABLE_PAGE_NUMBER, PAGE_SIZE));
        assertEquals(locationsPage.getNumber(), UNREACHABLE_PAGE_NUMBER);
        assertEquals(locationsPage.getTotalPages(), TOTAL_PAGES);
        assertEquals(locationsPage.getTotalElements(), LIST_SIZE);
        assertEquals(locationsPage.getNumberOfElements(), 0);
	}
	
	 @Test
	    public void findOneTest(){
	        Location location = locationService.findOne(EXISTING_ENTITY_ID);
	        assertNotNull(location);
	        assertEquals(location.getName(), EXISTING_ENTITY_NAME);
	        assertEquals(location.getId().longValue(), EXISTING_ENTITY_ID);
	    }

	    @Test
	    public void findOneTestFails(){
	        assertThrows(EntityNotFoundException.class, () -> locationService.findOne(NON_EXISTING_ENTITY_ID));
	    }
	    
	    @Test
	    @Transactional
	    @Rollback
	    public void createTest(){
	    	Location location = new Location(5.27f, 26.18f, "Neka adresa 12a");
	    	
	        Location locationCreated = locationService.create(location);
	        
	        assertNotNull(locationCreated);
	        assertEquals(locationCreated.getName(), "Neka adresa 12a");
	        
	        Location foundLocation = locationService.findOne(locationCreated.getId());
	        assertNotNull(foundLocation);       
	    }
	    
	    @Test
	    @Transactional
	    @Rollback
	    public void updateTest(){
	    	Location location = locationService.findOne(EXISTING_ENTITY_ID);
	    	Float exLongitude = location.getLongitude();
	    	String exName = location.getName();
	    	
	    	location.setLongitude(40.15f);
	    	location.setName("Stojana Novakovica 32");
	    	
	    	Location updatedLocation = locationService.update(location, EXISTING_ENTITY_ID);
	    	
	    	assertNotNull(updatedLocation);
	    	assertNotEquals(updatedLocation.getLongitude(), exLongitude);
	    	assertNotEquals(updatedLocation.getName(), exName);
	    	assertEquals(updatedLocation.getName(), "Stojana Novakovica 32");	    	
	    }
	    
	    @Test
	    @Transactional
	    @Rollback
	    public void updateTestFailsNotFound(){
	        Location location = new Location();
	        assertThrows(EntityNotFoundException.class, () -> locationService.update(location, NON_EXISTING_ENTITY_ID));
	    }
	    
	    @Test
	    @Transactional
	    @Rollback
	    public void deleteTest(){
	    	Location newLocation = new Location(5.27f, 26.18f, "Neka adresa 12a");
	    	Location createdLocation = locationService.create(newLocation);
	    	
	        List<Location> locationList1 = locationService.findAll(); 
	        locationService.delete(createdLocation.getId()); 
	        List<Location> locationList2 = locationService.findAll();

	        assertThrows(EntityNotFoundException.class, () -> locationService.findOne(createdLocation.getId()));
	        assertEquals(locationList1.size(), locationList2.size() + 1);
	    }
	    
	    @Test
	    @Transactional
	    @Rollback
	    public void deleteTestFails(){
	        assertThrows(EntityNotFoundException.class, () -> locationService.delete(NON_EXISTING_ENTITY_ID));
	    }

}
