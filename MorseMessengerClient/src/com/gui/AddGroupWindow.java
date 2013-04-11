/*
 * Created by JFormDesigner on Mon May 07 12:17:12 EEST 2012
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.netdata.CreateGroupRequest;
import com.netdata.LoginDataResponse;

/**
 * @author Ghennadi
 */
public class AddGroupWindow extends JFrame implements ActionListener {
	private JLabel groupNameLabel;
	private JTextField groupNameTextField;
	private JLabel errorLabel;
	private JPanel panel1;
	private JButton addButton;

	private MainWindow mainWindow;
	private LoginDataResponse loginDataResponse;

	public AddGroupWindow(MainWindow mainWindow,
			LoginDataResponse loginDataResponse) {
		super("Add group");

		this.mainWindow = mainWindow;
		this.loginDataResponse = loginDataResponse;

		initComponents();
	}

	private void initComponents() {
		groupNameLabel = new JLabel();
		groupNameTextField = new JTextField();
		errorLabel = new JLabel();
		panel1 = new JPanel();
		addButton = new JButton();

		// ======== this ========
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] {
				15, 0, 116, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 15,
				0, 0, 0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				0.0, 0.0, 0.0, 0.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };

		// ---- groupNameLabel ----
		groupNameLabel.setText("Group name :");
		contentPane.add(groupNameLabel, new GridBagConstraints(1, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));
		contentPane.add(groupNameTextField, new GridBagConstraints(2, 1, 1, 1,
				0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

		// ---- errorLabel ----
		// errorLabel.setText("Group already exists !");
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(errorLabel, new GridBagConstraints(1, 2, 2, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

		// ======== panel1 ========
		{
			panel1.setLayout(new GridBagLayout());
			((GridBagLayout) panel1.getLayout()).columnWidths = new int[] { 0,
					0, 0 };
			((GridBagLayout) panel1.getLayout()).rowHeights = new int[] { 0, 0 };
			((GridBagLayout) panel1.getLayout()).columnWeights = new double[] {
					1.0, 0.0, 1.0E-4 };
			((GridBagLayout) panel1.getLayout()).rowWeights = new double[] {
					0.0, 1.0E-4 };

			// ---- addButton ----
			addButton.setText("Add");
			addButton.addActionListener(this);
			panel1.add(addButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel1, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));
		pack();
		setLocationRelativeTo(null);
	}

	private void setError(String text) {
		errorLabel.setText("<html><center><font color=\"red\">" + text
				+ "</font></center></html>");
		errorLabel.setVisible(true);
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {

			String groupName = groupNameTextField.getText();
			if (!groupName.matches("[a-zA-Z0-9]+")) {
				setError("Invalid group name !");
				return;
			}

			if (loginDataResponse.getResponse().containsKey(groupName)) {
				setError("Group already exists !");
				return;
			}

			setError("");
			loginDataResponse.getResponse().put(groupName, null);
			mainWindow.drawFriendsTree(loginDataResponse);

			// TODO Intervine Liviu
			CreateGroupRequest req = new CreateGroupRequest(groupName);
			/* send the create group request to server */
			mainWindow.getGuiThread().getwThread().unblock(req);
		}
	}
}
