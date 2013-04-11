package com.netdata;

import java.io.Serializable;

public class WorldWindRequest implements Serializable {

	String username;
	int type;
	
	public WorldWindRequest(String username, int type) {
		super();
		this.username = username;
		this.type = type;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	
}
