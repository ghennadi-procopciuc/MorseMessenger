/*
 * Created by JFormDesigner on Mon Apr 30 17:08:39 EEST 2012
 */

package gui;

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
import javax.swing.JTextField;

import com.netdata.FriendSearch;

/**
 * @author Ghennadi
 */
public class AddFriend extends JFrame implements ActionListener {

	public AddFriend() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		mainPanel = new JPanel();
		panel1 = new JPanel();
		nameLlabel = new JLabel();
		label6 = new JLabel();
		nameTextField = new JTextField();
		textField6 = new JTextField();
		surnameLabel = new JLabel();
		textField13 = new JTextField();
		textField9 = new JTextField();
		label14 = new JLabel();
		label7 = new JLabel();
		usernameTextField = new JTextField();
		textField7 = new JTextField();
		emailLabel = new JLabel();
		label10 = new JLabel();
		emailTextField = new JTextField();
		textField10 = new JTextField();
		hobbyLabel = new JLabel();
		label8 = new JLabel();
		hobbyTextField = new JTextField();
		textField8 = new JTextField();
		countryLabel = new JLabel();
		countryTextField = new JTextField();
		panel2 = new JPanel();
		findButton = new JButton();

		// ======== this ========
		setVisible(true);
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] {
				15, 0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 15,
				0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				0.0, 1.0, 0.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				0.0, 0.0, 0.0, 1.0E-4 };

		// ======== mainPanel ========
		{
			mainPanel.setLayout(new GridBagLayout());
			((GridBagLayout) mainPanel.getLayout()).columnWidths = new int[] {
					0, 0 };
			((GridBagLayout) mainPanel.getLayout()).rowHeights = new int[] { 0,
					0, 0 };
			((GridBagLayout) mainPanel.getLayout()).columnWeights = new double[] {
					1.0, 1.0E-4 };
			((GridBagLayout) mainPanel.getLayout()).rowWeights = new double[] {
					0.0, 0.0, 1.0E-4 };

			// ======== panel1 ========
			{
				panel1.setLayout(new GridBagLayout());
				((GridBagLayout) panel1.getLayout()).columnWidths = new int[] {
						0, 0, 0, 0, 0 };
				((GridBagLayout) panel1.getLayout()).rowHeights = new int[] {
						0, 0, 0, 0 };
				((GridBagLayout) panel1.getLayout()).columnWeights = new double[] {
						0.0, 1.0, 0.0, 1.0, 1.0E-4 };
				((GridBagLayout) panel1.getLayout()).rowWeights = new double[] {
						0.0, 0.0, 0.0, 1.0E-4 };

				// ---- nameLlabel ----
				nameLlabel.setText("Name :");
				panel1.add(nameLlabel, new GridBagConstraints(0, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- label6 ----
				label6.setText("Name :");
				panel1.add(label6, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));
				panel1.add(nameTextField, new GridBagConstraints(1, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
				panel1.add(textField6, new GridBagConstraints(1, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- surnameLabel ----
				surnameLabel.setText("Surname :");
				panel1.add(surnameLabel, new GridBagConstraints(2, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
				panel1.add(textField13, new GridBagConstraints(3, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
				panel1.add(textField9, new GridBagConstraints(3, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

				// ---- label14 ----
				label14.setText("Username :");
				panel1.add(label14, new GridBagConstraints(0, 1, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- label7 ----
				label7.setText("Username :");
				panel1.add(label7, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));
				panel1.add(usernameTextField, new GridBagConstraints(1, 1, 1,
						1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
				panel1.add(textField7, new GridBagConstraints(1, 1, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- emailLabel ----
				emailLabel.setText("Email :");
				panel1.add(emailLabel, new GridBagConstraints(2, 1, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- label10 ----
				label10.setText("Email :");
				panel1.add(label10, new GridBagConstraints(2, 1, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
				panel1.add(emailTextField, new GridBagConstraints(3, 1, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
				panel1.add(textField10, new GridBagConstraints(3, 1, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

				// ---- hobbyLabel ----
				hobbyLabel.setText("Hobby :");
				panel1.add(hobbyLabel, new GridBagConstraints(0, 2, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

				// ---- label8 ----
				label8.setText("Hobby :");
				panel1.add(label8, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));
				panel1.add(hobbyTextField, new GridBagConstraints(1, 2, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
				panel1.add(textField8, new GridBagConstraints(1, 2, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

				// ---- countryLabel ----
				countryLabel.setText("Country :");
				panel1.add(countryLabel, new GridBagConstraints(2, 2, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
				panel1.add(countryTextField, new GridBagConstraints(3, 2, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
			mainPanel.add(panel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

			// ======== panel2 ========
			{
				panel2.setLayout(new GridBagLayout());
				((GridBagLayout) panel2.getLayout()).columnWidths = new int[] {
						0, 0, 0 };
				((GridBagLayout) panel2.getLayout()).rowHeights = new int[] {
						0, 0 };
				((GridBagLayout) panel2.getLayout()).columnWeights = new double[] {
						1.0, 0.0, 1.0E-4 };
				((GridBagLayout) panel2.getLayout()).rowWeights = new double[] {
						0.0, 1.0E-4 };

				// ---- findButton ----
				findButton.setText("Find");
				panel2.add(findButton, new GridBagConstraints(1, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.EAST,
						GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0,
						0));
			}
			mainPanel.add(panel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(mainPanel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));
		setSize(395, 170);
		setLocationRelativeTo(null);
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JPanel mainPanel;
	private JPanel panel1;
	private JLabel nameLlabel;
	private JLabel label6;
	private JTextField nameTextField;
	private JTextField textField6;
	private JLabel surnameLabel;
	private JTextField textField13;
	private JTextField textField9;
	private JLabel label14;
	private JLabel label7;
	private JTextField usernameTextField;
	private JTextField textField7;
	private JLabel emailLabel;
	private JLabel label10;
	private JTextField emailTextField;
	private JTextField textField10;
	private JLabel hobbyLabel;
	private JLabel label8;
	private JTextField hobbyTextField;
	private JTextField textField8;
	private JLabel countryLabel;
	private JTextField countryTextField;
	private JPanel panel2;
	private JButton findButton;

	// JFormDesigner - End of variables declaration //GEN-END:variables

	public static void main(String[] args) {
		AddFriend af = new AddFriend();
		af.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == findButton) {
			FriendSearch fs = new FriendSearch();
			fs.setFirstName(nameTextField.getText());

		}
	}
}