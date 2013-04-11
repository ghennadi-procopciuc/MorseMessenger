package com.netdata;

import java.io.Serializable;

public class DeleteFriendRequest implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String friendName;
	
	public DeleteFriendRequest(String friendName)
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

