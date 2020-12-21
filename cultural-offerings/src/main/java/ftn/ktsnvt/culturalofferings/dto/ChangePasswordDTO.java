package ftn.ktsnvt.culturalofferings.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ChangePasswordDTO {
	
    @Size(min = 3, message = "Password must be at least 3 characters long")
	@NotBlank(message = "Old password cannot be empty")
	private String oldPassword;
	
	@NotBlank(message = "New password cannot be empty")
    @Size(min = 3, message = "Password must be at least 3 characters long")
	private String newPassword;
	
	public ChangePasswordDTO() {}

	public ChangePasswordDTO(String oldPassword, String newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
