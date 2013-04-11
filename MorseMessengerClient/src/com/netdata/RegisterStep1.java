package com.netdata;
import java.io.Serializable;


public class RegisterStep1 implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String username, password1, password2, mail;
	
	public RegisterStep1(String username, String password1, String password2,
			String mail) {
		this.username = username;
		this.password1 = password1;
		this.password2 = password2;
		this.mail = mail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}
