package com.di.bookstore.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmailVerify {
	
	@Autowired
	private JavaMailSender mailsender;
	
	public void sendVerifyMail(String email, String token) throws MailException {
//		System.out.println("email" + email);
		log.info("verification mail");
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("sudeepkatiar@gmail.com");
		mail.setTo(email);
		mail.setSubject("verify user");
		mail.setText("click here..." + token);

		mailsender.send(mail);
	}
	
	public void orderMail(String email) throws MailException {
		log.info("order confirmation mail");
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("sudeepkatiar@gmail.com");
		mail.setTo(email);
		mail.setSubject("Order Confirmation Mail");
		mail.setText("Your Order with selected items has been Confirmed."
				+ " The order will be deliverd within 7 to 10 working days."
				+ " Thank you Please come again.");
		
		mailsender.send(mail);
	}

}
