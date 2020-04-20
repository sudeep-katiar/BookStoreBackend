package com.di.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "photos")
public class BookPhoto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private String photo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "bookId")
	private BookModel bookId;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "userId")
	private UserModel createdBy;

	public BookPhoto(String photo, UserModel user) {
		super();
		this.photo = photo;
		this.createdBy = user;
	}

}
