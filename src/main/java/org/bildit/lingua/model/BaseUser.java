package org.bildit.lingua.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.bildit.lingua.common.BaseEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Mladen Todorovic
 * @class BaseUser
 * */

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "base_users")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class BaseUser extends BaseEntity {
	
private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size(min = 2, max = 25)
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String firstName;
	@NotBlank
	@Size(min = 2, max = 25)
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String lastName;
	@NotBlank
	@Size(min = 5, max = 25)
	@Pattern(regexp = "^[\\p{L}0-9]*$")
	private String username;
	@NotBlank
	@Size(min = 5, max = 80)
	private String password;
	@NotBlank
	@Size(min = 8, max = 80)
	@Email
	private String email;
	
	@Column(columnDefinition = "BIT", length = 1)
	private boolean enabled;
	private String authority;
	
	public BaseUser() {
		/** Empty default constructor */
	}
	
	/** Constructor with parameters */
	public BaseUser(String username, String password, String firstName, String lastName, String email) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	/** Getters and Setters */
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
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getAuthority() {
		return authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}
