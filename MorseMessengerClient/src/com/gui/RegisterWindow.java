/*
 * Created by JFormDesigner on Thu Apr 12 01:47:53 EEST 2012
 */

package com.gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.client.GUIThread;
import com.netdata.RegisterStep1;

/**
 * @author Ghennadi
 */
public class RegisterWindow extends JFrame implements ActionListener {
	private JPanel panel1;
	private JLabel usernameLabel;
	private JTextField usernameTextField;
	private JLabel emailLabel;
	private JTextField emailTextField;
	private JLabel passwordLabel;
	private JPasswordField passwordTextField;
	private JLabel password1Label;
	private JPasswordField password1TextField;
	private JPanel panel2;
	private JButton nextButton;
	private JLabel errorLabel;
	private JProgressBar progressBar;

	private GUIThread guiThread;

	public RegisterWindow(GUIThread guiThread) {
		super("Register");
		this.guiThread = guiThread;
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		panel1 = new JPanel();
		usernameLabel = new JLabel();
		usernameTextField = new JTextField();
		emailLabel = new JLabel();
		emailTextField = new JTextField();
		passwordLabel = new JLabel();
		passwordTextField = new JPasswordField();
		password1Label = new JLabel();
		password1TextField = new JPasswordField();
		errorLabel = new JLabel();
		progressBar = new JProgressBar();
		panel2 = new JPanel();
		nextButton = new JButton();

		// ======== this ========
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] {
				15, 0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 15,
				0, 0, 15, 25, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				0.0, 0.0, 0.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };

		// ======== panel1 ========
		{
			panel1.setLayout(new GridBagLayout());
			((GridBagLayout) panel1.getLayout()).columnWidths = new int[] { 0,
					105, 14, 0, 100, 0 };
			((GridBagLayout) panel1.getLayout()).rowHeights = new int[] { 0, 0,
					0 };
			((GridBagLayout) panel1.getLayout()).columnWeights = new double[] {
					0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };
			((GridBagLayout) panel1.getLayout()).rowWeights = new double[] {
					0.0, 0.0, 1.0E-4 };

			// ---- usernameLabel ----
			usernameLabel.setText("Username :");
			panel1.add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel1.add(usernameTextField, new GridBagConstraints(1, 0, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

			// ---- emailLabel ----
			emailLabel.setText("Email :");
			panel1.add(emailLabel, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel1.add(emailTextField, new GridBagConstraints(4, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

			// ---- passwordLabel ----
			passwordLabel.setText("Password :");
			panel1.add(passwordLabel, new GridBagConstraints(0, 1, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));
			panel1.add(passwordTextField, new GridBagConstraints(1, 1, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

			// ---- password1Label ----
			password1Label.setText("Password :");
			panel1.add(password1Label, new GridBagConstraints(3, 1, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));
			panel1.add(password1TextField, new GridBagConstraints(4, 1, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));

		// ---- errorLabel ----
		errorLabel.setText("");
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(errorLabel, new GridBagConstraints(1, 2, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));
		progressBar.setVisible(false);
		contentPane.add(progressBar, new GridBagConstraints(1, 3, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

		// ======== panel2 ========
		{
			panel2.setLayout(new GridBagLayout());
			((GridBagLayout) panel2.getLayout()).columnWidths = new int[] { 0,
					0, 0 };
			((GridBagLayout) panel2.getLayout()).rowHeights = new int[] { 0, 0 };
			((GridBagLayout) panel2.getLayout()).columnWeights = new double[] {
					1.0, 0.0, 1.0E-4 };
			((GridBagLayout) panel2.getLayout()).rowWeights = new double[] {
					0.0, 1.0E-4 };

			// ---- nextButton ----
			nextButton.setText("Next");
			nextButton.addActionListener(this);
			panel2.add(nextButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel2, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));
		pack();
		setLocationRelativeTo(null);
		// //GEN-END:initComponents
	}

	public void setError(String text) {
		nextButton.setEnabled(true);
		progressBar.setVisible(false);
		errorLabel.setText("<html><center><font color=\"red\">" + text
				+ "</font></center></html>");
		errorLabel.setVisible(true);
		pack();
	}

	private Boolean passwordCheck() {
		if (password1TextField.getText().compareTo(passwordTextField.getText()) != 0) {
			setError("Passwords don't match");
			return false;
		}

		String password = passwordTextField.getText();
		if (password.length() < 8) {
			setError("Password must contain at least 8 characters !");
		}

		return true;
	}

	private Boolean usernameCheck() {
		String username = usernameTextField.getText();
		if (!username.matches("^[a-z0-9]+\\.*[a-z0-9]+$")) {
			setError("Invalid username !");
			return false;
		}

		return true;
	}

	private Boolean emailCheck() {
		String email = emailTextField.getText();
		if (!email.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+"
				+ "/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"
				+ "\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")) {
			setError("Invalid email !");
			return false;
		}

		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nextButton) {
			nextAction();
		}
	}

	private void nextAction() {
		/*
		 * progressBar.setVisible(true); setError("adsfdfds"); pack();
		 */
		System.out.println("Next Action");

		if (!usernameCheck()) {
			return;
		}

		if (!emailCheck()) {
			return;
		}

		if (!passwordCheck()) {
			return;
		}

		if (guiThread != null) {

			guiThread.getwThread().unblock(
					new RegisterStep1(usernameTextField.getText(),
							password1TextField.getText(), passwordTextField
									.getText(), emailTextField.getText()));

			guiThread.setRegisterWindow(this);

		}

		errorLabel.setVisible(false);
		progressBar.setIndeterminate(true);
		progressBar.setVisible(true);
		nextButton.setEnabled(false);
		pack();
	}

	public static void main(String[] args) {
		RegisterWindow rg = new RegisterWindow(null);
		rg.setDefaultCloseOperation(EXIT_ON_CLOSE);
		rg.setVisible(true);
	}

}
