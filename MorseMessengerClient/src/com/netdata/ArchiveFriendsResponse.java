package com.netdata;

import java.io.Serializable;
import java.util.ArrayList;

public class ArchiveFriendsResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<String> friendsFullName;;
	private ArrayList<String> friendsName;

	// private ArrayList<ImageIcon>

	public ArchiveFriendsResponse() {
		this.friendsName = new ArrayList<String>();
		this.friendsFullName = new ArrayList<String>();
	}

	public ArrayList<String> getFriendsName() {
		return friendsName;
	}

	public void setFriendsName(ArrayList<String> friendsName) {
		this.friendsName = friendsName;
	}

	public ArrayList<String> getFriendsFullName() {
		return friendsFullName;
	}

	public void setFriendsFullName(ArrayList<String> friendsFullName) {
		this.friendsFullName = friendsFullName;
	}
}
