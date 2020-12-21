package ftn.ktsnvt.culturalofferings.dto;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class CulturalOfferingTypeDTO {

	@NotBlank(message = "Cultural offering type name cannot be empty")
	private String typeName;

	private Long imageId;

	public CulturalOfferingTypeDTO(){}

	public CulturalOfferingTypeDTO(String typeName, Long imageId) {
		this.typeName = typeName;
		this.imageId = imageId;
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
}
