package com.di.bookstore.dto;

public class BookDto {

	private String bookName;
	private String bookCode;
	private String bookDetails;
	private double price;
	private int quantity;
	private String authorName;
	private boolean addToBag;

	public boolean isAddToBag() {
		return addToBag;
	}

	public void setAddToBag(boolean addToBag) {
		this.addToBag = addToBag;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
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

}
