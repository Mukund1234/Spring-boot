/**
 * 
 */
package com.email.emailservice;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;

import org.springframework.stereotype.Repository;

/**
 * @author mukund shil
 *
 */
@Repository
public interface EmailService {
	
	void sendSimpleEmail(final String toAddress, final String subject, final String message);
	void sendEmailWithAttachment(final String toAddress, final String subject, final String message, final String attachment) throws MessagingException, FileNotFoundException;
	
}
