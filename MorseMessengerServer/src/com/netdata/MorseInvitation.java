package com.netdata;


import java.io.Serializable;

public class MorseInvitation implements Serializable
{
	public static final int REQUEST = 1, ACCEPT = 2, DENY = 3;
	private static final long serialVersionUID = 1L;
	private String from, to;
	
	public MorseInvitation(String from, String to)
	{
		this.from = from;
		this.to   = to;
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
}