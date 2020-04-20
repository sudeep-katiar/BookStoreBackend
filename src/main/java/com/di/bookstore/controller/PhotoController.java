package com.di.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;
import com.di.bookstore.model.BookPhoto;
import com.di.bookstore.responses.Response;
import com.di.bookstore.service.PhotoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/photo")
public class PhotoController {
	
	@Autowired
	private PhotoService photoService;
	
	/*
	 * API to upload photo
	 */
	@PostMapping("/uploadpic")
	@ApiOperation(value = "Api to upload pic of Book for bookstore project", response = Response.class)
	public ResponseEntity<Response> addPic(@ModelAttribute MultipartFile file,
			@RequestHeader("token") String token){

		BookPhoto photo = photoService.storeObjectInS3(file, file.getOriginalFilename(), file.getContentType(),
				token);
		return photo.getCreatedBy() != null
				? ResponseEntity.status(HttpStatus.OK).body(new Response( 200, "profile added successfully", photo))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("something went Wrong ", 400));
	}
	
	/*
	 * API to get photo
	 */
	@GetMapping("/getPic")
	@ApiOperation(value = "Api to get Pic",response = Response.class)
	public ResponseEntity<Response> getProfilePic(@ModelAttribute MultipartFile file,@RequestHeader("token")String token){
		S3Object result=photoService.getProfilePic(file,token);
		return result!=null 
				? ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("All ProfilePic are",200))
				:ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Something went wrong!!!",400));
	}

}
