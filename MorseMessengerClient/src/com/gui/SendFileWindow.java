/*
 * Created by JFormDesigner on Sat May 05 19:32:24 EEST 2012
 */

package com.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

import com.client.GUIThread;
import com.netdata.TransferProtocol;

/**
 * @author Ghennadi
 */
public class SendFileWindow extends JFrame implements PropertyChangeListener {

	public JScrollPane scrollPane1;
	private JList list1;
	public JPanel mainPanel;
	private Vector<SendFilePanel> transferPanels;
	private Vector<Task> tasks;
	private GUIThread guiThread;

	public SendFileWindow(GUIThread guiThread) {
		super("File Transfer");
		this.guiThread = guiThread;
		initComponents();
	}

	private void initComponents() {
		mainPanel = new JPanel();
		scrollPane1 = new JScrollPane();
		list1 = new JList();

		transferPanels = new Vector<SendFilePanel>();
		tasks = new Vector<Task>();

		mainPanel.setLayout(new GridLayout(0, 1));
		scrollPane1.setViewportView(mainPanel);

		// ======== this ========
		setAutoRequestFocus(false);
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] {
				15, 0, 11, 0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 15,
				0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				0.0, 1.0, 0.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				0.0, 0.0, 0.0, 1.0E-4 };

		scrollPane1.setPreferredSize(new Dimension(550, 200));
		contentPane.add(scrollPane1, new GridBagConstraints(1, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));
		pack();

