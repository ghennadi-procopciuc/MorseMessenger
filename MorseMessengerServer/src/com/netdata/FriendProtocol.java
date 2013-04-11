package com.netdata;

import java.io.Serializable;

/**
 * Protocol de adaugare prieteni.
 * 
 * @author 4marmote
 */
public class FriendProtocol implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private FriendMW 	source;
	private FriendMW 	destination;
	private int 		type;
	public static final int REQUEST = -1;
	public static final int ACCEPT = -2;
	public static final int DENY = -3;
	
	public FriendProtocol(FriendMW source, FriendMW destination, int type) {
		super();
		this.source = source;
		this.destination = destination;
		this.type = type;
	}
	
	public FriendMW getSource() {
		return source;
	}
	
	public void setSource(FriendMW source) {
		this.source = source;
	}
	
	public FriendMW getDestination() {
		return destination;
	}
	
	public void setDestination(FriendMW destination) {
		this.destination = destination;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
}
