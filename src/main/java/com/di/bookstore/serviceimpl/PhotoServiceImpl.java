package com.di.bookstore.serviceimpl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.di.bookstore.model.BookPhoto;
import com.di.bookstore.model.UserModel;
import com.di.bookstore.repository.BookRepository;
import com.di.bookstore.repository.PhotoRepository;
import com.di.bookstore.repository.UserRepository;
import com.di.bookstore.service.PhotoService;
import com.di.bookstore.util.Jwt;

import lombok.extern.slf4j.Slf4j;

@Service
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	private PhotoRepository photoRepository;

	@Autowired
	private Jwt jwtGenerator;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookRepository bookRepository;

	@Value("${aws.bucket.name}")
	private String bucketName;

	@Autowired
	private AmazonS3 amazonS3Client;

	@Override
	public BookPhoto storeObjectInS3(MultipartFile file, String fileName, String contentType, String token) {
		try {
			System.out.println(contentType + " " + fileName);
			long userId = jwtGenerator.parseJwtToken(token);
			UserModel user = userRepository.findbyId(userId);
			if (user != null) {
				BookPhoto photo = new BookPhoto(fileName, user);
				ObjectMetadata objectMetadata = new ObjectMetadata();
				objectMetadata.setContentType(contentType);
				objectMetadata.setContentLength(file.getSize());
				System.out.println("1");
				System.out.println(bucketName + " " + fileName + " " + file.getInputStream() + " " + objectMetadata);
				amazonS3Client.putObject(bucketName, fileName, file.getInputStream(), objectMetadata);
				System.out.println("11");
				photoRepository.save(photo);
				return photo;
			}
		} catch (AmazonClientException | IOException exception) {
			throw new RuntimeException("Error while uploading file.");
		}
		return null;
	}

	@Override
	public S3Object getProfilePic(MultipartFile file, String token) {
		try {
			long userId = jwtGenerator.parseJwtToken(token);
			Optional<UserModel> user = userRepository.findById(userId);
			if (user != null) {
				BookPhoto photo = photoRepository.findByUserId(userId);
				if (photo != null) {
					return fetchObject(photo.getPhoto());
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public S3Object fetchObject(String awsFileName) {
		S3Object s3Object;
		try {
			s3Object = amazonS3Client.getObject(new GetObjectRequest(bucketName, awsFileName));
		} catch (AmazonServiceException serviceException) {


			throw new RuntimeException("Error while streaming File.");
		} catch (AmazonClientException exception) {

			throw new RuntimeException("Error while streaming File.");
		}
		return s3Object;
	}

}
