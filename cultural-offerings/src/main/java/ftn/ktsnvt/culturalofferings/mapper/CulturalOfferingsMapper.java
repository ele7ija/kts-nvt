package ftn.ktsnvt.culturalofferings.mapper;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingDTO;
import ftn.ktsnvt.culturalofferings.model.Comment;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.ImageModel;
import ftn.ktsnvt.culturalofferings.model.Location;
import ftn.ktsnvt.culturalofferings.model.News;
import ftn.ktsnvt.culturalofferings.model.Rating;
import ftn.ktsnvt.culturalofferings.model.Subscription;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingService;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingSubtypeService;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingTypeService;
import ftn.ktsnvt.culturalofferings.service.LocationService;

@Component
public class CulturalOfferingsMapper implements MapperInterface<CulturalOffering, CulturalOfferingDTO>{

    @Autowired
    private CulturalOfferingService culturalOfferingService;
    
    @Autowired
    private LocationService locationService;
    
    @Autowired
    private CulturalOfferingTypeService typeService;
    
    @Autowired
    private CulturalOfferingSubtypeService subtypeService;

    // this method is called when someone edits CulturalOffering
    // images cannot be changed through changing ids in list
	@Override
	public CulturalOffering toEntity(CulturalOfferingDTO dto)  {
		CulturalOffering offering = culturalOfferingService.findOne(dto.getId());
		
		/*
		if(offering == null ) {
			throw new Exception("Cultural offering with given id doesn't exist");
		}
		
		// if name changes check if it is unique
		// throws exception if cannot find type
    	// throws exception if cannot find subtype
		*/
		Location location = toLocation(dto.getId(), dto.getLongitude(), dto.getLatitude());
		
		offering.setName(dto.getName());
		offering.setDescription(dto.getDescription());
		offering.setLocation(location);
		offering.setCulturalOfferingType(typeService.findName(dto.getCulturalOfferingTypeName()));
		offering.setCulturalOfferingSubType(subtypeService.findName(dto.getCulturalOfferingSubtypeName()));
		
		
		return offering;
	}
    
	// this method is called when someone adds CulturalOffering
    public CulturalOffering toNewEntity(CulturalOfferingDTO dto) {
    	// warning: possibility of duplicating Locations without checking if location exists
    	// throws exception if name exists
    	// throws exception if cannot find type
    	// throws exception if cannot find subtype
    	
    	return new CulturalOffering(
	    	dto.getName(), 
			dto.getDescription(), 
			new Location(dto.getLongitude(), dto.getLatitude(), dto.getLocationName()), 
			new HashSet<Rating>(),
			new HashSet<Comment>(), 
			new HashSet<Subscription>(), 
			new HashSet<News>(),
			new HashSet<ImageModel>(),
			typeService.findName(dto.getCulturalOfferingTypeName()),
			subtypeService.findName(dto.getCulturalOfferingSubtypeName())
    	);
    }

	@Override
	public CulturalOfferingDTO toDto(CulturalOffering entity) {
		Location location = entity.getLocation();
		ArrayList<Long> images = new ArrayList<Long>();
		for(ImageModel i : entity.getImages()) {
			images.add(i.getId());
		}
		
		return new CulturalOfferingDTO(
			entity.getId(), 
			entity.getName(), 
			entity.getDescription(), 
			location.getId(), 
			location.getLongitude(), 
			location.getLatitude(),
			location.getName(), 
			entity.getCulturalOfferingType().getTypeName(),
			entity.getCulturalOfferingSubType().getSubTypeName(),
			images
		); 
	}

    public Location toLocation(Long id, float longitude, float latitude) {
    	Location location = locationService.findOne(id);
    	/*
    	if(location == null) {
    		throw new Exception("Location with given id doesn't exist");
    	}*/
    	
    	location.setLongitude(longitude);
    	location.setLatitude(latitude);
    	
    	return location;
    }

}
