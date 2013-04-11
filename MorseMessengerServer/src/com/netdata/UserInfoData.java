package com.netdata;

import java.util.ArrayList;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * The user information is sent when someone searches for friends
 * based on FriendSearch criteria.
 * @author Andrei
 *
 */
public class UserInfoData extends FriendSearch {

	private static final long serialVersionUID = 1L;
	/* SHOULD NOT USE hobby FIELD FROM FriendSearch */
	Long birthDate;
	byte []avatar;
	ArrayList<String> hobbies;
	
	public UserInfoData()
	{
		super();
	}
	
	public UserInfoData(String username, 
						String country,
						String hobby,/*should not be used*/
						String firstName, 
						String lastName, 
						String mail, 
						Long birthDate,
						byte[] avatar, 
						ArrayList<String> hobbies) 
	{
		super(username, country, hobby, firstName, lastName, mail);
		this.birthDate = birthDate;
		this.avatar = avatar;
		this.hobbies = hobbies;
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
	/**
	 * hobby field should not be used in UserInfoData
	 */
	public String getHobby() {
		throw new NotImplementedException();
	}
	public void setHobby(String hobby) {
		throw new NotImplementedException();
	}
	
	public String toString() {
		String result;
		result = this.username + " " + this.firstName + " " + this.lastName + "\n"; 
		result += this.mail + " " + this.country + " " + this.birthDate + "\n";
		result += this.hobbies.toString();
		return result;
	}
}
