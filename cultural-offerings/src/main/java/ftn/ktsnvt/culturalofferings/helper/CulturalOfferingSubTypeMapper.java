package ftn.ktsnvt.culturalofferings.helper;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingSubTypeDTO;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingTypeService;
import org.springframework.beans.factory.annotation.Autowired;

public class CulturalOfferingSubTypeMapper implements MapperInterface<CulturalOfferingSubType, CulturalOfferingSubTypeDTO>{

    @Autowired
    private CulturalOfferingTypeService culturalOfferingTypeService;

    @Override
    public CulturalOfferingSubType toEntity(CulturalOfferingSubTypeDTO dto) {
        return new CulturalOfferingSubType(
                dto.getSubTypeName(),
                culturalOfferingTypeService.findOne((dto.getTypeId()))
        );
    }

    @Override
    public CulturalOfferingSubTypeDTO toDto(CulturalOfferingSubType entity) {
        return new CulturalOfferingSubTypeDTO(
                entity.getSubTypeName(),
                entity.getCulturalOfferingType().getId()
        );
    }
}
