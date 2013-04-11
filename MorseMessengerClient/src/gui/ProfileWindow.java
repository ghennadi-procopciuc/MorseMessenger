/*
 * Created by JFormDesigner on Tue May 01 21:21:03 EEST 2012
 */

package gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author Ghennadi
 */
public class ProfileWindow extends JFrame implements ActionListener,
		MouseListener {

	private JPanel panel1;
	private JLabel usernameLabel;
	private JLabel avatarLabel;
	private JButton uploadButton;
	private JPanel panel5;
	private JLabel label7;
	private JTextField nameTextField;
	private JLabel label8;
	private JTextField surnameTextField;
	private JLabel label9;
	private JTextField birthDateTextField;
	private JLabel label10;
	private JTextField countryTextField;
	private JLabel label18;
	private JTextField hobbiesTextField;
	private JLabel label11;
	private JTextField newPasswordTextField;
	private JLabel label15;
	private JTextField passwordConfirmationTextField;
	private JLabel label12;
	private JTextField passwordTextField;
	private JPanel panel2;
	private JButton saveButton;

	public ProfileWindow() {
		initComponents();
	}

	private void initComponents() {
		panel1 = new JPanel();
		usernameLabel = new JLabel();
		avatarLabel = new JLabel();
		uploadButton = new JButton();
		panel5 = new JPanel();
		label7 = new JLabel();
		nameTextField = new JTextField();
		label8 = new JLabel();
		surnameTextField = new JTextField();
		label9 = new JLabel();
		birthDateTextField = new JTextField();
		label10 = new JLabel();
		countryTextField = new JTextField();
		label18 = new JLabel();
		hobbiesTextField = new JTextField();
		label11 = new JLabel();
		newPasswordTextField = new JTextField();
		label15 = new JLabel();
		passwordConfirmationTextField = new JTextField();
		label12 = new JLabel();
		passwordTextField = new JTextField();
		panel2 = new JPanel();
		saveButton = new JButton();

		// ======== this ========
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] {
				15, 0, 15, 0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 15,
				0, 0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				0.0, 0.0, 0.0, 1.0, 0.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				0.0, 1.0, 0.0, 0.0, 1.0E-4 };

		// ======== panel1 ========
		{
			panel1.setLayout(new GridBagLayout());
			((GridBagLayout) panel1.getLayout()).columnWidths = new int[] {
					100, 0 };
			((GridBagLayout) panel1.getLayout()).rowHeights = new int[] { 0,
					105, 0, 0, 0 };
			((GridBagLayout) panel1.getLayout()).columnWeights = new double[] {
					0.0, 1.0E-4 };
			((GridBagLayout) panel1.getLayout()).rowWeights = new double[] {
					0.0, 0.0, 0.0, 1.0, 1.0E-4 };

			// ---- usernameLabel ----
			usernameLabel.setText("<html><center>Username</center></html>");
			usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			usernameLabel.setFont(usernameLabel.getFont().deriveFont(
					usernameLabel.getFont().getStyle() | Font.BOLD));
			panel1.add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

			// ---- avatarLabel ----
			panel1.add(avatarLabel, new GridBagConstraints(0, 1, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

			// ---- uploadButton ----
			uploadButton.setText("Upload");
			panel1.add(uploadButton, new GridBagConstraints(0, 2, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));
		}
		contentPane.add(panel1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));

		// ======== panel5 ========
		{
			panel5.setLayout(new GridBagLayout());
			((GridBagLayout) panel5.getLayout()).columnWidths = new int[] { 0,
					0, 0, 0, 0 };
			((GridBagLayout) panel5.getLayout()).rowHeights = new int[] { 0, 0,
					0, 15, 0, 0, 0 };
			((GridBagLayout) panel5.getLayout()).columnWeights = new double[] {
					0.0, 1.0, 0.0, 1.0, 1.0E-4 };
			((GridBagLayout) panel5.getLayout()).rowWeights = new double[] {
					0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };

			// ---- label7 ----
			label7.setText("Name :");
			panel5.add(label7, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel5.add(nameTextField, new GridBagConstraints(1, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

			// ---- label8 ----
			label8.setText("Surname :");
			panel5.add(label8, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel5.add(surnameTextField, new GridBagConstraints(3, 0, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

			// ---- label9 ----
			birthDateTextField.addMouseListener(this);
			label9.setText("Birth date :");
			panel5.add(label9, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel5.add(birthDateTextField, new GridBagConstraints(1, 1, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

			// ---- label10 ----
			label10.setText("Country :");
			panel5.add(label10, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel5.add(countryTextField, new GridBagConstraints(3, 1, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

			// ---- label18 ----
			label18.setText("Hobbies :");
			panel5.add(label18, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel5.add(hobbiesTextField, new GridBagConstraints(1, 2, 3, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

			// ---- label11 ----
			label11.setText("<html><center>New<br>password :</center></html>");
			panel5.add(label11, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel5.add(newPasswordTextField, new GridBagConstraints(1, 4, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

			// ---- label15 ----
			label15.setText("<html><center>New<br>password :</center></html>");
			panel5.add(label15, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel5.add(passwordConfirmationTextField, new GridBagConstraints(3,
					4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

			// ---- label12 ----
			label12.setText("Password :");
			panel5.add(label12, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));
			panel5.add(passwordTextField, new GridBagConstraints(1, 5, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
		}
		contentPane.add(panel5, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));

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

			// ---- saveButton ----
			saveButton.setText("Save");
			panel2.add(saveButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel2, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));
		setSize(475, 275);
		setLocationRelativeTo(getOwner());
	}

	public void setUsername(String username) {
		usernameLabel.setText(username);
	}

	public void setAvatar(ImageIcon avatar) {
		avatarLabel.setIcon(avatar);
	}

	public void setName(String name) {
		nameTextField.setText(name);
	}

	public void setSurname(String surname) {
		surnameTextField.setText(surname);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == birthDateTextField) {
			System.out.println("AM AJUNS");
			new CalendarWindow(birthDateTextField);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		GUISettings.getSettings();
		ProfileWindow pw = new ProfileWindow();
		pw.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pw.setUsername("unix140");
		pw.setAvatar(new ImageIcon("avatar1.jpg"));
		pw.setVisible(true);
	}
}
