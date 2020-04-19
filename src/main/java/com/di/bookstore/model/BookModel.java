package com.di.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Book_Detail")
public class BookModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;

	@Column
	@NotNull
	private String bookName;

	@Column
	private String bookCode;

	@Column(columnDefinition = "varchar(999)")
	@NotNull
	private String bookDetails;

	@Column
	@NotNull
	private double price;

	@Column
	@NotNull
	private int quantity;

	@Column
	@NotNull
	private String authorName;

	@NotNull
	@Column(columnDefinition = "boolean default false")
	private boolean wishlist;

	@NotNull
	@Column(columnDefinition = "boolean default false")
	private boolean addToBag;

	@ManyToOne
	@JoinColumn(name = "userId")
	private UserModel createdBy;
	
//	@Column(name = "picByte", length = 999999999)
//	private byte[] picByte;
//
//	public byte[] getPicByte() {
//		return picByte;
//	}
//
//	public void setPicByte(byte[] picByte) {
//		this.picByte = picByte;
//	}

	public BookModel(@NotNull String bookName, String bookCode, @NotNull String bookDetails, @NotNull double price,
			@NotNull int quantity, @NotNull String authorName) {
		super();
		this.bookName = bookName;
		this.bookCode = bookCode;
		this.bookDetails = bookDetails;
		this.price = price;
		this.quantity = quantity;
		this.authorName = authorName;
	}

	public BookModel() {
		super();
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public String getBookDetails() {
		return bookDetails;
	}

	public void setBookDetails(String bookDetails) {
		this.bookDetails = bookDetails;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public boolean isWishlist() {
		return wishlist;
	}

	public void setWishlist(boolean wishlist) {
		this.wishlist = wishlist;
	}

	public boolean isAddToBag() {
		return addToBag;
	}

	public void setAddToBag(boolean addToBag) {
		this.addToBag = addToBag;
	}

	public UserModel getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserModel createdBy) {
		this.createdBy = createdBy;
	}

}
