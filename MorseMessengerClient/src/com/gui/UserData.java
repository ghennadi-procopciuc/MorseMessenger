package com.gui;

public class UserData {
	String username;
	String completeName;

	public UserData(String username, String completeName) {
		this.username = username;
		this.completeName = completeName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompleteName() {
		return completeName;
	}

	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}
}
