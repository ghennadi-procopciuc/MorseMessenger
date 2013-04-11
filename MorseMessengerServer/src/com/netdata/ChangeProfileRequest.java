package com.netdata;

import java.io.Serializable;
import java.util.ArrayList;

public class ChangeProfileRequest extends ShowProfileResponse implements
		Serializable {
	private static final long serialVersionUID = 1L;
	private String password, newPassword;

	public ChangeProfileRequest(String username, String country, String name,
			String surname, Long birthDate, byte[] avatar,
			ArrayList<String> hobbies, String password, String newPassword) {
		super(username, country, name, surname, birthDate, avatar, hobbies);

		this.password = password;
		this.newPassword = newPassword;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}
}
