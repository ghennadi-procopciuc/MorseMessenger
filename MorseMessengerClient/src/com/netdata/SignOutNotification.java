package com.netdata;

import java.io.Serializable;

public class SignOutNotification implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String friendName;

	public SignOutNotification(String friendName)
	{
		this.friendName = friendName;
	}
	
	public String getFriendName()
	{
		return friendName;
	}

	public void setFriendName(String friendName)
	{
		this.friendName = friendName;
	}
}
