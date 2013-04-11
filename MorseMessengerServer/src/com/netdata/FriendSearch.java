package com.netdata;



import java.io.Serializable;

public class FriendSearch implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String username, country, hobby, firstName, lastName, mail;
	
	public FriendSearch(){
	}
	
	public FriendSearch(String username, String country, String hobby,
			String firstName, String lastName, String mail) {
		this.username = username;
		this.country = country;
		this.hobby = hobby;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
