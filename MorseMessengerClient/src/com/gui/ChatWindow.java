/*
 * Created by JFormDesigner on Mon Apr 30 21:19:07 EEST 2012
 */

package com.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import com.netdata.FriendMW;
import com.netdata.InitiateChat;
import com.netdata.TransferProtocol;

/**
 * @author Ghennadi
 */
public class ChatWindow extends JFrame implements ActionListener {
	private JMenuBar menuBar1;
	private JMenu menu;
	private JMenuItem closeMenuItem;
	private JMenuItem worldWindMenuItem;
	private JCheckBoxMenuItem listenMorseMenuItem;
	private JMenu modeMenu;
	private JCheckBoxMenuItem asciiMenuItem;
	private JCheckBoxMenuItem morseMenuItem;
	private JMenuItem sendFileItem;
	private JPanel panel1;
	private JScrollPane scrollPane1;
	private JEditorPane chatEditorPane;
	private JPanel panel4;
	private JLabel friendAvatarLabel;
	private JLabel userAvatarLabel;
	private JPanel panel2;
	private JScrollPane scrollPane2;
	private JTextArea messageTextArea;
	private JPanel panel3;
	private JButton sendButton;
	private String chatText;
	private MorseCodes translator;
	private Integer communicationMode;
	private MainWindow mainWindow;
	private String username;

	public ChatWindow(String username, MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		this.username = username;
		initComponents();
		setTitle(username);
	}

	private void initComponents() {
		menuBar1 = new JMenuBar();
		menu = new JMenu();
		closeMenuItem = new JMenuItem();
		listenMorseMenuItem = new JCheckBoxMenuItem();
		sendFileItem = new JMenuItem();
		modeMenu = new JMenu();
		asciiMenuItem = new JCheckBoxMenuItem();
		morseMenuItem = new JCheckBoxMenuItem();
		panel1 = new JPanel();
		scrollPane1 = new JScrollPane();
		chatEditorPane = new JEditorPane();
		panel4 = new JPanel();
		friendAvatarLabel = new JLabel();
		userAvatarLabel = new JLabel();
		panel2 = new JPanel();
		scrollPane2 = new JScrollPane();
		messageTextArea = new JTextArea();
		panel3 = new JPanel();
		sendButton = new JButton();
		worldWindMenuItem = new JMenuItem();
		chatText = "";

		translator = new MorseCodes();

		// ======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] {
				15, 452, 13, 0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 15,
				0, 0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				0.0, 1.0, 0.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				0.0, 1.0, 0.0, 0.0, 1.0E-4 };

		// ======== menuBar1 ========
		{

			// ======== menu ========
			{
				menu.setText("Menu");

				// ---- closeMenuItem ----
				closeMenuItem.setText("Close");
				closeMenuItem.addActionListener(this);
				menu.add(closeMenuItem);

				// ---- closeMenuItem ----
				worldWindMenuItem.setText("World Wind");
				worldWindMenuItem.addActionListener(this);
				menu.add(worldWindMenuItem);

				sendFileItem.setText("Send file");
				sendFileItem.addActionListener(this);
				menu.add(sendFileItem);

				listenMorseMenuItem.setText("Listen Morse");
				listenMorseMenuItem.addActionListener(this);
				menu.add(listenMorseMenuItem);
			}
			menuBar1.add(menu);

			// ======== modeMenu ========
			{
				modeMenu.setText("Mode");

				// ---- asciiMenuItem ----
				asciiMenuItem.setText("ASCII");
				asciiMenuItem.setSelected(true);
				asciiMenuItem.addActionListener(this);
				modeMenu.add(asciiMenuItem);

				communicationMode = Resources.ASCII_MODE;

				// ---- morseMenuItem ----
				morseMenuItem.setText("Morse");
				morseMenuItem.addActionListener(this);
				modeMenu.add(morseMenuItem);
			}
			menuBar1.add(modeMenu);
		}
		setJMenuBar(menuBar1);

