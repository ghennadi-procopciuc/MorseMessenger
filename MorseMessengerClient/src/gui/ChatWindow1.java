/*
 * Created by JFormDesigner on Sat May 05 15:13:45 EEST 2012
 */

package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * @author Ghennadi
 */
public class ChatWindow1 extends JFrame {
	public ChatWindow1() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		menuBar1 = new JMenuBar();
		menu = new JMenu();
		closeMenuItem = new JMenuItem();
		modeMenu = new JMenu();
		asciiMenuItem = new JCheckBoxMenuItem();
		morseMenuItem = new JCheckBoxMenuItem();
		panel1 = new JPanel();
		scrollPane1 = new JScrollPane();
		chatTextArea = new JTextArea();
		panel4 = new JPanel();
		friendAvatarLabel = new JLabel();
		userAvatarLabel = new JLabel();
		panel2 = new JPanel();
		scrollPane2 = new JScrollPane();
		textArea1 = new JTextArea();
		panel3 = new JPanel();
		button1 = new JButton();

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
				menu.add(closeMenuItem);
			}
			menuBar1.add(menu);

			// ======== modeMenu ========
			{
				modeMenu.setText("Mode");

				// ---- asciiMenuItem ----
				asciiMenuItem.setText("ASCII");
				modeMenu.add(asciiMenuItem);

				// ---- morseMenuItem ----
				morseMenuItem.setText("Morse");
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
				scrollPane1.setViewportView(chatTextArea);
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
				friendAvatarLabel
						.setText("<html><img src=\"avatar1.jpg\" alt=\"Smiley face\" height=\"100\" width=\"100\" /> </html>");
				friendAvatarLabel
						.setHorizontalTextPosition(SwingConstants.CENTER);
				panel4.add(friendAvatarLabel, new GridBagConstraints(0, 0, 1,
						1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

				// ---- userAvatarLabel ----
				userAvatarLabel
						.setText("<html><img src=\"avatar2.jpg\" alt=\"Smiley face\" height=\"100\" width=\"100\" /> </html>");
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
			((GridBagLayout) panel2.getLayout()).rowHeights = new int[] { 94, 0 };
			((GridBagLayout) panel2.getLayout()).columnWeights = new double[] {
					1.0, 0.0, 1.0E-4 };
			((GridBagLayout) panel2.getLayout()).rowWeights = new double[] {
					1.0, 1.0E-4 };

			// ======== scrollPane2 ========
			{
				scrollPane2.setViewportView(textArea1);
			}
			panel2.add(scrollPane2, new GridBagConstraints(0, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

			// ======== panel3 ========
			{
				panel3.setLayout(new GridBagLayout());
				((GridBagLayout) panel3.getLayout()).columnWidths = new int[] {
						104, 0 };
				((GridBagLayout) panel3.getLayout()).rowHeights = new int[] {
						0, 51, 0, 0 };
				((GridBagLayout) panel3.getLayout()).columnWeights = new double[] {
						1.0, 1.0E-4 };
				((GridBagLayout) panel3.getLayout()).rowWeights = new double[] {
						1.0, 0.0, 1.0, 1.0E-4 };

				// ---- button1 ----
				button1.setText("Send");
				panel3.add(button1, new GridBagConstraints(0, 1, 1, 1, 0.0,
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
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JMenuBar menuBar1;
	private JMenu menu;
	private JMenuItem closeMenuItem;
	private JMenu modeMenu;
	private JCheckBoxMenuItem asciiMenuItem;
	private JCheckBoxMenuItem morseMenuItem;
	private JPanel panel1;
	private JScrollPane scrollPane1;
	private JTextArea chatTextArea;
	private JPanel panel4;
	private JLabel friendAvatarLabel;
	private JLabel userAvatarLabel;
	private JPanel panel2;
	private JScrollPane scrollPane2;
	private JTextArea textArea1;
	private JPanel panel3;
	private JButton button1;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
