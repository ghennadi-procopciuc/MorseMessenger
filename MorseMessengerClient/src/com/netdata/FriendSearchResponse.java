package com.netdata;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * @author Liviu
 */
public class FriendSearchResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	ArrayList<UserInfoData> list;

	public FriendSearchResponse(ArrayList<UserInfoData> list) {
		super();
		this.list = list;
	}

	public ArrayList<UserInfoData> getList() {
		return list;
	}

	public void setList(ArrayList<UserInfoData> list) {
		this.list = list;
	}

}
