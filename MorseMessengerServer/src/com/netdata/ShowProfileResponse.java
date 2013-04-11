package com.netdata;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowProfileResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	String username;
	String location;
	String name;
	String surname;
	Long birthDate;
	byte[] avatar;
	ArrayList<String> hobbies;

	public ShowProfileResponse(String username, String location, String name,
			String surname, Long birthDate, byte[] avatar,
			ArrayList<String> hobbies) {
		this.username = username;
		this.location = location;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.avatar = avatar;
		this.hobbies = hobbies;
	}

	public ShowProfileResponse(UserInfoData userInfo) {
		this.username = userInfo.getUsername();
		this.location = userInfo.getCountry();
		this.name = userInfo.getFirstName();
		this.surname = userInfo.getLastName();
		this.birthDate = userInfo.getBirthDate();
		this.avatar = userInfo.getAvatar();
		this.hobbies = userInfo.getHobbies();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Long getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Long birthDate) {
		this.birthDate = birthDate;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public ArrayList<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(ArrayList<String> hobbies) {
		this.hobbies = hobbies;
	}
}
