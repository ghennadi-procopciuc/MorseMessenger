package com.client;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;


public class LoginDataResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private TreeMap<String, ArrayList<FriendMW>> response;

	public LoginDataResponse(TreeMap<String, ArrayList<FriendMW>> response) {
		super();
		this.response = response;
	}

	public TreeMap<String, ArrayList<FriendMW>> getResponse() {
		return response;
	}

	public void setResponse(TreeMap<String, ArrayList<FriendMW>> response) {
		this.response = response;
	}
	
}
