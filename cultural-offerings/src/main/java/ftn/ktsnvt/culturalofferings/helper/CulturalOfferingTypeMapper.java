package ftn.ktsnvt.culturalofferings.helper;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeDTO;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;

public class CulturalOfferingTypeMapper implements MapperInterface<CulturalOfferingType, CulturalOfferingTypeDTO>{

    @Override
    public CulturalOfferingType toEntity(CulturalOfferingTypeDTO dto) {
        return new CulturalOfferingType();
    }

    @Override
    public CulturalOfferingTypeDTO toDto(CulturalOfferingType entity) {
        return new CulturalOfferingTypeDTO();
    }
}
