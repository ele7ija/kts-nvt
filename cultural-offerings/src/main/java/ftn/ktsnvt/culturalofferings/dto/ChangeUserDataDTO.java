package ftn.ktsnvt.culturalofferings.dto;

import javax.validation.constraints.NotBlank;

public class ChangeUserDataDTO {
	
	@NotBlank(message = "User first name cannot be blank!")
	private String firstName;
	
	@NotBlank(message = "User last name cannot be blank!")
	private String lastName;
	
	public ChangeUserDataDTO() {}

	public ChangeUserDataDTO(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
