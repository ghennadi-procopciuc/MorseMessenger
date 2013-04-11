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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author Ghennadi
 */
public class ForgotPasswordPanel extends JFrame {
	public ForgotPasswordPanel() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		menuBar1 = new JMenuBar();
		menu = new JMenu();
		newAccountMenuItem = new JMenuItem();
		forgotPasswordMenuItem = new JMenuItem();
		signInMenuItem = new JMenuItem();
		signOutMenuItem = new JMenuItem();
		quitMenuItem = new JMenuItem();
		forgotPasswordPanel = new JPanel();
		panel4 = new JPanel();
		emailLabel = new JLabel();
		emailField = new JTextField();
		wrongEmailLabel = new JLabel();
		recoverPanel = new JPanel();
		recoverButton = new JButton();

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

				// ---- signInMenuItem ----
				signInMenuItem.setText("Sign In");
				menu.add(signInMenuItem);

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
				panel4.setLayout(new GridBagLayout());
				((GridBagLayout) panel4.getLayout()).columnWidths = new int[] {
						0, 16, 100, 0 };
				((GridBagLayout) panel4.getLayout()).rowHeights = new int[] {
						0, 0 };
				((GridBagLayout) panel4.getLayout()).columnWeights = new double[] {
						0.0, 0.0, 0.0, 1.0E-4 };
				((GridBagLayout) panel4.getLayout()).rowWeights = new double[] {
						0.0, 1.0E-4 };

				// ---- emailLabel ----
				emailLabel.setText("Email :");
				panel4.add(emailLabel, new GridBagConstraints(0, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
				panel4.add(emailField, new GridBagConstraints(2, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
			forgotPasswordPanel.add(panel4, new GridBagConstraints(0, 0, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

			// ---- wrongEmailLabel ----
			wrongEmailLabel
					.setText("<html><font color=\"red\">Email is incorrect !</font></html>");
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
		setSize(250, 425);
		setLocationRelativeTo(null);
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JMenuBar menuBar1;
	private JMenu menu;
	private JMenuItem newAccountMenuItem;
	private JMenuItem forgotPasswordMenuItem;
	private JMenuItem signInMenuItem;
	private JMenuItem signOutMenuItem;
	private JMenuItem quitMenuItem;
	private JPanel forgotPasswordPanel;
	private JPanel panel4;
	private JLabel emailLabel;
	private JTextField emailField;
	private JLabel wrongEmailLabel;
	private JPanel recoverPanel;
	private JButton recoverButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
