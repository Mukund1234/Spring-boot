package com.email.controller;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.emailservice.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);
	
	@Autowired
	EmailService emailService;
	
	@GetMapping(value = "/simple-email/{user-email}")
	public @ResponseBody
	ResponseEntity<String> sendSimpleEmail(@PathVariable("user-email") String email){
		try {
			emailService.sendSimpleEmail(email, "Welcome", "This is a welcome email for you!!!");
			
		}catch(MailException e) {
			//LOG.error("Error while sending out email..{}", e.getStackTrace());
			LOG.error("Error while sending out email..{}", e.fillInStackTrace());
			return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Please Check Your Inbox", HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/simple-order-email/{user-email}")
	public @ResponseBody
	ResponseEntity<String> sendEmailAttachment(@PathVariable("user-email") String email){
		
		try {
			emailService.sendEmailWithAttachment(email, "Order confirmtion", "Thanks for your recent order", "classpath:purchase_order.pdf");
		} catch(MessagingException | FileNotFoundException e) {
			//LOG.error("Error while sending out email..{}", e.getStackTrace());
			LOG.error("Error while sending out email..{}", e.fillInStackTrace());
			return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Please Check Your Inbox", HttpStatus.OK);
		
		
	}
}
