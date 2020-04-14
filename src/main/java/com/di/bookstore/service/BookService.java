package com.di.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Component;

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

	public int updateCart(BookDto notedto, String token, long id);

	public List<BookModel> getAllBooks(String token);

}
