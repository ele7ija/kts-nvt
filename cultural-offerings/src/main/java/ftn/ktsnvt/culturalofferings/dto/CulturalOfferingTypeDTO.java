package ftn.ktsnvt.culturalofferings.dto;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CulturalOfferingTypeDTO {

	protected Long id;

	@NotBlank(message = "Cultural offering type name cannot be empty")
	protected String typeName;

	protected Long imageId;

	protected Set<Long> subTypeIds;

	public CulturalOfferingTypeDTO(){}

	public CulturalOfferingTypeDTO(Long id, String typeName, Long imageId, Set<Long> subTypeIds) {
		this.id = id;
		this.typeName = typeName;
		this.imageId = imageId;
		this.subTypeIds = subTypeIds;
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

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Set<Long> getSubTypeIds() {
		return subTypeIds;
	}

	public void setSubTypeIds(Set<Long> subTypeIds) {
		this.subTypeIds = subTypeIds;
	}
}
