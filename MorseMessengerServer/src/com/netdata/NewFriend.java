package com.netdata;

import java.io.Serializable;

public class NewFriend implements Serializable{
	
	FriendMW friendMW;

	public NewFriend(FriendMW friendMW) {
		super();
		this.friendMW = friendMW;
	}

	public FriendMW getFriendMW() {
		return friendMW;
	}

	public void setFriendMW(FriendMW friendMW) {
		this.friendMW = friendMW;
	}
}
