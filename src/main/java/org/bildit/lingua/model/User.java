package org.bildit.lingua.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
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
	
	@Transient
	@NotNull
	@Size(min = 6, max = 80)
	private String passwordTransient;
	
	@Transient
	@AssertTrue
	private boolean passwordMatch;
	
	public boolean isPasswordMatch() {
		this.passwordMatch = true;
		System.out.println(password);
		System.out.println(passwordTransient);
		System.out.println(passwordMatch);
		
		if((password == null || password.isEmpty()) || (passwordTransient == null || passwordTransient.isEmpty())) {
			this.passwordMatch = false;
		} else {
			this.passwordMatch = password.equals(passwordTransient);			
		}
		
		
		return this.passwordMatch = true;
	}

//	public void setPasswordMatch(boolean passwordMatch) {
//		if((password == null || password.isEmpty()) || (passwordTransient == null || passwordTransient.isEmpty())) {
//			this.passwordMatch = false;
//		} else {
//			this.passwordMatch = password.equals(passwordTransient);			
//		}
//	}


	@Size(min = 8, max = 30)
	private String email;
	
	private int authority;
	
	private boolean enabled;
	
	@AssertTrue
	private boolean isValid() {
		return this.password.equals(this.passwordTransient);
	}

	
	
	public String getPasswordTransient() {
		return passwordTransient;
	}

	public void setPasswordTransient(String passwordTransient) {
		this.passwordTransient = passwordTransient;
	}

	public User() {

	}

	public User(String username, String password, String firstName, String lastName, String email, int authority, boolean enabled) {
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
