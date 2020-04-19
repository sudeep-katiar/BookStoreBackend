package com.di.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.di.bookstore.dto.BookDto;
import com.di.bookstore.model.BookModel;
import com.di.bookstore.responses.Response;
import com.di.bookstore.service.BookService;

import io.swagger.annotations.ApiOperation;

/**
 * book controller
 * 
 * @author : Sudeep Kumar Katiar
 * @date : 13/04/2020
 * @version :1.0
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookservice;

	/*
	 * API to add book
	 */
	@PostMapping("/add")
	@ApiOperation(value = "Api to add book for BookStore", response = Response.class)
	private ResponseEntity<Response> add(@RequestBody BookDto bookdto, @RequestHeader("token") String token)
			throws Exception {

		BookModel book = bookservice.addBook(bookdto, token);
		
		return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Note is created successfully", book));

	}
	
//	@PostMapping("/upload")
//	public ResponseEntity<Response> uploadImage(@RequestParam("imageFile") MultipartFile file, @RequestHeader("token") String token) throws IOException {
//		
//		int book = bookservice.upload(file, token);
//		if(book == 1) {
//			return ResponseEntity.status(HttpStatus.OK).body(new Response("Image is successfully uploaded", 200));
//		}
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Image is not uploaded", 400));
//	}

	/*
	 * API to add book to cart
	 */
	@PostMapping("/addToBag/{id}")
	@ApiOperation(value = "Api to add a book to cart for BookStore", response = Response.class)
	public ResponseEntity<Response> addcart(@RequestBody BookDto notedto, @RequestHeader("token") String token,
			@PathVariable("id") long id) {
		int result = bookservice.updateCart(notedto, token, id);
		if (result == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(new Response("successfully removed from cart", 200));
		} else if (result == 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new Response("successfully added to cart", 200));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Something went wrong", 400));
		}
	}

	/*
	 * API to get all books
	 */
	@PostMapping("/allbooks")
	@ApiOperation(value = "Api to get all book for BookStore", response = Response.class)
	public ResponseEntity<Response> getAllBooks(@RequestHeader("token") String token) {
		List<BookModel> notesList = bookservice.getAllBooks(token);
		return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "all books of user", notesList));
	}

	/*
	 * API to get all books from cart
	 */
	@PostMapping("/cartbooks")
	@ApiOperation(value = "Api to get all book from cart for BookStore", response = Response.class)
	public ResponseEntity<Response> getAllCartBooks(@RequestHeader("token") String token) {
		List<BookModel> notesList = bookservice.allCartBooks(token);
		return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "all books of user", notesList));
	}

	/*
	 * API to add book to wishlist
	 */
	@PostMapping("/wishlist/{id}")
	@ApiOperation(value = "Api to add a book to wishlist for BookStore", response = Response.class)
	public ResponseEntity<Response> wishlist(@RequestBody BookDto notedto, @RequestHeader("token") String token,
			@PathVariable("id") long id) {
		int result = bookservice.updateWishlist(notedto, token, id);
		if (result == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(new Response("successfully removed from cart", 200));
		} else if (result == 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new Response("successfully added to cart", 200));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Something went wrong", 400));
		}
	}

	/*
	 * API to get all books from wishlist
	 */
	@PostMapping("/wishlistbooks")
	@ApiOperation(value = "Api to get all book from wishlist for BookStore", response = Response.class)
	public ResponseEntity<Response> getAllWishlistBooks(@RequestHeader("token") String token) {
		List<BookModel> notesList = bookservice.allWishlistBooks(token);
		return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "all books of user", notesList));
	}

}
