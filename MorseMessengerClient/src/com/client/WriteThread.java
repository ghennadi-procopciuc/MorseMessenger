package com.client;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteThread extends Thread {
	ObjectOutputStream outStream;
	Object localBuffer;
	Object lock;

	public WriteThread(ObjectOutputStream outStream) {
		super();
		this.outStream = outStream;
		lock = new Object();
	}

	public void run() {
		while (true) {
			synchronized (lock) {
				try {
					System.out.println("[Write Thread] intru in asteptare");
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				try {
					System.out
							.println("[Write Thread] Urmeaza sa transmit un pachet");
					outStream.writeObject(localBuffer);
					System.out.println("[Write Thread] Am transmis un pachet");
					System.out.println("Trecut");
				} catch (IOException e) {
					System.out.println("Exceptie");
					e.printStackTrace();
				}
			}
		}
	}

	public void unblock(Object buffer) {
		System.out.println("[WriteThread] Unblock begins ...");
		synchronized (lock) {
			localBuffer = buffer;
			lock.notify();
		}
	}
}