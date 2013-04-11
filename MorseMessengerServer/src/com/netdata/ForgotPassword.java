package com.netdata;
import java.io.Serializable;

public class ForgotPassword implements Serializable{
	private static final long serialVersionUID = 1L;
	String mail;

	public ForgotPassword(String mail) {
		super();
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}	
	
}
