/*
 * Created by JFormDesigner on Mon Apr 30 18:18:56 EEST 2012
 */

package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * @author Ghennadi
 */
public class FriendRequest extends JFrame {
	public FriendRequest() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		iconLabel = new JLabel();
		textLabel = new JLabel();
		panel1 = new JPanel();
		label2 = new JLabel();
		allowRadioButton = new JRadioButton();
		blockRadioButton = new JRadioButton();
		panel2 = new JPanel();
		button1 = new JButton();

		// ======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] {
				15, 0, 0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 15,
				0, 0, 0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				0.0, 0.0, 0.0, 0.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };

		// ---- iconLabel ----
		iconLabel.setText(" dsfds");
		contentPane.add(iconLabel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));

		// ---- textLabel ----
		textLabel
				.setText("[XXXXXXX] would like to add you to his or here Morse Messenger List.");
		contentPane.add(textLabel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));

		// ======== panel1 ========
		{
			panel1.setLayout(new GridBagLayout());
			((GridBagLayout) panel1.getLayout()).columnWidths = new int[] { 0,
					0 };
			((GridBagLayout) panel1.getLayout()).rowHeights = new int[] { 0, 0,
					0, 0 };
			((GridBagLayout) panel1.getLayout()).columnWeights = new double[] {
					0.0, 1.0E-4 };
			((GridBagLayout) panel1.getLayout()).rowWeights = new double[] {
					0.0, 0.0, 0.0, 1.0E-4 };

			// ---- label2 ----
			label2.setText("What do you want to do ?");
			panel1.add(label2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

			// ---- allowRadioButton ----
			allowRadioButton.setText("Allow this person to add me");
			panel1.add(allowRadioButton, new GridBagConstraints(0, 1, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

			// ---- blockRadioButton ----
			blockRadioButton.setText("Do not allow this person to add me");
			panel1.add(blockRadioButton, new GridBagConstraints(0, 2, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel1, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));

		// ======== panel2 ========
		{
			panel2.setLayout(new GridBagLayout());
			((GridBagLayout) panel2.getLayout()).columnWidths = new int[] { 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			((GridBagLayout) panel2.getLayout()).rowHeights = new int[] { 0, 0 };
			((GridBagLayout) panel2.getLayout()).columnWeights = new double[] {
					0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0, 1.0E-4 };
			((GridBagLayout) panel2.getLayout()).rowWeights = new double[] {
					0.0, 1.0E-4 };

			// ---- button1 ----
			button1.setText("Finish");
			panel2.add(button1, new GridBagConstraints(12, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel2, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));
		pack();
		setLocationRelativeTo(getOwner());

		// ---- buttonGroup ----
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(allowRadioButton);
		buttonGroup.add(blockRadioButton);
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JLabel iconLabel;
	private JLabel textLabel;
	private JPanel panel1;
	private JLabel label2;
	private JRadioButton allowRadioButton;
	private JRadioButton blockRadioButton;
	private JPanel panel2;
	private JButton button1;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
