package org.bildit.lingua.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class UserDto {

	@Size(min = 2, max = 25)
	private String firstName;

	@Size(min = 2, max = 25)
	private String lastName;

	@Size(min = 4, max = 25)
	private String username;

	@Size(min = 6, max = 40)
	private String password;

	@Size(min = 6, max = 40)
	private String passwordRe;

	@Email
	@Size(min = 4, max = 30)
	private String email;

	public UserDto() {

	}

	public UserDto(String firstName, String lastName, String username, String password, String passwordRe,
			String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.passwordRe = passwordRe;
		this.email = email;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRe() {
		return passwordRe;
	}

	public void setPasswordRe(String passwordRe) {
		this.passwordRe = passwordRe;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDto [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", password="
				+ password + ", passwordRe=" + passwordRe + ", email=" + email + "]";
	}

}
