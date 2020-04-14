package com.di.bookstore.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.di.bookstore.dto.BookDto;
import com.di.bookstore.model.BookModel;
import com.di.bookstore.model.UserModel;
import com.di.bookstore.repository.BookRepository;
import com.di.bookstore.repository.UserRepository;
import com.di.bookstore.service.BookService;
import com.di.bookstore.util.Jwt;

/**
 * This class implements {@link BookService} interface which has the
 * unimplemented functionality of adding a book, updating, deleting and
 * getallbooks. All operations will be carried out if the user is a valid user.
 * 
 * @author Sudeep Kumar Katiar
 * @created 13/4/2020
 * @version 1.0
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private Jwt tokenGenerator;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

	/**
	 * This function takes {@link BookDto} as input parameter and token as path
	 * variable. Using token it authorize the user if the user is verified then all
	 * data of bookDto is copied to the BookModel class and is saved then the user
	 * book information is saved in the database. After successful saving of note it
	 * return book data.
	 */
	@Override
	public BookModel addBook(BookDto bookdto, String token) {
		long id = tokenGenerator.parseJwtToken(token);
		UserModel user = userRepository.findbyId(id);
		if (user != null) {
			BookModel book = new BookModel(bookdto.getBookName(), bookdto.getBookCode(), bookdto.getBookDetails(),
					bookdto.getPrice(), bookdto.getQuantity(), bookdto.getAuthorName());
			book.setAddToBag(false);
			book.setCreatedBy(user);
			bookRepository.insertData(book.getBookName(), book.getBookCode(), book.getBookDetails(), book.getPrice(),
					book.getQuantity(), book.getAuthorName());
			return book;
		}
		return null;
	}

	@Override
	public int updateCart(BookDto notedto, String token, long id) {
		long userid = tokenGenerator.parseJwtToken(token);
		Optional<UserModel> user = userRepository.findById(userid);
		if (user != null) {
			BookModel book = bookRepository.findById(id);
			if (book.isAddToBag()) {
				bookRepository.bag(false, userid, id);
				return 1;
			} else {
				bookRepository.bag(true, userid, id);
				return 0;
			}

		}
		return -1;
	}

	/**
	 * This function takes note id and authorized token from the user checks for
	 * user authorization if valid customer then find all the available books which
	 * are not trashed from database and return it.
	 */
	@Override
	public List<BookModel> getAllBooks(String token) {
		long userId = tokenGenerator.parseJwtToken(token);
		Object isUserAvailable = userRepository.findById(userId);
		if (isUserAvailable != null) {
			List<BookModel> books = bookRepository.getAllForUser(userId);
			return books;
		}
		return null;
	}

}
