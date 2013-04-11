package com.client;
import java.io.IOException;
import java.net.*;

public class MorseServer {
	ServerSocket incomeSocket;
	Socket newConnection;
	int listeningPort;
	
	public MorseServer(int port){
		listeningPort = port;
		
		try {
			incomeSocket = new ServerSocket(listeningPort);
		} catch (IOException e) {
			new Error(e.getMessage());
		}
	}
	
	
	/**
	 * Waits for new connections and spawns associated threads.
	 */
	public void acceptConnections(){
		
		/** 
		 * Waiting for ever and ever ...
		 */
		System.out.println("[SERVER] Gonna wait for connections on port " + listeningPort);
		while(true) {
			try {
				newConnection = incomeSocket.accept();
			} catch (IOException e) {
				new Error(e.getMessage());
			}
			
			Worker newWorker = new Worker(newConnection);
			newWorker.start();
			System.out.println("[SERVER] Accepted new connections and assigned a worker.");
		}
	}
	
	public static void main(String[] args) {
		int port = 2004;
		MorseServer server = new MorseServer(port);
		server.acceptConnections();
	}
}
