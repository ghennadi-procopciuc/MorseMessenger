package com.client;

import java.io.IOException;
import java.io.ObjectInputStream;

import com.netdata.FriendSearchResponse;
import com.netdata.InitiateChat;
import com.netdata.LoginDataResponse;
import com.netdata.NewFriend;
import com.netdata.RegisterStep1Response;
import com.netdata.ShowProfileResponse;
import com.netdata.SignInNotification;
import com.netdata.SignOutNotification;
import com.netdata.TransferProtocol;

/**
 * Thread will wait for incoming messages from Server(associated Worker).
 * 
 * @author Liviu
 * 
 */

public class ReadThread extends Thread {

	ObjectInputStream inStream;
	GUIThread guiThread;

	public ReadThread(ObjectInputStream inStream, GUIThread guiThread) {
		super();
		this.inStream = inStream;
		this.guiThread = guiThread;
	}

	public void run() {

		while (true) {
			Object receivedData = null;
			try {
				synchronized (inStream) {
					System.out.println("[ReadThread] Astept sa primesc ceva");
					receivedData = inStream.readObject();
					System.out.println("[ReadThread] Received");
				}
			} catch (ClassNotFoundException e) {
				System.out.println("Eroare 1");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Eroare 2");
				e.printStackTrace();
			}

			System.out.println("[ReadThread] Received stuff");
			System.out.println(receivedData.getClass());
			/*
			 * Received RegisterStep1 response
			 */
			if (receivedData instanceof com.netdata.RegisterStep1Response) {
				System.out
						.println("[ReadThread] Received RegisterStep1Response:"
								+ ((RegisterStep1Response) receivedData)
										.getValid());

				/*
				 * Send response to GUI
				 */
				guiThread.step1Response((RegisterStep1Response) receivedData);
			}

			/*
			 * Received LoginData response
			 */
			if (receivedData instanceof LoginDataResponse) {
				System.out.println("[ReadThread] Received LoginDataResponse:"
						+ ((LoginDataResponse) receivedData).getResponse());

				/*
				 * Send response to GUI
				 */
				if (((LoginDataResponse) receivedData).getResponse() == null) {
					guiThread.getLoginWindow().setError(
							"Wrong username or password");
					System.out.println("Here");
				} else {
					System.out.println("Ma loghez");
					guiThread.loginResponse((LoginDataResponse) receivedData);
				}
			}

			if (receivedData instanceof FriendSearchResponse) {
				guiThread.getMainWindow().setFriendSearchResponse(
						(FriendSearchResponse) receivedData);
			}

			if (receivedData instanceof InitiateChat) {
				guiThread.getMainWindow().postMessage(
						(InitiateChat) receivedData);
			}

			if (receivedData instanceof TransferProtocol) {
				TransferProtocol transfer = (TransferProtocol) receivedData;

				System.out
						.println("[ReadThread] Am primit un fiser pentru transfer");
				if (transfer.getType() != TransferProtocol.DENY) {

					System.out.println("[ReadThread] Tipul pachetului : "
							+ transfer.getType());
					guiThread.getMainWindow().getSendFileWindow()
							.addTransfers((TransferProtocol) receivedData);
				}
			}

			if (receivedData instanceof NewFriend) {
				System.out.println("New friend");
				guiThread.getMainWindow().addNewFriend(
						((NewFriend) receivedData).getFriendMW());
			}

			if (receivedData instanceof ShowProfileResponse) {
				System.out
						.println("[ReadThread] Am primit un raspuns de la Profile");
				System.out.println(((ShowProfileResponse) receivedData)
						.getUsername());
				guiThread.getMainWindow().getProfileWindow()
						.setData((ShowProfileResponse) receivedData);
			}

			if (receivedData instanceof SignOutNotification) {
				System.out.println("[ReadThread] User sign-out");
				guiThread.getMainWindow().signOut(
						((SignOutNotification) receivedData).getFriendName());
			}

			if (receivedData instanceof SignInNotification) {
				System.out.println("[ReadThread] User sign-in");
				SignInNotification signInNotification = (SignInNotification) receivedData;
				System.out.println("Group : "
						+ signInNotification.getGroupname());
				System.out.println("User : "
						+ signInNotification.getUserMW().getUsername());

				guiThread.getMainWindow().signIn(
						(SignInNotification) receivedData);

				System.out.println("After signIN");
			}
		}
	}
}
