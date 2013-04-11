package com.netdata;

import java.io.Serializable;

public class DeleteGroupRequest implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String groupName;
	
	public DeleteGroupRequest(String groupName)
	{
		this.groupName = groupName;
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
