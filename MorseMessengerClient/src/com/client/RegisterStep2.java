package com.client;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class RegisterStep2 implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String name, surname;
	Long birthDate;
	String location;
	ArrayList<String> hobbies;
	byte[] avatar;
	
	public RegisterStep2(String name, String surname, Long birthDate,
			String location, ArrayList<String> hobbies, byte[] avatar) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.location = location;
		this.hobbies = hobbies;
		this.avatar = avatar;
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

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	
}
