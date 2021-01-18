package ftn.ktsnvt.culturalofferings.unit.cultural_offering;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingDTO;
import ftn.ktsnvt.culturalofferings.mapper.CulturalOfferingsMapper;
import ftn.ktsnvt.culturalofferings.model.Comment;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.model.ImageModel;
import ftn.ktsnvt.culturalofferings.model.Location;
import ftn.ktsnvt.culturalofferings.model.News;
import ftn.ktsnvt.culturalofferings.model.Rating;
import ftn.ktsnvt.culturalofferings.model.Subscription;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundByNameException;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingService;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingSubtypeService;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingTypeService;
import ftn.ktsnvt.culturalofferings.service.ImageService;
import ftn.ktsnvt.culturalofferings.service.LocationService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CulturalOfferingMapperTest {
	
    @InjectMocks
    CulturalOfferingsMapper mapper;
    
    @Mock
    private CulturalOfferingService culturalOfferingService;
    
    @Mock
    private LocationService locationService;
    
    @Mock
    private CulturalOfferingTypeService typeService;
    
    @Mock
    private CulturalOfferingSubtypeService subtypeService;
    
    @Mock 
    private ImageService imageService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);	
    }
	
	@Test
	public void toEntityTestNull() {
		CulturalOfferingDTO dto = new CulturalOfferingDTO(null, "New name", "Some description", 11l, 20.0f, 45.5f, 
        		"Trg republike BB, Novi Sad", "type name", "subtype name", new ArrayList<Long>());
		
		Location location = new Location(11l, 20.0f, 45.5f, "Trg republike BB, Novi Sad");
		CulturalOfferingType type = new CulturalOfferingType(1l, "type name", new ImageModel(), new HashSet<>());
		CulturalOfferingSubType subtype = new CulturalOfferingSubType(1l, "subtype name", type);
		CulturalOffering culturalOffering = new CulturalOffering( "New name", "Some description", location, 
				new HashSet<ImageModel>(), type, subtype);
		
		when(imageService.findAll(new ArrayList<Long>())).thenReturn(new ArrayList<ImageModel>());
		when(typeService.findName("type name")).thenReturn(type);
		when(subtypeService.findName("subtype name")).thenReturn(subtype);
		when(locationService.findOne(11l)).thenReturn(location);
		when(locationService.update(location, 11l)).thenReturn(location);
		
		CulturalOffering entity = mapper.toEntity(dto, null);
		
		assertNotNull(entity);
		assertEquals(culturalOffering.getName(), entity.getName());
		assertEquals(culturalOffering.getLocation().getName(), entity.getLocation().getName());
		assertEquals(culturalOffering.getCulturalOfferingType().getTypeName(), entity.getCulturalOfferingType().getTypeName());
		assertEquals(culturalOffering.getCulturalOfferingSubType().getSubTypeName(), entity.getCulturalOfferingSubType().getSubTypeName());
		
	}
	
	@Test
	public void toEntityTestId() {
		CulturalOfferingDTO dto = new CulturalOfferingDTO(null, "New name", "Some description", 11l, 20.0f, 45.5f, 
        		"Trg republike BB, Novi Sad", "type name", "subtype name", new ArrayList<Long>());
		
		Location location = new Location(11l, 20.0f, 45.5f, "Trg republike BB, Novi Sad");
		CulturalOfferingType type = new CulturalOfferingType(1l, "type name", new ImageModel(), new HashSet<>());
		CulturalOfferingSubType subtype = new CulturalOfferingSubType(1l,"subtype name", type);
		CulturalOffering culturalOffering = new CulturalOffering( "New name", "Some description", location, 
				new HashSet<ImageModel>(), type, subtype);
		Long id = 32l;
		
		when(imageService.findAll(new ArrayList<Long>())).thenReturn(new ArrayList<ImageModel>());
		when(typeService.findName("type name")).thenReturn(type);
		when(subtypeService.findName("subtype name")).thenReturn(subtype);
		when(locationService.findOne(11l)).thenReturn(location);
		when(locationService.update(location, 11l)).thenReturn(location);
		when(culturalOfferingService.findOne(id)).thenReturn(culturalOffering);
		
		CulturalOffering entity = mapper.toEntity(dto, id);
		
		assertNotNull(entity);
		assertEquals(culturalOffering.getId(), entity.getId());
		assertEquals(culturalOffering.getName(), entity.getName());
		assertEquals(culturalOffering.getLocation().getName(), entity.getLocation().getName());
		assertEquals(culturalOffering.getCulturalOfferingType().getTypeName(), entity.getCulturalOfferingType().getTypeName());
		assertEquals(culturalOffering.getCulturalOfferingSubType().getSubTypeName(), entity.getCulturalOfferingSubType().getSubTypeName());
		
	}
	
	@Test(expected = EntityNotFoundByNameException.class)
	public void toEntityTestFailsType() {
		CulturalOfferingDTO dto = new CulturalOfferingDTO(null, "New name", "Some description", 11l, 20.0f, 45.5f, 
        		"Trg republike BB, Novi Sad", "wrong type name", "subtype name", new ArrayList<Long>());
		
		Location location = new Location(11l, 20.0f, 45.5f, "Trg republike BB, Novi Sad");
		CulturalOfferingType type = new CulturalOfferingType(1l, "type name", new ImageModel(), new HashSet<>());
		CulturalOfferingSubType subtype = new CulturalOfferingSubType(1l,"subtype name", type);

		Long id = 32l;
		
		when(imageService.findAll(new ArrayList<Long>())).thenReturn(new ArrayList<ImageModel>());
		when(typeService.findName(" wrong type name")).thenReturn(null);
		when(subtypeService.findName("subtype name")).thenReturn(subtype);
		when(locationService.findOne(11l)).thenReturn(location);
		when(locationService.update(location, 11l)).thenReturn(location);
				
		mapper.toEntity(dto, id);		
	}
	
	@Test
    public void toDtoTest(){
		Location location = new Location(11l, 20.0f, 45.5f, "Trg republike BB, Novi Sad");
		CulturalOfferingType type = new CulturalOfferingType(1l, "type name", new ImageModel(), new HashSet<>());
		CulturalOfferingSubType subtype = new CulturalOfferingSubType(1l,"subtype name", type);
		
		CulturalOffering culturalOffering = new CulturalOffering("Cultural offering", "Description", location, 
				new HashSet<Rating>(), new HashSet<Comment>(), new HashSet<Subscription>(), new HashSet<News>(),
				new HashSet<ImageModel>(), type, subtype);
		
		CulturalOfferingDTO dto = mapper.toDto(culturalOffering);
		
		assertNotNull(dto);
		assertEquals(culturalOffering.getName(), dto.getName());
		assertEquals(culturalOffering.getLocation().getName(), dto.getLocationName());
		assertEquals(culturalOffering.getCulturalOfferingType().getTypeName(), dto.getCulturalOfferingTypeName());
		assertEquals(culturalOffering.getCulturalOfferingSubType().getSubTypeName(), dto.getCulturalOfferingSubtypeName());

	}
	
	@Test
    public void toLocationTest() {
		Location location = new Location(11l, 20.0f, 45.5f, "Trg republike BB, Novi Sad");
		
		when(locationService.findOne(11l)).thenReturn(location);
		when(locationService.update(location, 11l)).thenReturn(location);	

		Location returnedLocation = mapper.toLocation(11l, 20.0f, 45.5f, "Trg republike BB");
		
		assertNotNull(returnedLocation);
		
	}
	
	
	@Test
    public void toLocationTestIdNull() { 
        Location location = new Location(27l, 20.0f, 45.5f, "Trg republike BB");
		
		when(locationService.create(Mockito.any(Location.class))).thenReturn(location);	

		Location returnedLocation = mapper.toLocation(null, 20.0f, 45.5f, "Trg republike BB");
		
		assertNotNull(returnedLocation);
		
	}
	
	
	@Test(expected = EntityNotFoundException.class)
    public void toLocationTestFails() {
		Long wrongId = 58l;
		
		when(locationService.findOne(wrongId)).thenReturn(null);

		mapper.toLocation(wrongId, 20.0f, 45.5f, "Trg republike BB");		
	}

}
