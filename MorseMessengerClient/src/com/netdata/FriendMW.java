package com.netdata;

import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * FriendMW is used to store the user information needed in the Main Window.
 * 
 * @author Liviu
 * 
 */
public class FriendMW implements Serializable {

	private static final long serialVersionUID = 1L;
	String username;
	String completeName;
	int status;
	byte[] icon;

	public FriendMW(String username, int status, byte[] icon) {
		this.username = username;
		this.status = status;
		this.icon = icon;
	}

	public FriendMW(String username, String completeName, int status,
			byte[] icon) {
		super();
		this.username = username;
		this.completeName = completeName;
		this.status = status;
		this.icon = icon;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompleteName() {
		return completeName;
	}

	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ImageIcon getIcon() {
		if (icon == null) {
			return null;
		}

		return new ImageIcon(icon);
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}
}
