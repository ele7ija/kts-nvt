package ftn.ktsnvt.culturalofferings.helper;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingDTO;
import ftn.ktsnvt.culturalofferings.model.Comment;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
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

	@Override
	public CulturalOffering toEntity(CulturalOfferingDTO dto) {
		return culturalOfferingService.findOne(dto.getId());
	}
    
    public CulturalOffering toNewEntity(CulturalOfferingDTO dto) {
    	return new CulturalOffering(
	    	dto.getName(), 
			dto.getDescription(), 
			new Location(dto.getLongitude(), dto.getLatitude(), dto.getLocationName()), 
			new HashSet<Rating>(),
			new HashSet<Comment>(), 
			new HashSet<Subscription>(), 
			new HashSet<News>(),
			typeService.findName(dto.getCulturalOfferingTypeName()),
			subtypeService.findName(dto.getCulturalOfferingSubtypeName())
    	);
    }

	@Override
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
			entity.getCulturalOfferingSubType().getSubTypeName()
		); 
	}

    

}
