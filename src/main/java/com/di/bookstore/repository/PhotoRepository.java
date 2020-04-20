package com.di.bookstore.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.di.bookstore.model.BookPhoto;

@Repository
@Transactional
public interface PhotoRepository extends JpaRepository<BookPhoto, Long> {
	
	@Query(value = "select * from photos where user_id=?", nativeQuery = true)
	BookPhoto findByUserId(long user_id);

}
