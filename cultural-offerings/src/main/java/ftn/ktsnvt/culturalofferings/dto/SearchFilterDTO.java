package ftn.ktsnvt.culturalofferings.dto;

import java.util.List;

public class SearchFilterDTO {
    private String term;
    private List<Long> culturalOfferingTypeIds;
    private List<Long> culturalOfferingSubtypeIds;


    public SearchFilterDTO(String term, List<Long> culturalOfferingTypeIds, List<Long> culturalOfferingSubtypeIds) {
        this.term = term;
        this.culturalOfferingTypeIds = culturalOfferingTypeIds;
        this.culturalOfferingSubtypeIds = culturalOfferingSubtypeIds;
    }

    public SearchFilterDTO() {
    }


    public String getTerm() {
        return this.term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public List<Long> getCulturalOfferingTypeIds() {
        return this.culturalOfferingTypeIds;
    }

    public void setCulturalOfferingTypeIds(List<Long> culturalOfferingTypeIds) {
        this.culturalOfferingTypeIds = culturalOfferingTypeIds;
    }

    public List<Long> getCulturalOfferingSubtypeIds() {
        return this.culturalOfferingSubtypeIds;
    }

    public void setCulturalOfferingSubtypeIds(List<Long> culturalOfferingSubtypeIds) {
        this.culturalOfferingSubtypeIds = culturalOfferingSubtypeIds;
    }

}
