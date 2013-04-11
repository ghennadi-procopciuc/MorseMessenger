/*
 * Created by JFormDesigner on Thu Apr 12 01:33:37 EEST 2012
 */

package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author Ghennadi
 */
public class MainWindow3 extends JFrame {
	public MainWindow3() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		menuBar1 = new JMenuBar();
		menu = new JMenu();
		newAccountMenuItem = new JMenuItem();
		forgotPasswordMenuItem = new JMenuItem();
		SignInMenuItem = new JMenuItem();
		signOutMenuItem = new JMenuItem();
		quitMenuItem = new JMenuItem();
		morseLabel = new JLabel();
		panel2 = new JPanel();
		panel4 = new JPanel();
		userLabel = new JLabel();
		usernameField = new JTextField();
		passwordLabel = new JLabel();
		passwordField = new JPasswordField();
		wrongPasswordLabel = new JLabel();
		panel3 = new JPanel();
		signInButton = new JButton();

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
				menu.add(newAccountMenuItem);

				// ---- forgotPasswordMenuItem ----
				forgotPasswordMenuItem.setText("Forgot password");
				menu.add(forgotPasswordMenuItem);

				// ---- SignInMenuItem ----
				SignInMenuItem.setText("Sign In");
				menu.add(SignInMenuItem);

				// ---- signOutMenuItem ----
				signOutMenuItem.setText("Sign out");
				menu.add(signOutMenuItem);

				// ---- quitMenuItem ----
				quitMenuItem.setText("Quit");
				menu.add(quitMenuItem);
			}
			menuBar1.add(menu);
		}
		setJMenuBar(menuBar1);

		// ---- morseLabel ----
		morseLabel.setText("Pune o marmota");
		contentPane.add(morseLabel, new GridBagConstraints(1, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

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

			// ---- wrongPasswordLabel ----
			wrongPasswordLabel
					.setText("<html><font color=\"red\">Username or password is incorrect !</font></html>");
			wrongPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
			panel2.add(wrongPasswordLabel, new GridBagConstraints(0, 1, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

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
		setSize(250, 425);
		setLocationRelativeTo(null);
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JMenuBar menuBar1;
	private JMenu menu;
	private JMenuItem newAccountMenuItem;
	private JMenuItem forgotPasswordMenuItem;
	private JMenuItem SignInMenuItem;
	private JMenuItem signOutMenuItem;
	private JMenuItem quitMenuItem;
	private JLabel morseLabel;
	private JPanel panel2;
	private JPanel panel4;
	private JLabel userLabel;
	private JTextField usernameField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JLabel wrongPasswordLabel;
	private JPanel panel3;
	private JButton signInButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
