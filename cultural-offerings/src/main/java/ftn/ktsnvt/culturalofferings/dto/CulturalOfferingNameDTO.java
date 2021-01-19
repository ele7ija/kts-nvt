package ftn.ktsnvt.culturalofferings.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CulturalOfferingNameDTO {

	@NotNull(message = "Cultural offering id must be provided!")
	@Positive(message = "Cultural offering id must be a positive number!")
	private Long id;

	@NotBlank(message = "Cultural offering name cannot be empty!")
	private String name;

	public CulturalOfferingNameDTO() {
	}

	public CulturalOfferingNameDTO(Long id, String name) {
		this.id = id;
		this.name = name;
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

}