		setLocationRelativeTo(getOwner());
	}

	public void addTransfers(TransferProtocol transfer) {
		Task task;
		SendFilePanel panel = new SendFilePanel();

		panel.usernameLabel.setText(transfer.getFrom());

		String filename = transfer.getFilename();
		int index = filename.lastIndexOf('\\');
		panel.pathLabel.setText(filename.substring(index + 1));

		this.setVisible(true);
		System.out.println("[SendFile] Fac fereastra vizibila");
		System.out
				.println("----------------------------------------------------");

		mainPanel.add(panel);
		transferPanels.add(panel);

		task = new Task(panel, transfer, guiThread);
		task.addPropertyChangeListener(this);
		task.execute();

		tasks.add(task);
		scrollPane1.setViewportView(mainPanel);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			for (int i = 0; i < tasks.size(); i++) {
				if (evt.getSource() == tasks.get(i)) {
					int progress = (Integer) evt.getNewValue();
					transferPanels.get(i).progressBar.setValue(progress);
				}
			}
		}
	}

	private class Task extends SwingWorker<Void, Void> {

		private SendFilePanel sendPanel;
		private TransferProtocol transfer;
		private ServerSocket serverSocket;
		private GUIThread guiThreadi;

		public Task(SendFilePanel panel, TransferProtocol transfer,
				GUIThread guiThread) {
			sendPanel = panel;
			this.transfer = transfer;
			this.guiThreadi = guiThread;
		}

		@Override
		public Void doInBackground() {
			int type = transfer.getType();

			switch (type) {
			case TransferProtocol.REQUEST: {
				String filename = "";
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new java.io.File("."));
				fileChooser.setDialogTitle("Receive File");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				fileChooser.setAcceptAllFileFilterUsed(false);

				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					int index = transfer.getFilename().lastIndexOf('\\');
					filename += fileChooser.getSelectedFile() + "\\"
							+ transfer.getFilename().substring(index + 1);

					System.out.println("[SendFile] Emit un pachet de accept");

					serverSocket = null;
					try {
						serverSocket = new ServerSocket(0);
					} catch (IOException e) {
						e.printStackTrace();
					}

					System.out.println("Adresa din SERVER : "
							+ ((InetSocketAddress) serverSocket
									.getLocalSocketAddress()).getAddress()
									.getHostAddress());
					transfer.setAddress((InetSocketAddress) serverSocket
							.getLocalSocketAddress());
					transfer.setType(TransferProtocol.ACCEPT);
					String to = transfer.getTo();
					transfer.setTo(transfer.getFrom());
					transfer.setFrom(to);

					System.out.println("FROM : " + transfer.getFrom()
							+ " TO : " + transfer.getTo() + " Filename : "
							+ transfer.getFilename() + " Filesize : "
							+ transfer.getFilesize() + " Type : "
							+ transfer.getType() + " Address port : "
							+ transfer.getAddress().getPort()
							+ " Address IP : " + transfer.getAddress());

					System.out.println("[SendFile] Urmeaza sa transmit ACCEPT");
					System.out.println(transfer);
					guiThreadi.getwThread().unblock(transfer);
					System.out
							.println("[SendFile] Am trimis un pachet de accept");
				} else {
					return null;
				}

				Socket socket = null;
				try {

					System.out
							.println("[SendFile] Astept ca cineva sa se conecteze la mine");

					System.out.println("Server socket : " + serverSocket);
					socket = serverSocket.accept();

					System.out
							.println("[SendFile] Am gasit pe cineva care s-a conectat");

					System.out.println("Socket = " + socket);
					System.out
							.println("[TransferServer] Connection has been established ...");
					InputStream stream = null;

					stream = socket.getInputStream();

					System.out.println("Check 1");
					FileOutputStream outputStream = new FileOutputStream(
							filename);

					System.out.println("RECEIVE FILE NAME : " + filename);

					System.out.println("Check2");
					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
							outputStream);

					int chunksize = transfer.getFilesize() / 99;

					byte[] mybytearray = new byte[chunksize];

					System.out.println("Check3");
					int transferSoFar = 0;
					while (transferSoFar < transfer.getFilesize()) {

						int bytesRead = 0;
						bytesRead = stream.read(mybytearray, 0,
								mybytearray.length);

						transferSoFar += bytesRead;
						bufferedOutputStream.write(mybytearray, 0, bytesRead);
						bufferedOutputStream.flush();

						sendPanel.progressBar.setValue(new Double(new Double(
								transferSoFar) / transfer.getFilesize() * 100)
								.intValue());
					}

					// bufferedOutputStream.close();
					// socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("[TransferServer] Received file ...");
			}
				break;

			case TransferProtocol.ACCEPT: {

				System.out.println("[SendFile] Am primit accept");

				InetSocketAddress isa = transfer.getAddress();

				Socket socket = null;
				try {
					socket = new Socket("127.0.0.1", isa.getPort());
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}

				int chunkSize = transfer.getFilesize() / 99;

				byte[] mybytearray = new byte[chunkSize];

				System.out
						.println("[TransferClient] Connection has been established ...");

				OutputStream os;
				try {
					os = socket.getOutputStream();
					System.out.println("Check 11");

					BufferedInputStream bis = new BufferedInputStream(
							new FileInputStream(transfer.getFilename()));
					System.out.println("Check 12");

					int bytesRead = 0;
					int progress = 0;

					System.out.println("Check 13");

					int transferSoFar = 0;
					while (transferSoFar < transfer.getFilesize()) {
						bytesRead = bis.read(mybytearray, 0, chunkSize);
						transferSoFar += bytesRead;

						os.write(mybytearray, 0, bytesRead);
						os.flush();

						sendPanel.progressBar.setValue(new Double(new Double(
								transferSoFar) / transfer.getFilesize() * 100)
								.intValue());
					}

					// bis.close();
					// socket.close();
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

			return null;
		}

		@Override
		public void done() {
			sendPanel.stateLabel.setIcon(new ImageIcon(Resources.CHECK_ICON));
			Toolkit.getDefaultToolkit().beep();
		}
	}

	public static void main(String[] args) {
		GUISettings.getSettings();
		SendFileWindow sw = new SendFileWindow(null);
		sw.setVisible(true);
		sw.setDefaultCloseOperation(EXIT_ON_CLOSE);

		/*
		 * for (int i = 0; i < 10; i++) { sw.addTransfers(); }
		 */
	}
}
