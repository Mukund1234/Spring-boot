/**
 * 
 */
package com.email.emailservice;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;


/**
 * @author mukund shil
 *
 */
@Service
public class DefaultEmailService implements EmailService {
	
	@Autowired
	public JavaMailSender javaMailSender;
	
	/*
	 * @Autowired SimpleMailMessage simpleMailMessage;
	 */
	
	
	/* MimeMailMessage mimeMailMessage; */
	/*
	 * @Autowired MimeMessage mimeMessage;
	 */
	
	/*
	 * @Autowired MimeMessageHelper mimeMessageHelper;
	 */
	@Override
	public void sendSimpleEmail(String toAddress, String subject, String message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(toAddress);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		javaMailSender.send(simpleMailMessage);

	}

	@Override
	public void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment)
			throws MessagingException, FileNotFoundException {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
		mimeMessageHelper.setTo(toAddress);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(message);
		FileSystemResource file= new FileSystemResource(ResourceUtils.getFile(attachment));
		mimeMessageHelper.addAttachment("Purchase", file);
		javaMailSender.send(mimeMessage);
		
	}

}
