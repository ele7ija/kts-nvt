package ftn.ktsnvt.culturalofferings.dto;

import java.util.Set;

public class CulturalOfferingTypeUpsertDTO extends CulturalOfferingTypeDTO{

    private Set<String> subTypesToAdd;

    public CulturalOfferingTypeUpsertDTO(){}

    public CulturalOfferingTypeUpsertDTO(Long id, String typeName, Long imageId, Set<Long> subTypeIds, Set<String> subTypesToAdd) {
        super(id, typeName, imageId, subTypeIds);
        this.subTypesToAdd = subTypesToAdd;
    }

    public Set<String> getSubTypesToAdd() {
        return subTypesToAdd;
    }

    public void setSubTypesToAdd(Set<String> subTypesToAdd) {
        this.subTypesToAdd = subTypesToAdd;
    }
}
