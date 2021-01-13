package ftn.ktsnvt.culturalofferings.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingDTO;
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

@Component
public class CulturalOfferingsMapper{

    @Autowired
    private CulturalOfferingService culturalOfferingService;
    
    @Autowired
    private LocationService locationService;
    
    @Autowired
    private CulturalOfferingTypeService typeService;
    
    @Autowired
    private CulturalOfferingSubtypeService subtypeService;
    
    @Autowired 
    private ImageService imageService;

	public CulturalOffering toEntity(CulturalOfferingDTO dto) {
		Location location = toLocation(dto.getLocationId(), dto.getLongitude(), dto.getLatitude(), dto.getName());
		
		List<ImageModel> imgList = imageService.findAll(dto.getImageIds());
		Set<ImageModel> imgSet = imgList.stream().collect(Collectors.toSet());
		
		CulturalOfferingType type = typeService.findName(dto.getCulturalOfferingTypeName());
		CulturalOfferingSubType subtype = subtypeService.findName(dto.getCulturalOfferingSubtypeName());
		check(type, dto.getCulturalOfferingTypeName(), subtype, dto.getCulturalOfferingSubtypeName());

		if(dto.getId() == null) {
			//if id is unknown create new CulturalOffering
			return new CulturalOffering(
					dto.getName(),
					dto.getDescription(),
					location,
					imgSet,
					type,
					subtype
					);
		}
		
		// else, update existing CulturalOffering
		CulturalOffering offering = culturalOfferingService.findOne(dto.getId());
		offering.setName(dto.getName());
		offering.setDescription(dto.getDescription());
		offering.setLocation(location);
		offering.getImages().clear();
		offering.getImages().addAll(imgSet);
		offering.setCulturalOfferingType(type);
		offering.setCulturalOfferingSubType(subtype);
		return offering;
		
	}

	public CulturalOfferingDTO toDto(CulturalOffering entity) {
		Location location = entity.getLocation();
		
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
			entity.getImages()
				.stream()
	            .map(image -> image.getId())
	            .collect(Collectors.toList())
		); 
	}

    public Location toLocation(Long id, float longitude, float latitude, String name) {
    	// if creating new Location
    	if(id == null) {
    		Location location = new Location(longitude, latitude, name);
    		return locationService.create(location);
    	}
    	
    	// if updating existing location
    	Location location = locationService.findOne(id);
    	
    	if(location == null) {
    		throw new EntityNotFoundException(id, Location.class);
    	}

    	location.setLongitude(longitude);
    	location.setLatitude(latitude);
    	location.setName(name);
    	
    	return locationService.update(location, id);
    }
    
    private void check(CulturalOfferingType type, String typeName, CulturalOfferingSubType subtype, String subtypeName) {
    	if(type == null) {
    		throw new EntityNotFoundByNameException(typeName, CulturalOfferingType.class);
    	}
    	if(subtype == null) {
    		throw new EntityNotFoundByNameException(subtypeName, CulturalOfferingSubType.class);
    	}
    }

}
