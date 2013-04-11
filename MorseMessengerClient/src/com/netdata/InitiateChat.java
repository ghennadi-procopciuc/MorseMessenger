package com.netdata;

import java.io.Serializable;

public class InitiateChat implements Serializable {

	private static final long serialVersionUID = 1L;
	String from, to;
	String data;

	public InitiateChat(String from, String to, String data) {
		super();
		this.from = from;
		this.to = to;
		this.data = data;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
