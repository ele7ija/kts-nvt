package ftn.ktsnvt.culturalofferings.dto;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


public class CulturalOfferingDTO {
	
	@NotBlank(message = "Cultural offering name cannot be empty")
	private String name;
	
	// can be empty
	private String description;
	
	private Long locationId;
	
	@NotNull(message = "Location longitude must be provided")
	private float longitude;

	@NotNull(message = "Location latitude id must be provided")
    private float latitude;

    @NotBlank(message = "Location name cannot be empty")
    private String locationName;
    
    @NotBlank(message = "Cultural offering type name cannot be empty")
    private String culturalOfferingTypeName;
    
    @NotBlank(message = "Cultural offering subtype name cannot be empty")
    private String culturalOfferingSubtypeName;
    
    private List<Long> imageIds;
    
    public CulturalOfferingDTO() {}
    
	public CulturalOfferingDTO(String name, String description, Long locationId, float longitude, float latitude,
			String locationName, String culturalOfferingTypeName, String culturalOfferingSubtypeName, List<Long> imageIds) {
		super();
		this.name = name;
		this.description = description;
		this.locationId = locationId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.locationName = locationName;
		this.culturalOfferingTypeName = culturalOfferingTypeName;
		this.culturalOfferingSubtypeName = culturalOfferingSubtypeName;
		this.imageIds = imageIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getCulturalOfferingTypeName() {
		return culturalOfferingTypeName;
	}

	public void setCulturalOfferingTypeName(String culturalOfferingTypeName) {
		this.culturalOfferingTypeName = culturalOfferingTypeName;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getCulturalOfferingSubtypeName() {
		return culturalOfferingSubtypeName;
	}

	public void setCulturalOfferingSubtypeName(String culturalOfferingSubtypeName) {
		this.culturalOfferingSubtypeName = culturalOfferingSubtypeName;
	}

	public List<Long> getImageIds() {
		return imageIds;
	}

	public void setImageIds(List<Long> imageIds) {
		this.imageIds = imageIds;
	}

}
