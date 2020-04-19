package com.di.bookstore.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.di.bookstore.model.BookModel;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<BookModel, Long> {

	@Query(value = "select * from book_detail where book_id = :id", nativeQuery = true)
	BookModel findById(long id);

	@Modifying
	@Query(value = "insert into book_detail (book_name, book_code, book_details, price, quantity, author_name) values (:bookName, :bookCode, :bookDetails, :price, :quantity, :authorName)", nativeQuery = true)
	void insertData(String bookName, String bookCode, String bookDetails, double price, int quantity,
			String authorName);

	@Modifying
	@Query(value = "update book_detail set add_to_bag = :b where user_id = :userid AND id = :id", nativeQuery = true)
	void bag(boolean b, long userid, long id);

	@Query(value = "select * from book_detail where user_id = :userId", nativeQuery = true)
	List<BookModel> getAllForAdmin(long userId);

	@Query(value = "select * from book_detail where user_id = :userId and add_to_bag = true", nativeQuery = true)
	List<BookModel> getCartBooks(long userId);

	@Modifying
	@Query(value = "update book_detail set wishlist = :b where user_id = :userid AND id = :id", nativeQuery = true)
	void setWishlist(boolean b, long userid, long id);

	@Query(value = "select * from book_detail where user_id = :userId and wishlist = true", nativeQuery = true)
	List<BookModel> getWishlistBooks(long userId);

}
