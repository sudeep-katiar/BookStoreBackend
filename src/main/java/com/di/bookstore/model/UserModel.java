package com.di.bookstore.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class UserModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String firstname;

	@NotNull
	private String lastname;

	@NotNull
	@Column(unique = true)
	private String username;

	@NotNull
	private String password;

	@NotNull
	private String mobile;

	@NotNull
	@Column(unique = true)
	private String email;

	@NotNull
	@Column(columnDefinition = "boolean default false")
	private boolean isVerified;

	private Date creatorStamp;
	
	public UserModel() {
	}

	public UserModel(long id, @NotNull String firstname, @NotNull String lastname, @NotNull String username,
			@NotNull String password, @NotNull String mobile, @NotNull String email, @NotNull boolean isVerified,
			Date creatorStamp) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.mobile = mobile;
		this.email = email;
		this.isVerified = isVerified;
		this.creatorStamp = creatorStamp;
	}

	public UserModel(String firstName, String lastName, String email, String mobile, String password) {
		super();
		this.firstname = firstName;
		this.lastname = lastName;
		this.password = password;
		this.mobile = mobile;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public Date getCreatorStamp() {
		return creatorStamp;
	}

	public void setCreatorStamp(Date creatorStamp) {
		this.creatorStamp = creatorStamp;
	}

}
