package com.netdata;

import java.util.ArrayList;

public class WorldWindResponse extends WorldWindRequest {

	private static final long serialVersionUID = 1L;
	ArrayList<WorldWindData> list;

	public WorldWindResponse(String username, int type,
			ArrayList<WorldWindData> list) {
		super(username, type);
		this.list = list;
	}

	public ArrayList<WorldWindData> getList() {
		return list;
	}

	public void setList(ArrayList<WorldWindData> list) {
		this.list = list;
	}

}
