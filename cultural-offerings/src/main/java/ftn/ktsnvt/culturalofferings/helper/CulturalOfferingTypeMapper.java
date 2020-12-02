package ftn.ktsnvt.culturalofferings.helper;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeDTO;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CulturalOfferingTypeMapper implements MapperInterface<CulturalOfferingType, CulturalOfferingTypeDTO>{

    @Autowired
    private ImageService imageService;

    @Override
    public CulturalOfferingType toEntity(CulturalOfferingTypeDTO dto) {
        return new CulturalOfferingType(
                dto.getTypeName(),
                imageService.findOne(dto.getImageId())
        );
    }

    @Override
    public CulturalOfferingTypeDTO toDto(CulturalOfferingType entity) {
        return new CulturalOfferingTypeDTO(
                entity.getTypeName(),
                entity.getImageModel().getId()
        );
    }
}
