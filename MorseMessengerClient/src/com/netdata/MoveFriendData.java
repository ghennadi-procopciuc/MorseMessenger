package com.netdata;

import java.io.Serializable;


/**
 * Move a friend to other group.
 * 
 * @author paulvlase
 */
public class MoveFriendData implements Serializable {
	private String friendName;
	private String newGroup;
	
	public String getFriendName() {
		return friendName;
	}
	
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
	
	public String getNewGroup() {
		return newGroup;
	}
	
	public void setNewGroup(String newGroup) {
		this.newGroup = newGroup;
	}
}
