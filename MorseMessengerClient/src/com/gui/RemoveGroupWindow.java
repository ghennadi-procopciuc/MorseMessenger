/*
 * Created by JFormDesigner on Mon May 07 13:09:49 EEST 2012
 */

package com.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.netdata.DeleteGroupRequest;
import com.netdata.FriendMW;
import com.netdata.LoginDataResponse;

/**
 * @author Ghennadi
 */
public class RemoveGroupWindow extends JFrame implements ActionListener,
		ListSelectionListener {
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JLabel label1;
	private JLabel label2;
	private JScrollPane scrollPane1;
	private JList groupsList;
	private JPanel panel1;
	private JButton toRemoveButton;
	private JButton restoreButton;
	private JScrollPane scrollPane2;
	private JList removeList;
	private JPanel buttonBar;
	private JButton cancelButton;
	private JButton removeButton;

	private DefaultListModel<String> groupsListModel;
	private DefaultListModel<String> removeListModel;

	private MainWindow mainWindow;
	private LoginDataResponse loginDataResponse;

	public RemoveGroupWindow(MainWindow mainWindow,
			LoginDataResponse loginDataResponse) {
		super("Remove group");
		this.loginDataResponse = loginDataResponse;
		this.mainWindow = mainWindow;
		initComponents();
	}

	private void initComponents() {
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		label1 = new JLabel();
		label2 = new JLabel();
		scrollPane1 = new JScrollPane();
		groupsList = new JList();
		panel1 = new JPanel();
		toRemoveButton = new JButton();
		restoreButton = new JButton();
		scrollPane2 = new JScrollPane();
		removeList = new JList();
		buttonBar = new JPanel();
		cancelButton = new JButton();
		removeButton = new JButton();

		groupsListModel = new DefaultListModel<String>();
		removeListModel = new DefaultListModel<String>();

		// ======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		// ======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
			dialogPane.setLayout(new BorderLayout());

			// ======== contentPanel ========
			{
				contentPanel.setLayout(new GridBagLayout());
				((GridBagLayout) contentPanel.getLayout()).columnWidths = new int[] {
						0, 0, 0, 0 };
				((GridBagLayout) contentPanel.getLayout()).rowHeights = new int[] {
						0, 0, 0 };
				((GridBagLayout) contentPanel.getLayout()).columnWeights = new double[] {
						1.0, 0.0, 1.0, 1.0E-4 };
				((GridBagLayout) contentPanel.getLayout()).rowWeights = new double[] {
						0.0, 1.0, 1.0E-4 };

				// ---- label1 ----
				label1.setText("Groups");
				label1.setHorizontalAlignment(SwingConstants.CENTER);
				contentPanel.add(label1, new GridBagConstraints(0, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- label2 ----
				label2.setText("<html><center>Remove<br>groups<center><html>");
				label2.setHorizontalAlignment(SwingConstants.CENTER);
				contentPanel.add(label2, new GridBagConstraints(2, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

				// ======== scrollPane1 ========
				{
					for (String group : loginDataResponse.getResponse()
							.keySet()) {
						if (group.compareTo(Resources.SENT_REQUEST_GROUP_NAME) == 0
								|| group.compareTo(Resources.INIT_GROUP_NAME) == 0
								|| group.compareTo(Resources.INCOMING_REQUESTS_GROUP) == 0) {
							continue;
						}

						groupsListModel.addElement(group);
					}
					groupsList.setModel(groupsListModel);
					groupsList
							.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					groupsList.addListSelectionListener(this);
					scrollPane1.setViewportView(groupsList);
				}
				contentPanel.add(scrollPane1, new GridBagConstraints(0, 1, 1,
						1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

				// ======== panel1 ========
				{
					panel1.setLayout(new GridBagLayout());
					((GridBagLayout) panel1.getLayout()).columnWidths = new int[] {
							0, 0 };
					((GridBagLayout) panel1.getLayout()).rowHeights = new int[] {
							0, 0, 0, 0, 0 };
					((GridBagLayout) panel1.getLayout()).columnWeights = new double[] {
							0.0, 1.0E-4 };
					((GridBagLayout) panel1.getLayout()).rowWeights = new double[] {
							1.0, 0.0, 0.0, 1.0, 1.0E-4 };

					// ---- toRemoveButton ----
					toRemoveButton.setText(">>");
					toRemoveButton.setEnabled(false);
					toRemoveButton.addActionListener(this);
					panel1.add(toRemoveButton, new GridBagConstraints(0, 1, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0,
							0));

					// ---- restoreButton ----
					restoreButton.setText("<<");
					restoreButton.setEnabled(false);
					restoreButton.addActionListener(this);
					panel1.add(restoreButton, new GridBagConstraints(0, 2, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0,
							0));
				}
				contentPanel.add(panel1, new GridBagConstraints(1, 1, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

				// ======== scrollPane2 ========
				{
					removeList.setModel(removeListModel);
					removeList
							.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					removeList.addListSelectionListener(this);
					scrollPane2.setViewportView(removeList);
				}
				contentPanel.add(scrollPane2, new GridBagConstraints(2, 1, 1,
						1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			// ======== buttonBar ========
			{
				buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
				buttonBar.setLayout(new GridBagLayout());
				((GridBagLayout) buttonBar.getLayout()).columnWidths = new int[] {
						0, 0, 80 };
				((GridBagLayout) buttonBar.getLayout()).columnWeights = new double[] {
						0.0, 1.0, 0.0 };

				// ---- cancelButton ----
				cancelButton.setText("Cancel");
				cancelButton.addActionListener(this);
				buttonBar.add(cancelButton, new GridBagConstraints(0, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

				// ---- removeButton ----
				removeButton.setText("Remove");
				removeButton.setEnabled(false);
				removeButton.addActionListener(this);
				buttonBar.add(removeButton, new GridBagConstraints(2, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		setSize(400, 300);
		setLocationRelativeTo(getOwner());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == toRemoveButton) {
			toRemoveAction();
		}

		if (e.getSource() == restoreButton) {
			restoreAction();
		}

		if (e.getSource() == removeButton) {
			removeButtonAction();
		}

		if (e.getSource() == cancelButton) {
			cancelAction();
		}
	}

	private void removeButtonAction() {
		String group = "";
		for (int i = 0; i < removeListModel.size(); i++) {
			group = removeListModel.get(i);

			/* Verific daca exista grupul Init */
			if (!loginDataResponse.getResponse().containsKey(
					Resources.INIT_GROUP_NAME)) {
				loginDataResponse.getResponse().put(Resources.INIT_GROUP_NAME,
						new ArrayList<FriendMW>());
			}

			/* Adaug toti prietenii la grupul Init */
			ArrayList<FriendMW> friends = loginDataResponse.getResponse().get(
					group);

			if (friends != null) {
				for (FriendMW friend : friends) {
					if (friend != null) {
						loginDataResponse.getResponse()
								.get(Resources.INIT_GROUP_NAME).add(friend);
					}
				}
			}

			/* Elimin grupul */
			loginDataResponse.getResponse().remove(group);
			DeleteGroupRequest del = new DeleteGroupRequest(group);
			mainWindow.getGuiThread().getwThread().unblock(del);
			mainWindow.drawFriendsTree(loginDataResponse);
		}

		// TODO Intervine Liviu

		cancelAction();
	}

	private void restoreAction() {
		Integer selectedindex = removeList.getSelectedIndex();
		String selectedValue = removeListModel.get(selectedindex);

		System.out.println("S-a selectat : "
				+ removeListModel.get(selectedindex));

		removeListModel.remove(selectedindex);

		if (removeListModel.isEmpty()) {
			removeButton.setEnabled(false);
		}

		groupsListModel.addElement(selectedValue);

		restoreButton.setEnabled(false);
	}

	private void toRemoveAction() {
		Integer selectedindex = groupsList.getSelectedIndex();
		String selectedValue = groupsListModel.get(selectedindex);

		System.out.println("S-a selectat : "
				+ groupsListModel.get(selectedindex));

		groupsListModel.remove(selectedindex);

		removeButton.setEnabled(true);

		removeListModel.addElement(selectedValue);

		toRemoveButton.setEnabled(false);
	}

	private void cancelAction() {
		dispose();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			if (e.getSource() == groupsList) {
				System.out.println("Group List");
				toRemoveButton.setEnabled(true);
			}

			if (e.getSource() == removeList) {
				System.out.println("Remove List");
				restoreButton.setEnabled(true);
			}
		}
	}
}