		// ======== panel1 ========
		{
			panel1.setLayout(new GridBagLayout());
			((GridBagLayout) panel1.getLayout()).columnWidths = new int[] { 47,
					0, 0 };
			((GridBagLayout) panel1.getLayout()).rowHeights = new int[] { 116,
					0 };
			((GridBagLayout) panel1.getLayout()).columnWeights = new double[] {
					1.0, 0.0, 1.0E-4 };
			((GridBagLayout) panel1.getLayout()).rowWeights = new double[] {
					1.0, 1.0E-4 };

			// ======== scrollPane1 ========
			{
				chatEditorPane.setEditable(false);
				chatEditorPane.setContentType("text/html");
				scrollPane1.setViewportView(chatEditorPane);
			}
			panel1.add(scrollPane1, new GridBagConstraints(0, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

			// ======== panel4 ========
			{
				panel4.setLayout(new GridBagLayout());
				((GridBagLayout) panel4.getLayout()).columnWidths = new int[] {
						0, 0 };
				((GridBagLayout) panel4.getLayout()).rowHeights = new int[] {
						0, 0, 0, 0 };
				((GridBagLayout) panel4.getLayout()).columnWeights = new double[] {
						0.0, 1.0E-4 };
				((GridBagLayout) panel4.getLayout()).rowWeights = new double[] {
						0.0, 1.0, 0.0, 1.0E-4 };

				// ---- friendAvatarLabel ----
				friendAvatarLabel.setIcon(new ImageIcon(
						Resources.DEFAULT_BIG_AVATAR_IMAGE));
				friendAvatarLabel
						.setHorizontalTextPosition(SwingConstants.CENTER);
				panel4.add(friendAvatarLabel, new GridBagConstraints(0, 0, 1,
						1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

				// ---- userAvatarLabel ----
				userAvatarLabel.setIcon(new ImageIcon(
						Resources.DEFAULT_BIG_AVATAR_IMAGE));
				panel4.add(userAvatarLabel, new GridBagConstraints(0, 2, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
			panel1.add(panel4, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
					new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));

		// ======== panel2 ========
		{
			panel2.setLayout(new GridBagLayout());
			((GridBagLayout) panel2.getLayout()).columnWidths = new int[] {
					358, 94, 0 };
			((GridBagLayout) panel2.getLayout()).rowHeights = new int[] { 60, 0 };
			((GridBagLayout) panel2.getLayout()).columnWeights = new double[] {
					1.0, 0.0, 1.0E-4 };
			((GridBagLayout) panel2.getLayout()).rowWeights = new double[] {
					1.0, 1.0E-4 };

			// ======== scrollPane2 ========
			{
				addEnterAction();
				addCtrlEnterAction();
				messageTextArea.setLineWrap(true);
				scrollPane2.setViewportView(messageTextArea);
			}
			panel2.add(scrollPane2, new GridBagConstraints(0, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

			// ======== panel3 ========
			{
				panel3.setLayout(new GridBagLayout());
				((GridBagLayout) panel3.getLayout()).columnWidths = new int[] {
						102, 0 };
				((GridBagLayout) panel3.getLayout()).rowHeights = new int[] {
						0, 51, 0, 0 };
				((GridBagLayout) panel3.getLayout()).columnWeights = new double[] {
						1.0, 1.0E-4 };
				((GridBagLayout) panel3.getLayout()).rowWeights = new double[] {
						1.0, 0.0, 1.0, 1.0E-4 };

				// ---- sendButton ----
				sendButton.setText("Send");
				sendButton.addActionListener(this);
				panel3.add(sendButton, new GridBagConstraints(0, 1, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
			}
			panel2.add(panel3, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
					new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));
		setSize(490, 480);
		setLocationRelativeTo(getOwner());

		// ---- buttonGroup1 ----
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(asciiMenuItem);
		buttonGroup1.add(morseMenuItem);
	}

	private String getUserDisplayName(String username) {
		if (mainWindow.getLoginDataResponse().getUsername() == username) {
			return mainWindow.getLoginDataResponse().getCompleteName();
		}

		for (String group : mainWindow.getLoginDataResponse().getResponse()
				.keySet()) {
			if (mainWindow.getLoginDataResponse().getResponse().get(group) != null) {
				for (FriendMW friend : mainWindow.getLoginDataResponse()
						.getResponse().get(group)) {

					if (friend.getUsername().compareTo(username) == 0) {
						return friend.getCompleteName();
					}
				}
			}
		}

		return "";
	}

	/**
	 * Adauga un mesaj la fereastra de chat
	 * 
	 * @param username
	 *            Username-ul celui care posteaza
	 * @param localUser
	 *            Daca cel care posteaza este utilizatorul local
	 * @param message
	 *            Mesajul care va fi afisat
	 */
	public void addMessage(String username, Boolean localUser, String message) {

		/* Elimin \n de la sfarsitul mesajului */
		if (message.endsWith("\n")) {
			message = message.substring(0, message.length() - 1);
		}

		/* Adaug numele utilizatorului in fata mesajului */
		chatText += "<bold><span style=\"color:"
				+ (localUser ? "blue" : "red")
				+ ";font-weight:bold\">"
				+ (getUserDisplayName(username).trim().length() == 0 ? username
						: getUserDisplayName(username))
				+ " : &nbsp </span></bold>";

		/* Daca se comunica in morse => ingros mesajul */
		if (communicationMode == Resources.MORSE_MODE) {
			chatText += "<span style=\"font-size:20px\">";
			chatText += message.replace("\n", "<br>");
			chatText += "</span>";
		} else {
			chatText += message.replace("\n", "<br>");
		}

		/* Adaug o linie noua la mesajul din fereastra */
		chatText += "<br>";
		chatEditorPane.setText("<html>" + chatText + "</html>");

		/* Redau mesajul daca a fost selectata optiunea ListenMorse */
		if (listenMorseMenuItem.isSelected()) {
			if (communicationMode == Resources.ASCII_MODE) {
				new MorseSound(
						translator.toMorse(message.replaceAll("\n", " ")))
						.playSample();
			} else {
				new MorseSound(message.replaceAll("\n", " ")).playSample();
			}
		}
	}

	public void sendMessageAction() {
		String message = messageTextArea.getText();
		String morseMessage;

		if (message.length() == 0 || message.replace("\n", "").length() == 0) {
			messageTextArea.setText("");
			return;
		}

		if (communicationMode == Resources.ASCII_MODE) {
			addMessage(mainWindow.getLoginDataResponse().getUserDisplayName(),
					true, message);
			messageTextArea.setText("");
			mainWindow
					.getGuiThread()
					.getwThread()
					.unblock(
							new InitiateChat(mainWindow.getLoginDataResponse()
									.getUsername(), username, message));
			System.out.println("[CHAT WINDOW] Trimit mesaj pe retea !!!");
		} else {
			morseMessage = translator.toAscii(message);
			if (morseMessage == null) {
				System.out.println("PROST");
				JOptionPane.showMessageDialog(this,
						"You are not morseing enough !!!", "Info",
						JOptionPane.ERROR_MESSAGE, null);
			} else {
				addMessage(mainWindow.getLoginDataResponse()
						.getUserDisplayName(), true, message);
				messageTextArea.setText("");
				mainWindow
						.getGuiThread()
						.getwThread()
						.unblock(
								new InitiateChat(mainWindow
										.getLoginDataResponse().getUsername(),
										username, morseMessage));
			}
		}
	}

	public void closeAction() {
		setVisible(false);
		processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sendButton) {
			sendMessageAction();
		}

		if (e.getSource() == closeMenuItem) {
			closeAction();
		}

		if (e.getSource() == asciiMenuItem) {
			asciiModeAction();
			System.out.println("ASCII MODE");
		}

		if (e.getSource() == morseMenuItem) {
			morseModeAction();
			System.out.println("MORSE MODE");
		}

		if (e.getSource() == listenMorseMenuItem) {
			System.out.println("Listen Morse");
		}

		if (e.getSource() == sendFileItem) {
			sendFileAction();
		}

		if (e.getSource() == worldWindMenuItem) {
			wordlWindAction();
		}
	}

	private void wordlWindAction() {
		// new ZoomFrame(new WorldWindEx());
	}

	private void sendFileAction() {
		System.out.println("Send file Action");

		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setDialogTitle("Send File");

		int status = fileChooser.showOpenDialog(this);

		if (status != JFileChooser.APPROVE_OPTION)
			return;

		System.out.println("Selected file : "
				+ fileChooser.getSelectedFile().getAbsolutePath().toString());

		File file = new File(fileChooser.getSelectedFile().getAbsolutePath()
				.toString());

		// TODO Liviu intervention
		mainWindow.getSendFileWindow().setVisible(true);
		System.out.println("FROM : "
				+ mainWindow.getLoginDataResponse().getUsername() + " TO : "
				+ username);

		TransferProtocol transfer = new TransferProtocol(username, mainWindow
				.getLoginDataResponse().getUsername(),
				TransferProtocol.REQUEST, (int) file.length(),
				file.getAbsolutePath(), null);

		mainWindow.getGuiThread().getwThread().unblock(transfer);
	}

	private void morseModeAction() {
		communicationMode = Resources.MORSE_MODE;
	}

	private void asciiModeAction() {
		communicationMode = Resources.ASCII_MODE;
	}

	private void addEnterAction() {
		// TODO Trimite totul pe retea
		/* La Enter se goleste textul din mesaj */
		messageTextArea.getActionMap().put("Enter",
				new AbstractAction("Enter") {
					public void actionPerformed(ActionEvent evt) {
						sendMessageAction();
						System.out.println("[CHAT WINDOW] Recunosc Enter");
					}
				});
		messageTextArea.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "Enter");
	}

	private void addCtrlEnterAction() {

		// TODO Trimite totul pe retea
		/* La Enter se goleste textul din mesaj */
		messageTextArea.getActionMap().put("ctrlEnter",
				new AbstractAction("ctrlEnter") {
					public void actionPerformed(ActionEvent evt) {
						messageTextArea.setText(messageTextArea.getText()
								+ "\n");
					}
				});
		messageTextArea.getInputMap().put(KeyStroke.getKeyStroke("ctrl ENTER"),
				"ctrlEnter");
	}

	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			if (mainWindow != null) {
				mainWindow.closeChatWindow(username);
				System.out.println("Am intrat");
			}
		}

		super.processWindowEvent(e);
	}

	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

		ChatWindow ch = new ChatWindow("Ghennadi", null);
		ch.setVisible(true);
		ch.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ch.addMessage("Ghennadi Procopciuc1", true,
				"Mesaj da adsd asd asd as dasd asd sd aas ");
		ch.addMessage("Liviu Ioan", false,
				"Mesaj dsa asd asd as das asd as as dasda sds");
	}
}
