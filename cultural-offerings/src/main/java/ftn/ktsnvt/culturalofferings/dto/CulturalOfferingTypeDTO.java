package ftn.ktsnvt.culturalofferings.dto;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class CulturalOfferingTypeDTO {

	@NotBlank(message = "Cultural offering type name cannot be empty")
	private String typeName;

	private List<Long> imageIds;

	public CulturalOfferingTypeDTO(String typeName, List<Long> imageIds) {
		this.typeName = typeName;
		this.imageIds = imageIds;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<Long> getImageIds() {
		return imageIds;
	}

	public void setImageIds(List<Long> imageIds) {
		this.imageIds = imageIds;
	}
}
