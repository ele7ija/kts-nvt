package ftn.ktsnvt.culturalofferings.dto;

import java.util.List;

public class SearchFilterDTO {
    private String term;
    private List<Long> culturalOfferingTypeIds;
    private List<Long> culturalOfferingSubtypeIds;
    private boolean subscriptions;



    public SearchFilterDTO(String term, List<Long> culturalOfferingTypeIds, List<Long> culturalOfferingSubtypeIds, boolean pretplate) {
        this.term = term;
        this.culturalOfferingTypeIds = culturalOfferingTypeIds;
        this.culturalOfferingSubtypeIds = culturalOfferingSubtypeIds;
        this.subscriptions = pretplate;
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


    public boolean isSubscriptions() {
        return this.subscriptions;
    }

    public boolean getSubscriptions() {
        return this.subscriptions;
    }

    public void setSubscriptions(boolean subscriptions) {
        this.subscriptions = subscriptions;
    }


}
