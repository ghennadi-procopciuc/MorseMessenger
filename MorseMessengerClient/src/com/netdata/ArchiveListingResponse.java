package com.netdata;

import java.io.Serializable;
import java.util.ArrayList;

public class ArchiveListingResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	ArrayList<ArchiveMessage> messages;

	public ArchiveListingResponse(ArrayList<ArchiveMessage> messages) {
		this.messages = messages;
	}

	public ArrayList<ArchiveMessage> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<ArchiveMessage> messages) {
		this.messages = messages;
	}
}
