package ftn.ktsnvt.culturalofferings.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

public class CulturalOfferingTypeDTO {

	private Long id;
	
	@NotBlank(message = "Name cannot be empty.") 
	private String typeName;
	
	// unique image name
	private String iconName;
	
	private Set<String> culturalOfferingSubtypes;
	
	public CulturalOfferingTypeDTO() {}

	public CulturalOfferingTypeDTO(Long id, @NotBlank(message = "Name cannot be empty.")  String typeName, 
			String icon, Set<String> culturalOfferingSubtypes) {
		this.id = id;
		this.typeName = typeName;
		this.iconName = icon;
		this.culturalOfferingSubtypes = culturalOfferingSubtypes;
	}

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

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public Set<String> getCulturalOfferingSubtypes() {
		return culturalOfferingSubtypes;
	}

	public void setCulturalOfferingSubtypes(Set<String> culturalOfferingSubtypes) {
		this.culturalOfferingSubtypes = culturalOfferingSubtypes;
	}
	
}
