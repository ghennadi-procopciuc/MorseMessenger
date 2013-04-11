package com.client;
import java.net.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.io.*;

public class Worker extends Thread{
	Socket connection;
	ObjectOutputStream outStream;
	ObjectInputStream inStream;
	RandomString generator;
	private final int PASSWD_LENGTH = 10;
	
	/*
	 * This thread is responsible for a specific user.
	 * username - set when Login is confirmed
	 */
	private String username;
	
	public Worker(Socket connection){
		this.connection = connection;
		generator = new RandomString(PASSWD_LENGTH);
		
		try {
			outStream = new ObjectOutputStream(this.connection.getOutputStream());
			outStream.flush();
		} catch (IOException e) {
			new Error(e.getMessage());
		}
		
		try {
			inStream = new ObjectInputStream(this.connection.getInputStream());
		} catch (IOException e) {
			new Error(e.getMessage());
		}
	}
	
	public void run() {
		Object receivedData = null;
		
		while(true) {
			
			try {
				synchronized(inStream){
					receivedData = inStream.readObject();	
				}
			} catch (ClassNotFoundException e) {
				new Error(e.getMessage());
			} catch (IOException e) {
				new Error(e.getMessage());
			} 
		
			System.out.println("[SERVER] Received stuff");
			
			/**
			 * Now, I have to know the type of the received data ...
			 */
			if(receivedData instanceof RegisterStep1){
				/*
				 * If user and password are valid, send boolean true
				 * and wait for a RegisterStep2 content.
				 */
				RegisterStep1Response resp = new RegisterStep1Response(false);
				System.out.println("[SERVER] RegisterStep1: " + ((RegisterStep1)receivedData).getUsername());
				
				/**
				 * TODO: check username & send boolean accordingly
				 */
				resp.setValid(true);
				try {
					outStream.writeObject(resp);
				} catch (IOException e) {
					new Error(e.getMessage());
				}
			
				if(resp.getValid()) {	
					/*
					 * 	valid = true, so wait for RegisterStep2 content
					 */
					RegisterStep2 step2 = null;
					try {
						step2 = (RegisterStep2) inStream.readObject();
					} catch (ClassNotFoundException e) {
						new Error(e.getMessage());
					} catch (IOException e) {
						new Error(e.getMessage());
					}
					System.out.println("[SERVER] RegisterStep2: " + step2.name + " " + step2.surname);
				}
			}
			
			if(receivedData instanceof LoginData){
				/**
				 * A user tries to log in.
				 * I have to send him back the Main Window data.
				 * TreeMap<groupname, users>
				 * If the LoginData is invalid, send NULL.
				 * this.username is set 
				 */
				this.username = ((LoginData) receivedData).getUsername();
				
				System.out.println("[SERVER] LoginData: " + ((LoginData) receivedData).getUsername());
				ArrayList<FriendMW> group1  = new ArrayList<>();
				group1.add(new FriendMW("john", 1, null));
				group1.add(new FriendMW("ken", 1, null));
			
				ArrayList<FriendMW> group2  = new ArrayList<>();
				group2.add(new FriendMW("john2", 1, null));
				group2.add(new FriendMW("ken2", 1, null));
			
				TreeMap<String, ArrayList<FriendMW>> map = new TreeMap<>();
				map.put("schmenozauri", group1);
				map.put("marmotari", group2);
			
				LoginDataResponse ldr = new LoginDataResponse(map);
				try {
					outStream.writeObject(ldr);
				} catch (IOException e) {
					new Error(e.getMessage());
				}
			}
			
			if(receivedData instanceof ForgotPassword) {
				/**
				 * Gonna send mail: user + newpass
				 * newpass is random
				 */
				
				/**
				 * TODO: get mail from database for this.username
				 */
				System.out.println("[SERVER] Received ForgotPassword");
				String mail = ((ForgotPassword)receivedData).getMail();
				String password = generator.getRandomString();
				GMailTool mailTool = new GMailTool(mail, this.username, password);
				mailTool.sendMail();
			}
			
			
			if(receivedData instanceof FriendSearch){
				System.out.println("[SERVER] Received FriendSearch");
				
				/*
				 * Gonna send a list of results, i.e. ArrayList<FriendSearch>
				 */
			}
			
			
		}
	}
}
