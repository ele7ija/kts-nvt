package ftn.ktsnvt.culturalofferings.mapper;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingSubTypeDTO;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CulturalOfferingSubTypeMapper implements MapperInterface<CulturalOfferingSubType, CulturalOfferingSubTypeDTO>{

    @Autowired
    private CulturalOfferingTypeService culturalOfferingTypeService;

    @Override
    public CulturalOfferingSubType toEntity(CulturalOfferingSubTypeDTO dto) {
        return new CulturalOfferingSubType(
                dto.getId(),
                dto.getSubTypeName(),
                culturalOfferingTypeService.findOne((dto.getTypeId()))
        );
    }

    @Override
    public CulturalOfferingSubTypeDTO toDto(CulturalOfferingSubType entity) {
        return new CulturalOfferingSubTypeDTO(
                entity.getId(),
                entity.getSubTypeName(),
                entity.getCulturalOfferingType().getId()
        );
    }
}
