/*
 * Created by JFormDesigner on Mon Apr 30 17:08:39 EEST 2012
 */

package com.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.client.GUIThread;
import com.netdata.FriendMW;
import com.netdata.FriendProtocol;
import com.netdata.FriendSearchResponse;
import com.netdata.UserInfoData;

/**
 * @author Ghennadi
 */
public class AddFriendResultWindow extends JFrame implements ActionListener {
	private static String[] columnNames = { "Username", "Country", "Hobbies",
			"First name", "Last name", "Email", "Birthdate", "Avatar" };
	private JPanel mainPanel;
	private JScrollPane scrollPane;
	private JTable resultTable;
	private JPanel panel2;
	private JButton button1;
	private JButton finishButton;
	private FriendSearchResponse friendSearchResponse;
	private DefaultTableModel tableModel;

	private GUIThread guiThread;

	public AddFriendResultWindow(FriendSearchResponse friendSearchResponse,
			GUIThread guiThread) {
		super("Add friend");

		this.friendSearchResponse = friendSearchResponse;
		this.guiThread = guiThread;

		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		mainPanel = new JPanel();
		scrollPane = new JScrollPane();
		resultTable = new JTable();
		panel2 = new JPanel();
		button1 = new JButton();
		finishButton = new JButton();

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
			((GridBagLayout) mainPanel.getLayout()).rowHeights = new int[] {
					170, 0, 0 };
			((GridBagLayout) mainPanel.getLayout()).columnWeights = new double[] {
					1.0, 1.0E-4 };
			((GridBagLayout) mainPanel.getLayout()).rowWeights = new double[] {
					0.0, 0.0, 1.0E-4 };

			// ======== scrollPane ========
			{

				// ---- resultTable ----
				tableModel = new DefaultTableModel(
						getData(friendSearchResponse.getList()), new Vector(
								Arrays.asList(columnNames))) {

					private static final long serialVersionUID = 1L;

					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}

				};

				resultTable.setModel(tableModel);
				resultTable.getColumn("Avatar").setCellRenderer(
						new ImageRenderer());
				DefaultTableModel Tmodel = (DefaultTableModel) resultTable
						.getModel();

				resultTable.setRowHeight(35);
				resultTable.setAutoCreateRowSorter(true);

				resultTable.setRowSelectionAllowed(true);
				scrollPane.setViewportView(resultTable);
			}
			mainPanel.add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

			// ======== panel2 ========
			{
				panel2.setLayout(new GridBagLayout());
				((GridBagLayout) panel2.getLayout()).columnWidths = new int[] {
						0, 0, 0, 0 };
				((GridBagLayout) panel2.getLayout()).rowHeights = new int[] {
						0, 0 };
				((GridBagLayout) panel2.getLayout()).columnWeights = new double[] {
						1.0, 0.0, 0.0, 1.0E-4 };
				((GridBagLayout) panel2.getLayout()).rowWeights = new double[] {
						0.0, 1.0E-4 };

				// ---- button1 ----
				button1.setText("Cancel");
				button1.addActionListener(this);
				panel2.add(button1, new GridBagConstraints(1, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

				// ---- finishButton ----
				finishButton.setText("Finish");
				finishButton.addActionListener(this);
				panel2.add(finishButton, new GridBagConstraints(2, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.EAST,
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
		setSize(700, 245);
		setLocationRelativeTo(null);
	}

	public Vector<Vector<Object>> getData(ArrayList<UserInfoData> list) {

		System.out.println("Dimesiunea listei : " + list.size());

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		for (int i = 0; i < list.size(); i++) {
			UserInfoData user = list.get(i);
			/* should not show SELF at Add Friend */
			if (user.getUsername().compareTo(guiThread.getSelf().getUsername()) == 0)
				continue;

			data.add(new Vector<Object>());

			data.get(i).add(user.getUsername());
			data.get(i).add(user.getCountry());

			// hobbies
			{
				String hobbies = "";
				if (user.getHobbies() != null) {
					for (String hobby : user.getHobbies()) {
						hobbies += hobby + ", ";
					}
				}

				if (hobbies.endsWith(", ")) {
					hobbies = hobbies.substring(0, hobbies.length() - 2);
				}

				data.get(i).add(hobbies);
			}

			data.get(i).add(user.getFirstName());
			data.get(i).add(user.getLastName());
			data.get(i).add(user.getMail());

			// birthdate
			{

				Date birthdate = new Date(user.getBirthDate());
				SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
				data.get(i).add(format.format(birthdate));
			}

			// Avatar
			{
				ImageIcon avatar;
				if (user.getAvatar() == null) {
					avatar = new ImageIcon(Resources.DEFAULT_AVATAR_IMAGE);
					if (avatar == null) {
						System.out.println("CEVA NASPA");
					}
					System.out.println("Here");
				} else {
					avatar = new ImageIcon(user.getAvatar());
				}

				data.get(i).add(avatar);
			}
		}

		return data;
	}

	class ImageRenderer extends DefaultTableCellRenderer {
		JLabel lbl = new JLabel();

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			lbl.setIcon((ImageIcon) value);
			return lbl;
		}
	}

	private void finishAction() {
		int index = resultTable.getSelectedRow();

		if (index != -1) {
			// Friend
			FriendMW destination = new FriendMW(friendSearchResponse.getList()
					.get(index).getUsername(), 0, null);
			FriendProtocol fp = new FriendProtocol(guiThread.getSelf(),
					destination, FriendProtocol.REQUEST);

			guiThread.getwThread().unblock(fp);
			JOptionPane.showMessageDialog(this, "The request was sent to : "
					+ friendSearchResponse.getList().get(index).getUsername(),
					"Info", JOptionPane.INFORMATION_MESSAGE, null);
			dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == finishButton) {
			finishAction();
		}
		/* cancel */
		if (e.getSource() == button1) {
			System.out.println("AAAAAAAAAAAAA");
			this.dispose();
		}
	}

	public static void main(String[] args) {

		ArrayList<UserInfoData> list = new ArrayList<UserInfoData>();
		for (int i = 0; i < 10; i++) {
			UserInfoData user = new UserInfoData("username" + i, "country" + i,
					"hobby" + i, "firstName" + i, "lastName" + i, "mail" + i,
					(long) 1235445321, null, null);
			list.add(user);
		}
		FriendSearchResponse fsr = new FriendSearchResponse(list);

		AddFriendResultWindow afrw = new AddFriendResultWindow(fsr, null);
		afrw.setDefaultCloseOperation(EXIT_ON_CLOSE);
		afrw.setVisible(true);
	}
}
