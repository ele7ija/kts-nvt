package ftn.ktsnvt.culturalofferings.mapper;

import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeDTO;
import ftn.ktsnvt.culturalofferings.dto.CulturalOfferingTypeUpsertDTO;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingSubType;
import ftn.ktsnvt.culturalofferings.model.CulturalOfferingType;
import ftn.ktsnvt.culturalofferings.model.exceptions.ModelConstraintViolationException;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingSubtypeService;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingTypeService;
import ftn.ktsnvt.culturalofferings.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CulturalOfferingTypeMapper implements MapperInterface<CulturalOfferingType, CulturalOfferingTypeDTO>{

    @Autowired
    private ImageService imageService;

    @Autowired
    private CulturalOfferingSubtypeService culturalOfferingSubTypeService;

    @Autowired
    private CulturalOfferingTypeService culturalOfferingTypeService;

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
    public CulturalOfferingType toEntityAddSubTypes(CulturalOfferingTypeUpsertDTO body) {
        List<CulturalOfferingType> culturalOfferingTypes = this.culturalOfferingTypeService.findAllByTypeName(body.getTypeName());
        for(CulturalOfferingType culturalOfferingType : culturalOfferingTypes){
            if(culturalOfferingType.getId() != body.getId() && culturalOfferingType.getTypeName().equals(body.getTypeName())){
                throw new ModelConstraintViolationException("Type name already exists", CulturalOfferingType.class);
            }
        }

        CulturalOfferingTypeDTO culturalOfferingTypeDTO = new CulturalOfferingTypeDTO();
        culturalOfferingTypeDTO.setId(body.getId());
        culturalOfferingTypeDTO.setTypeName(body.getTypeName());
        culturalOfferingTypeDTO.setImageId(body.getImageId());
        culturalOfferingTypeDTO.setSubTypeIds(body.getSubTypeIds());
        CulturalOfferingType culturalOfferingType = this.toEntity(culturalOfferingTypeDTO);

        Set<CulturalOfferingSubType> culturalOfferingSubTypeSet = body.getSubTypesToAdd().stream().map(subTypeName -> {
            CulturalOfferingSubType culturalOfferingSubType = new CulturalOfferingSubType();
            if(culturalOfferingType.getId() != null)
                culturalOfferingSubType.setCulturalOfferingType(culturalOfferingType);
            if(culturalOfferingSubTypeService.findName(subTypeName) != null){
                throw new ModelConstraintViolationException("Sub-type name already exists", CulturalOfferingSubType.class);
            }
            culturalOfferingSubType.setSubTypeName(subTypeName);
            return this.culturalOfferingSubTypeService.create(culturalOfferingSubType);
        }).collect(Collectors.toSet());

        culturalOfferingTypeDTO.getSubTypeIds().addAll(culturalOfferingSubTypeSet.stream().map(item -> item.getId()).collect(Collectors.toSet()));

        return this.toEntity(culturalOfferingTypeDTO);
    }
}
