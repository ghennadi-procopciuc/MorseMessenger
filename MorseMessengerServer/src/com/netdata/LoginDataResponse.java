package com.netdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class LoginDataResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private TreeMap<String, ArrayList<FriendMW>> response;
	private String completeName;
	private String username;
	/* myself */
	private FriendMW self;
	
	public LoginDataResponse(TreeMap<String, ArrayList<FriendMW>> response,
			String completeName, String username, FriendMW self) {
		super();
		this.response = response;
		this.completeName = completeName;
		this.username = username;
		this.self = self;
	}

	public TreeMap<String, ArrayList<FriendMW>> getResponse() {
		return response;
	}

	public void setResponse(TreeMap<String, ArrayList<FriendMW>> response) {
		this.response = response;
	}



	public String getCompleteName() {
		return completeName;
	}



	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public FriendMW getSelf() {
		return self;
	}



	public void setSelf(FriendMW self) {
		this.self = self;
	}



	public String getUserDisplayName() {
		if (completeName != null) {
			if (completeName.trim().length() != 0) {
				return completeName;
			}
		}

		return username;
	}
}
