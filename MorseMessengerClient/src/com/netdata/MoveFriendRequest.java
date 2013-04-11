package com.netdata;

import java.io.Serializable;


/**
 * Move a friend to other group.
 * 
 * @author paulvlase
 */
public class MoveFriendRequest implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String friendName;
	private String groupName;
	
	public MoveFriendRequest(String friendName, String groupName)
	{
		this.friendName = friendName;
		this.groupName  = groupName;
	}
	
	public String getFriendName()
	{
		return friendName;
	}
	
	public void setFriendName(String friendName)
	{
		this.friendName = friendName;
	}
	
	public String getGroupName()
	{
		return groupName;
	}
	
	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}
}
