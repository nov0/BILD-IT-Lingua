package org.bildit.lingua.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class UserDto {

	private Long id;
	
	@NotNull
	@Size(min = 2, max = 25)
	private String firstName;
	
	@NotNull
	@Size(min = 2, max = 25)
	private String lastName;

	@NotNull
	@Size(min = 4, max = 25)
	private String username;

	@Size(min = 6, max = 80)
	@NotNull
	private String password;
	
	private String repeatPassword;
	
	@NotNull
	@Email
	@Size(min = 8, max = 30)
	private String email;
	
	private String repeatEmail;
	

	private int authority;
	
	private boolean enabled;
	
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

	public UserDto(Long id, String username, String password, String firstName, String lastName, String email, int authority, boolean enabled) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.authority = authority;
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRepeatEmail() {
		return repeatEmail;
	}

	public void setRepeatEmail(String repeatEmail) {
		this.repeatEmail = repeatEmail;
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



	


}
