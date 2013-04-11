package com.netdata;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.client.WriteThread;

/*
 * This is gonna be spawned by the requested Client.
 */

public class FileTransferThread extends Thread {

	private WriteThread wThread;
	private TransferProtocol tp;
	private ServerSocket serverSocket;

	public FileTransferThread(TransferProtocol tp, WriteThread wThread) {
		super();

		this.tp = tp;
		this.wThread = wThread;
	}

	public void run() {
		int type = tp.getType();

		switch (type) {
		case TransferProtocol.REQUEST: {
			System.out.println("[ReadThread] Received TransferProtocol:"
					+ tp.getFilename());

			ServerSocket servSocket = null;
			try {
				servSocket = new ServerSocket(0);
			} catch (IOException e) {
				e.printStackTrace();
			}

			tp.setAddress((InetSocketAddress) servSocket
					.getLocalSocketAddress());
			tp.setType(TransferProtocol.ACCEPT);
			String to = tp.getTo();
			tp.setTo(tp.getFrom());
			tp.setFrom(to);

			wThread.unblock(tp);

			Socket socket = null;
			try {
				socket = serverSocket.accept();

				System.out
						.println("[TransferServer] Connection has been established ...");
				InputStream stream = null;

				stream = socket.getInputStream();

				FileOutputStream outputStream = new FileOutputStream(
						tp.getFilename());

				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
						outputStream);

				int chunksize = tp.getFilesize() / 100;

				byte[] mybytearray = new byte[chunksize];

				int readSoFar = 0;
				int progress = 0;
				while (readSoFar < tp.getFilesize()) {
					int bytesRead = 0;
					bytesRead = stream.read(mybytearray, 0, mybytearray.length);
					bufferedOutputStream.write(mybytearray, 0, bytesRead);
					readSoFar += chunksize;

					System.out.println("Progress: " + progress + "%");
					progress += 5;
				}

				bufferedOutputStream.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("[TransferServer] Received file ...");
		}
			break;

		case TransferProtocol.ACCEPT: {
			InetSocketAddress isa = tp.getAddress();

			Socket socket = null;
			try {
				socket = new Socket(isa.getAddress().getHostAddress(),
						isa.getPort());
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

			int chunkSize = tp.getFilesize() / 100;

			System.out
					.println("[TransferClient] Connection has been established ...");

			byte[] mybytearray = new byte[chunkSize];

			OutputStream os;
			try {
				os = socket.getOutputStream();

				BufferedInputStream bis = new BufferedInputStream(
						new FileInputStream(tp.getFilename()));

				int bytesRead = 0;
				int progress = 0;
				while ((bytesRead = bis.read(mybytearray, 0, chunkSize)) != -1) {
					os.write(mybytearray, 0, bytesRead);
					os.flush();

					System.out.println("Progress: " + progress + "%");
					progress += 5;

				}

				bis.close();
				socket.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			System.out.println("[TransferClient] Sent file ...");
		}
			break;

		case TransferProtocol.DENY: {
			System.out.println("File Transfer Denied");
		}
			break;
		}
	}
}
