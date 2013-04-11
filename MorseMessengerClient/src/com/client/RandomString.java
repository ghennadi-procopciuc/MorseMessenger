package com.client;
import java.util.Random;

public class RandomString {
	String randomString;
	int length;
	private final String SYMBOLS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private Random generator;
	
	public RandomString(int length){
		this.length = length;
		generator = new Random();
	}
	
	public String getRandomString(){
		String result = "";
		for (int i = 0; i < this.length; i++) {
			result += SYMBOLS.charAt(generator.nextInt(SYMBOLS.length()));
		}
		return result;
	}
}
