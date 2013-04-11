package com.netdata;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;


public class TransferProtocol implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final int REQUEST = 1, ACCEPT = 2, DENY = 3;
	
	private String from, to;
	private int type;
	
	private int filesize;
	private String filename;
	
	/*
	 * ACCEPT
	 */
	private InetSocketAddress address;

	public TransferProtocol(String from, String to, int type, int filesize,
			String filename, InetSocketAddress address) {
		super();
		
		this.from = from;
		this.to = to;
		this.type = type;
		this.filesize = filesize;
		this.filename = filename;
		this.address = address;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getFilesize() {
		return filesize;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public InetSocketAddress getAddress() {
		return address;
	}

	public void setAddress(InetSocketAddress address) {
		this.address = address;
	}
	
}
