package com.client;
import java.io.Serializable;

public class LoginData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String username, password;
	
	public LoginData(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
