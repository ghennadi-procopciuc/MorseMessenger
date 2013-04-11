/*
 * Created by JFormDesigner on Thu Apr 12 01:33:37 EEST 2012
 */

package com.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.client.GUIThread;
import com.netdata.ForgotPassword;
import com.netdata.LoginData;

/**
 * @author Ghennadi
 */
public class LoginWindow extends JFrame implements ActionListener {
	private GUIThread guiThread;

	private JPanel mainPanel;
	private JMenuBar menuBar1;
	private JMenu menu;
	private JMenuItem newAccountMenuItem;
	private JMenuItem forgotPasswordMenuItem;
	private JMenuItem signInMenuItem;
	private JMenuItem quitMenuItem;
	private JLabel morseLabel;
	private JPanel panel2;
	private JPanel panel4;
	private JLabel userLabel;
	private JTextField usernameField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JLabel wrongDataLabel;
	private JPanel panel3;
	private JButton signInButton;
	private JProgressBar progressBar;

	private JPanel forgotPasswordPanel;
	private JLabel emailLabel;
	private JTextField emailField;
	private JLabel wrongEmailLabel;
	private JButton recoverButton;
	private JPanel recoverPanel;
	private JPanel forgotPanel1;

	public LoginWindow(GUIThread guiThread) {
		super("Login");
		this.guiThread = guiThread;
		initComponents();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void initComponents() {
		mainPanel = new JPanel();
		menuBar1 = new JMenuBar();
		menu = new JMenu();
		newAccountMenuItem = new JMenuItem();
		forgotPasswordMenuItem = new JMenuItem();
		signInMenuItem = new JMenuItem();
		quitMenuItem = new JMenuItem();
		morseLabel = new JLabel();
		panel2 = new JPanel();
		panel4 = new JPanel();
		userLabel = new JLabel();
		usernameField = new JTextField();
		passwordLabel = new JLabel();
		passwordField = new JPasswordField();
		wrongDataLabel = new JLabel();
		panel3 = new JPanel();
		signInButton = new JButton();
		progressBar = new JProgressBar();

		forgotPasswordPanel = new JPanel();
		emailLabel = new JLabel();
		emailField = new JTextField();
		wrongEmailLabel = new JLabel();
		recoverButton = new JButton();
		recoverPanel = new JPanel();
		forgotPanel1 = new JPanel();

		// ======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] { 0,
				0, 0, 0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 0,
				0, 0, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				1.0, 0.0, 1.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				1.0, 0.0, 1.0, 1.0E-4 };

		// ======== menuBar1 ========
		{

			// ======== menu ========
			{
				menu.setText("Menu");

				// ---- newAccountMenuItem ----
				newAccountMenuItem.setText("Create new account");
				newAccountMenuItem.addActionListener(this);
				menu.add(newAccountMenuItem);

				// ---- forgotPasswordMenuItem ----
				forgotPasswordMenuItem.setText("Forgot password");
				forgotPasswordMenuItem.addActionListener(this);
				menu.add(forgotPasswordMenuItem);

				// ---- signInMenuItem ----
				signInMenuItem.setText("Sign In");
				signInMenuItem.setEnabled(false);
				signInMenuItem.addActionListener(this);
				menu.add(signInMenuItem);

				// ---- quitMenuItem ----
				quitMenuItem.setText("Quit");
				quitMenuItem.addActionListener(this);
				menu.add(quitMenuItem);
			}
			menuBar1.add(menu);
		}
		setJMenuBar(menuBar1);

		// ---- morseLabel ----
		morseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		morseLabel.setIcon(new ImageIcon(Resources.LOGIN_IMAGE));
		contentPane.add(morseLabel, new GridBagConstraints(1, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

		mainPanel.setLayout(new GridLayout(0, 1));

		// ======== panel2 ========
		{
			panel2.setLayout(new GridBagLayout());
			((GridBagLayout) panel2.getLayout()).columnWidths = new int[] { 0,
					0 };
			((GridBagLayout) panel2.getLayout()).rowHeights = new int[] { 0,
					24, 0, 0 };
			((GridBagLayout) panel2.getLayout()).columnWeights = new double[] {
					0.0, 1.0E-4 };
			((GridBagLayout) panel2.getLayout()).rowWeights = new double[] {
					0.0, 0.0, 0.0, 1.0E-4 };

			// ======== panel4 ========
			{
				panel4.setLayout(new GridBagLayout());
				((GridBagLayout) panel4.getLayout()).columnWidths = new int[] {
						0, 16, 100, 0 };
				((GridBagLayout) panel4.getLayout()).rowHeights = new int[] {
						0, 0, 0 };
				((GridBagLayout) panel4.getLayout()).columnWeights = new double[] {
						0.0, 0.0, 0.0, 1.0E-4 };
				((GridBagLayout) panel4.getLayout()).rowWeights = new double[] {
						0.0, 0.0, 1.0E-4 };

				// ---- userLabel ----
				userLabel.setText("Username :");
				panel4.add(userLabel, new GridBagConstraints(0, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
				panel4.add(usernameField, new GridBagConstraints(2, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

				// ---- passwordLabel ----
				passwordLabel.setText("Password :");
				panel4.add(passwordLabel, new GridBagConstraints(0, 1, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
				panel4.add(passwordField, new GridBagConstraints(2, 1, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
			panel2.add(panel4, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

			// ---- wrongDataLabel ----
			wrongDataLabel.setHorizontalAlignment(SwingConstants.CENTER);
			panel2.add(wrongDataLabel, new GridBagConstraints(0, 1, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

			panel2.add(progressBar, new GridBagConstraints(0, 1, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));
			progressBar.setVisible(false);

			// ======== panel3 ========
			{
				panel3.setLayout(new GridBagLayout());
				((GridBagLayout) panel3.getLayout()).columnWidths = new int[] {
						0, 0, 0, 0 };
				((GridBagLayout) panel3.getLayout()).rowHeights = new int[] {
						0, 0 };
				((GridBagLayout) panel3.getLayout()).columnWeights = new double[] {
						1.0, 0.0, 1.0, 1.0E-4 };
				((GridBagLayout) panel3.getLayout()).rowWeights = new double[] {
						0.0, 1.0E-4 };

				// ---- signInButton ----
				signInButton.setText("Sign In");
				signInButton.addActionListener(this);
				panel3.add(signInButton, new GridBagConstraints(1, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
			}
			panel2.add(panel3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));

		// ======== forgotPasswordPanel ========
		{
			forgotPasswordPanel.setLayout(new GridBagLayout());
			((GridBagLayout) forgotPasswordPanel.getLayout()).columnWidths = new int[] {
					0, 0 };
			((GridBagLayout) forgotPasswordPanel.getLayout()).rowHeights = new int[] {
					0, 24, 0, 0 };
			((GridBagLayout) forgotPasswordPanel.getLayout()).columnWeights = new double[] {
					0.0, 1.0E-4 };
			((GridBagLayout) forgotPasswordPanel.getLayout()).rowWeights = new double[] {
					0.0, 0.0, 0.0, 1.0E-4 };

			// ======== panel4 ========
			{
				forgotPanel1.setLayout(new GridBagLayout());
				((GridBagLayout) forgotPanel1.getLayout()).columnWidths = new int[] {
						0, 16, 100, 0 };
				((GridBagLayout) forgotPanel1.getLayout()).rowHeights = new int[] {
						0, 0 };
				((GridBagLayout) forgotPanel1.getLayout()).columnWeights = new double[] {
						0.0, 0.0, 0.0, 1.0E-4 };
				((GridBagLayout) forgotPanel1.getLayout()).rowWeights = new double[] {
						0.0, 1.0E-4 };

				// ---- emailLabel ----
				emailLabel.setText("Email :");
				forgotPanel1.add(emailLabel, new GridBagConstraints(0, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
				forgotPanel1.add(emailField, new GridBagConstraints(2, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
			forgotPasswordPanel.add(forgotPanel1, new GridBagConstraints(0, 0,
					1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

			// ---- wrongEmailLabel ----

			wrongEmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
			forgotPasswordPanel.add(wrongEmailLabel, new GridBagConstraints(0,
					1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

			// ======== recoverPanel ========
			{
				recoverPanel.setLayout(new GridBagLayout());
				((GridBagLayout) recoverPanel.getLayout()).columnWidths = new int[] {
						0, 0, 0, 0 };
				((GridBagLayout) recoverPanel.getLayout()).rowHeights = new int[] {
						0, 0 };
				((GridBagLayout) recoverPanel.getLayout()).columnWeights = new double[] {
						1.0, 0.0, 1.0, 1.0E-4 };
				((GridBagLayout) recoverPanel.getLayout()).rowWeights = new double[] {
						0.0, 1.0E-4 };

				// ---- recoverButton ----
				recoverButton.setText("Recover");
				recoverButton.addActionListener(this);
				recoverPanel.add(recoverButton, new GridBagConstraints(1, 0, 1,
						1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
			}
			forgotPasswordPanel.add(recoverPanel, new GridBagConstraints(0, 2,
					1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(forgotPasswordPanel, new GridBagConstraints(1, 1, 1, 1,
				0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));
		forgotPasswordPanel.setVisible(false);

		setSize(250, 425);
		setLocationRelativeTo(null);
	}

	private Boolean fieldCheck(String string) {
		if (string.replaceAll(" ", "").replaceAll("\n", "")
				.replaceAll("\r", "").replaceAll("\t", "").length() == 0) {
			return false;
		}

		return true;
	}

	public void setError(String message) {
		progressBar.setVisible(false);
		signInButton.setEnabled(true);
		wrongDataLabel.setVisible(true);
		wrongDataLabel.setText("<html><font color=\"red\">" + message
				+ "</font></html>");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == signInButton) {
			signInAction();
		}

		if (e.getSource() == newAccountMenuItem) {
			registerAction();
		}

		if (e.getSource() == forgotPasswordMenuItem) {
			passwordRecoveryAction();
		}

		if (e.getSource() == signInMenuItem) {
			showSignInPanel();
		}

		if (e.getSource() == recoverButton) {
			recoverAction();
		}

		if (e.getSource() == quitMenuItem) {
			quitAction();
		}
	}

	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}

		super.processWindowEvent(e);
	}

	private void quitAction() {
		setVisible(false);
		processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	private Boolean emailCheck() {
		String email = emailField.getText();
		if (!email.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+"
				+ "/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"
				+ "\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")) {
			wrongEmailLabel
					.setText("<html><font color=\"red\">Email is incorrect !</font></html>");
			return false;
		}

		return true;
	}

	private void recoverAction() {
		if (emailCheck()) {
			wrongEmailLabel.setText("");
			ForgotPassword forgotPassword = new ForgotPassword(
					emailField.getText());

			guiThread.getwThread().unblock(forgotPassword);
		}
	}

	private void showSignInPanel() {
		panel2.setVisible(true);
		forgotPasswordPanel.setVisible(false);
		forgotPasswordMenuItem.setEnabled(true);
		signInMenuItem.setEnabled(false);
	}

	private void passwordRecoveryAction() {
		panel2.setVisible(false);
		forgotPasswordPanel.setVisible(true);
		forgotPasswordMenuItem.setEnabled(false);
		signInMenuItem.setEnabled(true);
	}

	private void registerAction() {
		new RegisterWindow(guiThread).setVisible(true);
	}

	private void signInAction() {
		String username = usernameField.getText();
		String password = passwordField.getText();
		if (!fieldCheck(username)) {
			setError("Empty username !");
			return;
		}

		if (!fieldCheck(password)) {
			setError("Empty password !");
			return;
		}

		signInButton.setEnabled(false);
		System.out.println("----" + username + " " + password + "----");
		wrongDataLabel.setVisible(false);

		progressBar.setVisible(true);
		progressBar.setIndeterminate(true);

		guiThread.initClient();
		guiThread.getwThread().unblock(new LoginData(username, password));
	}

	public static void main(String[] args) {
		LoginWindow mw = new LoginWindow(null);
		mw.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mw.setVisible(true);
	}
}
