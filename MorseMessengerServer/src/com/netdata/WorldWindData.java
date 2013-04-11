package com.netdata;

import java.io.Serializable;

/**
 * Can either be about label OR pin.
 * @author Andrei
 *
 */
public class WorldWindData implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	public static final int PIN = 1;
	public static final int LABEL = 2;
	/* used both for pins and for messages */
	private String username;
	private double latitude;
	private double longitude;
	/* used for labels*/
	private String text;
	
	/* used for pins */
	private int color;
	/* pin or label */
	public Boolean label = false;
	public Boolean pin = false;
	/**
	 * 
	 * @param username
	 * @param latitude
	 * @param longitude
	 * @param value - string for both text AND color. The color is an 
	 * 				  integer, thus it should be send as intVal.toString()
	 * @param choice - WorldWindData.PIN or WorldWindData.LABEL
	 */
	public WorldWindData(String username, 
						 double latitude,
						 double longitude,
						 String value,
						 int choice) {
		super();
		this.username = username;
		this.latitude = latitude;
		this.longitude = longitude;
		switch (choice) {
		case WorldWindData.PIN:
			this.pin = true;
			this.color = Integer.parseInt(value);
			break;
		case WorldWindData.LABEL:
			this.label = true;
			this.text = value;
			break;
		}
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public Boolean getLabel() {
		return label;
	}
	public void setLabel(Boolean label) {
		this.label = label;
	}
	public Boolean getPin() {
		return pin;
	}
	public void setPin(Boolean pin) {
		this.pin = pin;
	}
	public String toString() {
		String s = "";
		s += this.username + " " + this.latitude + " " + this.longitude + " ";
		if (this.label) {
			s += this.text + " ";
		}
		if (this.pin) {
			s += this.color + " ";
		}
		s += "\n";
		return s;
	}
}
