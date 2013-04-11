package com.netdata;

import java.util.ArrayList;

public class ViewArchiveResponse extends ViewArchive {

	private static final long serialVersionUID = 1L;
	ArrayList<ArchiveMessage> messages;
	
	public ViewArchiveResponse(String username,
			ArrayList<ArchiveMessage> messages) {
		super(username);
		this.messages = messages;
	}
	
	public ArrayList<ArchiveMessage> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<ArchiveMessage> messages) {
		this.messages = messages;
	}
	
	
}
