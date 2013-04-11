package com.client;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class GMailTool {
	private Properties settings;
	private Session session;
	private Transport transport;
	String mailTo;
	private String mailSubject;
	private String mailContent;
	
	public GMailTool(String mailTo, String username, String password){
		this.mailTo = mailTo;
		mailSubject = "[MorseMessenger] Password Recovery Message";
		mailContent = "Dear " + username + ",\n" + "\n" + 
					  "You can now log in using the following credentials:" + "\n" + "\n" +
					  "User: " + username + "\n" + 
					  "Password: " + password + "\n" + "\n\n\n" + 
					  "Morse Messenger " + "\n" + 
					  "  powered by 4Marmote";
		
		settings = new Properties();
		settings.put("mail.smtp.auth", "true");
        settings.put("mail.smtp.starttls.enable", "true");
        
        session = Session.getInstance(settings);
        
        try {
			transport = session.getTransport("smtp");
		} catch (NoSuchProviderException e) {
			new Error(e.getMessage());
		}
        
        try {
			transport.connect("smtp.gmail.com", 587, "4marmote", "4marmotari");
		} catch (MessagingException e) {
			new Error(e.getMessage());
		}    
	}
	
	public void sendMail(){
		 Message message = new MimeMessage(this.session);
		 try {
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
		} catch (AddressException e) {
			new Error(e.getMessage());
		} catch (MessagingException e) {
			new Error(e.getMessage());
		}
		 
		try {
			message.setSubject(mailSubject);
		} catch (MessagingException e) {
			new Error(e.getMessage());
		}
		
        try {
			message.setContent(mailContent, "text/plain");
		} catch (MessagingException e) {
			new Error(e.getMessage());
		}
        
        try {
			this.transport.sendMessage(message, message.getAllRecipients());
		} catch (MessagingException e) {
			new Error(e.getMessage());
		}
	}

}
