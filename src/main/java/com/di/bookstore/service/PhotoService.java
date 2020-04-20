package com.di.bookstore.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;
import com.di.bookstore.model.BookPhoto;

@Component
public interface PhotoService {

	BookPhoto storeObjectInS3(MultipartFile file, String originalFilename, String contentType, String token);

	S3Object getProfilePic(MultipartFile file, String token);

}
