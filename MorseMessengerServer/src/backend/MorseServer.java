package backend;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Hashtable;

public class MorseServer {
	ServerSocket incomeSocket;
	Socket newConnection;
	int listeningPort;
	
	/**
	 * <Username, Socket> associations to know everyone online
	 * When a user signs out, the table is modified
	 */
	public static Hashtable<String, ObjectOutputStream> onlineUsers;
	
	public MorseServer(int port){
		listeningPort = port;
		onlineUsers = new Hashtable<>();
		
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
				System.out.println("Connected?");
			} catch (IOException e) {
				new Error(e.getMessage());
			}
			
			Worker newWorker = new Worker(newConnection);
			newWorker.start();
			System.out.println("[SERVER] Accepted new connections and assigned a worker.");
		}
	}
	
	public static void main(String[] args) {
		MorseServer server = new MorseServer(Resources.SERVER_PORT);
		server.acceptConnections();
	}
}
