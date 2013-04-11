package com.netdata;

import java.io.Serializable;

public class ArchiveListingRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String friendName;

	public ArchiveListingRequest(String friendName) {
		this.friendName = friendName;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		friendName = friendName;
	}

}
