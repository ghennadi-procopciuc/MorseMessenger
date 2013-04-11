package com.netdata;

import java.io.Serializable;

public class ShowProfileRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	String username;

	public ShowProfileRequest(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
