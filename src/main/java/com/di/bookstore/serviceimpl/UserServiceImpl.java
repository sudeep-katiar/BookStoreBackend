package com.di.bookstore.serviceimpl;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.di.bookstore.dto.LoginDto;
import com.di.bookstore.dto.UserDto;
import com.di.bookstore.model.UserModel;
import com.di.bookstore.repository.UserRepository;
import com.di.bookstore.service.UserService;
import com.di.bookstore.util.EmailVerify;
import com.di.bookstore.util.Jwt;

/**
 * This class has the implemented functionality of registering the user and
 * verifying with the identity & login functionality.
 * 
 * @author Sudeep Kumar Katiar
 * @created 11/04/2020
 * @version 1.0
 * @see {@link BCryptPasswordEncoder} for creating encrypted password
 * @see {@link UserRepository} for storing data with the database
 * @see {@link Jwt} fore creation of token
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BCryptPasswordEncoder bcryptpasswordEncoder;

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private EmailVerify mail;

	@Autowired
	private Jwt tokenGenerator;

	/**
	 * This class takes the user inputed data and checks whether the user present in
	 * the database or not if the user is not registered with the database then it
	 * copies all the data from DTO to normal user class and encodes the user
	 * password and save the user with the database and then by using {@link Jwt}
	 * and {@link EmailVerify} it create a token and send the user's mail id for
	 * verification.
	 */
	@Override
	public UserModel register(@Valid UserDto userdto) {
		Date date = new Date();
		UserModel emailavailable = repository.findEmail(userdto.getEmail());
		System.out.println(emailavailable);
		if (emailavailable == null) {
			UserModel userDetails = new UserModel(userdto.getFirstname(), userdto.getLastname(), userdto.getEmail(),
					userdto.getMobile(), userdto.getPassword());
			userDetails.setCreatorStamp(date);
			userDetails.setVerified(false);
			userDetails.setPassword(bcryptpasswordEncoder.encode(userDetails.getPassword()));

			repository.insertdata(date, userdto.getEmail(), userdto.getFirstname(), false, userdto.getLastname(),
					userdto.getMobile(), bcryptpasswordEncoder.encode(userdto.getPassword()), userdto.getUsername());

			UserModel sendMail = repository.findEmail(userdto.getEmail());
			String response = "http://192.168.1.24:8080/users/verify/" + tokenGenerator.createToken(sendMail.getId());
			mail.sendVerifyMail(sendMail.getEmail(), response);

			return userDetails;
		} else {
			return null;
		}
	}

	/**
	 * this function takes login information from user on the basis of input user
	 * email id it fetch all information of the user from database and checks for
	 * Verification details of the user. if the user is verified then it return all
	 * information of user else it proceed with the verification.
	 */
	@Override
	public UserModel login(LoginDto logindto) {
		UserModel usermodel = repository.findEmail(logindto.getEmail());
		if (usermodel.isVerified())
			if (bcryptpasswordEncoder.matches(logindto.getPassword(), usermodel.getPassword())) {
				return usermodel;
			}
		return null;
	}

	/**
	 * This function takes id as long input parameter and checks for user. if user is
	 * found it sends mail to the given mailId for verification. If the user clicks
	 * on the mail then the user gets verified and then the user can login successfully.
	 **/
	@Override
	public UserModel verify(String token) {
		long id = tokenGenerator.parseJwtToken(token);
		UserModel userInfo = repository.findbyId(id);
		if (userInfo != null) {
			if (!userInfo.isVerified()) {
				userInfo.setVerified(true);
				repository.verify(userInfo.getId());
				return userInfo;
			} else {
				return userInfo;
			}
		}
		return null;
	}

}
