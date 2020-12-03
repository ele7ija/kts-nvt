package ftn.ktsnvt.culturalofferings.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;


public class CulturalOfferingDTO {
	
	private Long id;
	
	@NotBlank(message = "Cultural offering name cannot be empty")
	private String name;
	
	private String description;
	
	private Long locationId;
	
	private float longitude;

    private float latitude;

    @NotBlank(message = "Location name cannot be empty")
    private String locationName;
    
    @NotBlank(message = "Cultural offering type name cannot be empty")
    private String culturalOfferingTypeName;
    
    @NotBlank(message = "Cultural offering subtype name cannot be empty")
    private String culturalOfferingSubtypeName;
    
    public CulturalOfferingDTO() {}
    
	public CulturalOfferingDTO(Long id, String name, String description, Long locationId, float longitude, float latitude,
			String locationName, String culturalOfferingTypeName, String culturalOfferingSubtypeName) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.locationId = locationId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.locationName = locationName;
		this.culturalOfferingTypeName = culturalOfferingTypeName;
		this.culturalOfferingSubtypeName = culturalOfferingSubtypeName;
	}

	public CulturalOfferingDTO(String name, String description, float longitude, float latitude,
			String locationName, String culturalOfferingTypeName, String culturalOfferingSubtypeName) {
		super();
		this.name = name;
		this.description = description;
		this.longitude = longitude;
		this.latitude = latitude;
		this.locationName = locationName;
		this.culturalOfferingTypeName = culturalOfferingTypeName;
		this.culturalOfferingSubtypeName = culturalOfferingSubtypeName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
