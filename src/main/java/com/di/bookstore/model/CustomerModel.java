package com.di.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customer")
public class CustomerModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	@NotNull
	private String name;
	
	@Column
	@NotNull
	private String email;

	@Column
	@NotNull
	private long phone;

	@Column
	@NotNull
	private long pincode;

	@Column
	@NotNull
	private String locality;

	@Column(columnDefinition = "varchar(999)")
	@NotNull
	private String address;

	@Column
	@NotNull
	private String city;

	@Column
	private String landmark;

	public CustomerModel(@NotNull String name, @NotNull String email, @NotNull long phone, @NotNull long pincode, @NotNull String locality,
			@NotNull String address, @NotNull String city, String landmark) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.pincode = pincode;
		this.locality = locality;
		this.address = address;
		this.city = city;
		this.landmark = landmark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public long getPincode() {
		return pincode;
	}

	public void setPincode(long pincode) {
		this.pincode = pincode;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

}
