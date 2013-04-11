package com.client;
import java.io.Serializable;
import javax.swing.ImageIcon;


/**
 * FriendMW is used to store the user information needed in the Main Window.
 * @author Liviu
 *
 */
public class FriendMW implements Serializable {
	
	private static final long serialVersionUID = 1L;
	String username;
	int status;
	ImageIcon icon;
	
	public FriendMW(String username, int status, ImageIcon icon) {
		super();
		this.username = username;
		this.status = status;
		this.icon = icon;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	
}
