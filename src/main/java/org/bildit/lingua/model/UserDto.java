package org.bildit.lingua.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

	private long id;

	@Size(min = 2, max = 25)
	private String firstName;

	@Size(min = 2, max = 25)
	private String lastName;

	@Size(min = 4, max = 25)
	private String username;

	@Size(min = 6, max = 80)
	@NotNull
	private String password;
	
	@NotNull
	@Size(min = 6, max = 80)
	private String passwordTransient;
	
	@Size(min = 8, max = 30)
	private String email;
	
	private int authority;
	
	private boolean enabled;
	
	public String getPasswordTransient() {
		return passwordTransient;
	}

	public void setPasswordTransient(String passwordTransient) {
		this.passwordTransient = passwordTransient;
	}

	public UserDto() {

	}

	public UserDto(String username, String password, String firstName, String lastName, String email, int authority, boolean enabled) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.authority = authority;
		this.enabled = enabled;
	}

	public long getId() {
		return id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + "]";
	}

}
