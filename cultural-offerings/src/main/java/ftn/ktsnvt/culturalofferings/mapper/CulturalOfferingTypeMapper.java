package ftn.ktsnvt.culturalofferings.mapper;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeDTO;
import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeUpdateDTO;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingSubtypeService;
import ftn.ktsnvt.culturalofferings.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CulturalOfferingTypeMapper implements MapperInterface<CulturalOfferingType, CulturalOfferingTypeDTO>{

    @Autowired
    private ImageService imageService;

    @Autowired
    private CulturalOfferingSubtypeService culturalOfferingSubTypeService;

    @Override
    public CulturalOfferingType toEntity(CulturalOfferingTypeDTO dto) {
        return new CulturalOfferingType(
                dto.getId(),
                dto.getTypeName(),
                imageService.findOne(dto.getImageId()),
                dto.getSubTypeIds() != null ? dto.getSubTypeIds().stream().map(id -> culturalOfferingSubTypeService.findOne(id)).collect(Collectors.toSet()) : new HashSet<>()
        );
    }

    @Override
    public CulturalOfferingTypeDTO toDto(CulturalOfferingType entity) {
        return new CulturalOfferingTypeDTO(
                entity.getId(),
                entity.getTypeName(),
                entity.getImageModel() != null ? entity.getImageModel().getId() : null,
                entity.getCulturalOfferingSubTypes() != null ? entity.getCulturalOfferingSubTypes().stream().map(x -> x.getId()).collect(Collectors.toSet()) : new HashSet<>()
        );
    }

    @Transactional
    public CulturalOfferingType toEntityAddSubTypes(CulturalOfferingTypeUpdateDTO body) {
        CulturalOfferingTypeDTO culturalOfferingTypeDTO = new CulturalOfferingTypeDTO();
        culturalOfferingTypeDTO.setId(body.getId());
        culturalOfferingTypeDTO.setTypeName(body.getTypeName());
        culturalOfferingTypeDTO.setImageId(body.getImageId());
        culturalOfferingTypeDTO.setSubTypeIds(body.getSubTypeIds());
        CulturalOfferingType culturalOfferingType = this.toEntity(culturalOfferingTypeDTO);

        Set<CulturalOfferingSubType> culturalOfferingSubTypeSet = body.getSubTypesToAdd().stream().map(subTypeName -> {
            CulturalOfferingSubType culturalOfferingSubType = new CulturalOfferingSubType();
            culturalOfferingSubType.setCulturalOfferingType(culturalOfferingType);
            culturalOfferingSubType.setSubTypeName(subTypeName);
            return this.culturalOfferingSubTypeService.create(culturalOfferingSubType);
        }).collect(Collectors.toSet());

        culturalOfferingTypeDTO.getSubTypeIds().addAll(culturalOfferingSubTypeSet.stream().map(item -> item.getId()).collect(Collectors.toSet()));

        return this.toEntity(culturalOfferingTypeDTO);
    }
}
