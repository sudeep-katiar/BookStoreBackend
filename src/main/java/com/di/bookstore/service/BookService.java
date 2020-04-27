package com.di.bookstore.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.di.bookstore.dto.BookDto;
import com.di.bookstore.model.BookModel;

/**
 * This interface has the UnImplemented functionality of adding book,
 * updating status of book, get all books functionality of the user's
 * book after verifying with the identity.
 * 
 * @author Sudeep Kumar Katiar
 * @created 2020-01-10
 * @version 1.0
 */

@Component
public interface BookService {

	public BookModel addBook(BookDto bookdto, String token);

	public int updateCart(String token, int id);

	public List<BookModel> getAllBooks(String token);

	public List<BookModel> allCartBooks(String token);

	public int updateWishlist(BookDto notedto, String token, int id);

	public List<BookModel> allWishlistBooks(String token);

	public int upload(MultipartFile file, String token, int id) throws IOException;

}
