package com.netdata;

import java.io.Serializable;

public class ViewArchive implements Serializable {
	
	private static final long serialVersionUID = 1L;
	String username;

	public ViewArchive(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
