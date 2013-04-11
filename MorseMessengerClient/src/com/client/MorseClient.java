package com.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.gui.GUISettings;

/*
 * @author Liviu
 */

public class MorseClient {
	private Socket connection;
	ObjectOutputStream outStream;
	ObjectInputStream inStream;
	ReadThread rThread;
	WriteThread wThread;
	GUIThread gThread;

	String machine;
	int port;

	public MorseClient(String machine, int port) {
		this.machine = machine;
		this.port = port;

		initClient();

		wThread = new WriteThread(outStream);
		gThread = new GUIThread(wThread, this);
		rThread = new ReadThread(inStream, gThread);

		wThread.start();
		rThread.start();
	}

	public void initClient() {
		if (connection != null) {
			return;
		}

		try {
			connection = new Socket(machine, port);
		} catch (UnknownHostException e) {
			new Error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			new Error(e.getMessage());
		}

		try {
			System.out.println("Inainte de output");
			outStream = new ObjectOutputStream(connection.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			new Error(e.getMessage());
		}

		try {
			outStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
			new Error(e.getMessage());
		}

		try {
			System.out.println("Obtin input stream");

			inStream = new ObjectInputStream(connection.getInputStream());
			System.out.println("Dupa input stream");
		} catch (IOException e) {
			e.printStackTrace();
			new Error(e.getMessage());
		}
	}

	public static void main(String[] args) {
		GUISettings.getSettings();

		MorseClient client = new MorseClient(Resources.SERVER_IP,
				Resources.SERVER_PORT);
	}
}