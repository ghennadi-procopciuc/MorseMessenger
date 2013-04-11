package com.netdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class NewFriendList implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private TreeMap<String, ArrayList<FriendMW>> list;
	
	public NewFriendList(TreeMap<String, ArrayList<FriendMW>> list) {
		super();
		this.list = list;
	}
	public TreeMap<String, ArrayList<FriendMW>> getList() {
		return list;
	}
	public void setList(TreeMap<String, ArrayList<FriendMW>> list) {
		this.list = list;
	}
	
	
	

}
