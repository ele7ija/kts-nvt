package ftn.ktsnvt.culturalofferings.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CulturalOfferingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String typeName;

    @Column
    private String icon;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "culturalOfferingType")
    private Set<CulturalOfferingSubType> culturalOfferingSubTypes;

    public CulturalOfferingType(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Set<CulturalOfferingSubType> getCulturalOfferingSubTypes() {
        return culturalOfferingSubTypes;
    }

    public void setCulturalOfferingSubTypes(Set<CulturalOfferingSubType> culturalOfferingSubTypes) {
        this.culturalOfferingSubTypes = culturalOfferingSubTypes;
    }
}
