package com.netdata;

import java.io.Serializable;
import java.util.ArrayList;

public class ProfileOptions implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String username;
	String name, surname;
	Long birthDate;
	String location;
	ArrayList<String> hobbies;
	byte[] array;

	public ProfileOptions(String username, String name, String surname,
			Long birthDate, String location, ArrayList<String> hobbies,
			byte[] array) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.location = location;
		this.hobbies = hobbies;
		this.array = array;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ArrayList<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(ArrayList<String> hobbies) {
		this.hobbies = hobbies;
	}

	public byte[] getArray() {
		return array;
	}

	public void setArray(byte[] array) {
		this.array = array;
	}
}
