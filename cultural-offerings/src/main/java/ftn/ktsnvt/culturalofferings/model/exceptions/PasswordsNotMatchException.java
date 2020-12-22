package ftn.ktsnvt.culturalofferings.model.exceptions;

public class PasswordsNotMatchException extends RuntimeException {
	
	private String email;
	
	public PasswordsNotMatchException() {}

	public PasswordsNotMatchException(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
