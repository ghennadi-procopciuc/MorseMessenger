package com.netdata;

import java.io.Serializable;

public class SignInNotification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FriendMW userMW;
	private String groupname;
	public SignInNotification(FriendMW userMW, String groupname) {
		super();
		this.userMW = userMW;
		this.groupname = groupname;
	}
	public FriendMW getUserMW() {
		return userMW;
	}
	public void setUserMW(FriendMW userMW) {
		this.userMW = userMW;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	
	
}
