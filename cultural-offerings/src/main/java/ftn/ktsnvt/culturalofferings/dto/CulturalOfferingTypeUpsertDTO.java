package ftn.ktsnvt.culturalofferings.dto;

import java.util.Set;

public class CulturalOfferingTypeUpdateDTO extends CulturalOfferingTypeDTO{

    private Set<String> subTypesToAdd;

    public CulturalOfferingTypeUpdateDTO(){}

    public CulturalOfferingTypeUpdateDTO(Long id, String typeName, Long imageId, Set<Long> subTypeIds, Set<String> subTypesToAdd) {
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
