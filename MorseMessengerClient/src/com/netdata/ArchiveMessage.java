package com.netdata;

import java.io.Serializable;

public class ArchiveMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String sender;
	private String receiver;
	/* the date is time from the Epoch */
	private Long date;
	private String message;
	
	public ArchiveMessage(String sender, String receiver, Long date,
			String message) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.date = date;
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}