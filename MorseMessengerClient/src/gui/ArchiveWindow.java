/*
 * Created by JFormDesigner on Tue May 01 12:43:48 EEST 2012
 */

package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author Ghennadi
 */
public class ArchiveWindow extends JFrame implements ActionListener,
		ListSelectionListener {

	private class ImageListCellRenderer implements ListCellRenderer {

		public Component getListCellRendererComponent(JList jlist,
				Object value, int cellIndex, boolean isSelected,
				boolean cellHasFocus) {

			if (value instanceof FriendPanelEntry) {
				Color background = new Color(255, 255, 255);

				Component component = (Component) value;
				component.setForeground(Color.white);
				component.setBackground(isSelected ? UIManager
						.getColor("Table.selectionBackground") : background);
				return component;
			} else {
				return new JLabel("No Panel Added");
			}
		}
	}

	private JScrollPane scrollPane1;
	private JList friendList;
	private JScrollPane scrollPane2;
	private JEditorPane archiveEditorPane;
	private FriendPanelEntry friendsPanelEntry;
	private JPanel progressBarPanel;
	private JProgressBar progressBar;

	public ArchiveWindow() {
		initComponents();
	}

	private void initComponents() {
		scrollPane1 = new JScrollPane();
		friendList = new JList();
		scrollPane2 = new JScrollPane();
		archiveEditorPane = new JEditorPane();
		progressBar = new JProgressBar();
		progressBarPanel = new JPanel();

		// ======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] {
				15, 167, 440, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 15,
				330, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				0.0, 0.0, 1.0, 0.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				0.0, 1.0, 0.0, 1.0E-4 };

		// ======== scrollPane1 ========
		{

			// ---- friendList ----
			friendList.addListSelectionListener(this);
			friendList.setCellRenderer(new ImageListCellRenderer());
			friendList.setListData(getFriendsPanels().toArray());
			friendList.setBackground(new Color(214, 217, 223));
			scrollPane1.setViewportView(friendList);
		}

		contentPane.add(scrollPane1, new GridBagConstraints(1, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

		// ======== scrollPane2 ========
		{
			archiveEditorPane.setEditable(false);
			scrollPane2.setViewportView(archiveEditorPane);
		}
		contentPane.add(scrollPane2, new GridBagConstraints(2, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));
		scrollPane2.setVisible(false);

		// ======== progressBarPanel ========
		{
			progressBarPanel.setLayout(new GridBagLayout());
			((GridBagLayout) progressBarPanel.getLayout()).columnWidths = new int[] {
					0, 206, 0, 0 };
			((GridBagLayout) progressBarPanel.getLayout()).rowHeights = new int[] {
					0, 0, 0, 0 };
			((GridBagLayout) progressBarPanel.getLayout()).columnWeights = new double[] {
					1.0, 0.0, 1.0, 1.0E-4 };
			((GridBagLayout) progressBarPanel.getLayout()).rowWeights = new double[] {
					1.0, 0.0, 1.0, 1.0E-4 };

			// ---- progressBar ----
			progressBar.setIndeterminate(true);
			progressBarPanel.add(progressBar, new GridBagConstraints(1, 1, 1,
					1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
			// progressBarPanel.setVisible(false);
		}
		contentPane.add(progressBarPanel, new GridBagConstraints(2, 1, 1, 1,
				0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

		pack();
		setLocationRelativeTo(getOwner());
	}

	private Vector<FriendPanelEntry> getFriendsPanels() {
		Vector<FriendPanelEntry> friends = new Vector<FriendPanelEntry>();

		for (int i = 0; i < 10; i++) {
			friends.add(new FriendPanelEntry("Gigel" + i, new ImageIcon(
					"avatar1_mini.jpg")));
		}

		return friends;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			System.out.println(e.getFirstIndex());
			if (e.getFirstIndex() == 0) {
				scrollPane2.setVisible(true);
				progressBarPanel.setVisible(false);
				printArchive();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	private void printArchive() {
		SimpleDateFormat headDateFormatter = new SimpleDateFormat(
				"EEE, dd-MMM-yyyy");
		SimpleDateFormat messageDateFormatter = new SimpleDateFormat("HH:mm:ss");

		String htmlText = "";
		archiveEditorPane.setContentType("text/html");

		long ts = (new Date()).getTime();

		Date dates[] = new Date[10];
		String messages[] = new String[10];
		String users[] = new String[10];

		for (int i = 0; i < dates.length; i++) {
			dates[i] = new Date(ts + 1000 * 60 * 60 * i);
			messages[i] = "Message sadasmdbjhasgdjhavsdjhgfashdfhgasdvj djsagdjh agsdja gdajhs gjhasg djahsg djashg djhgas jhgdaasj hgjdash gdjasg jashg jasgd jhagsd jhgasj hdgajs"
					+ i;
			users[i] = "user" + i;
		}

		htmlText += "<html>";

		htmlText += "<center><bold><div style=\"color:D5D5D5;font-weight:bold\"><strike>";

		/* Adaug spatii in header */
		for (int i = 0; i < 20; i++) {
			htmlText += "&nbsp";
		}
		htmlText += "</strike>";

		for (int i = 0; i < 3; i++) {
			htmlText += "&nbsp";
		}

		htmlText += " " + headDateFormatter.format(dates[0]);

		for (int i = 0; i < 3; i++) {
			htmlText += "&nbsp";
		}

		htmlText += "<strike>";
		for (int i = 0; i < 20; i++) {
			htmlText += "&nbsp";
		}
		htmlText += "</strike></bold></div></center><br>";

		for (int i = 0; i < dates.length; i++) {
			htmlText += "";
		}

		for (int i = 0; i < dates.length; i++) {
			htmlText += "<span style=\"color:D5D5D5;font-weight:bold\"> "
					+ messageDateFormatter.format(dates[i]) + "</span>"
					+ "<span style=\"font-weight:bold\"> " + users[i]
					+ " : </span>&nbsp&nbsp&nbsp&nbsp ";
			htmlText += messages[i];
			htmlText += "<br>";
		}

		htmlText += "</html>";

		System.out.println(htmlText);
		archiveEditorPane.setText(htmlText);
	}

	public static void main(String[] args) {
		GUISettings.getSettings();
		ArchiveWindow a = new ArchiveWindow();
		a.setVisible(true);
		a.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
