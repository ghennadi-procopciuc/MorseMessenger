/*
 * Created by JFormDesigner on Thu Apr 12 01:33:37 EEST 2012
 */

package gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * @author Ghennadi
 */
public class MainWindow extends JFrame implements ActionListener,
		MouseListener, TreeSelectionListener {
	private JMenuBar menuBar1;
	private JMenu menu;
	private JMenuItem addFriendMenuItem;
	private JMenuItem signOutMenuItem;
	private JMenuItem quitMenuItem;
	private JMenuItem preferencesMenuItem;
	private JScrollPane scrollPane;
	private JTree friendTree;
	private JPopupMenu popupMenu;
	private JMenuItem removeFriend;
	private JMenuItem initiateChat;
	private JMenu edit;
	private JMenuItem profileMenuItem;
	private JMenuItem groupManagmentMenuItem;
	private JMenu help;
	private JMenuItem morseAlphabetMenuItem;

	private HashMap<String, ChatWindow> chatWindows;

	private class MyIconNodeRenderer extends DefaultTreeCellRenderer {

		public Component getTreeCellRendererComponent(JTree tree, Object value,
				boolean sel, boolean expanded, boolean leaf, int row,
				boolean hasFocus) {

			super.getTreeCellRendererComponent(tree, value, sel, expanded,
					leaf, row, hasFocus);

			Icon icon = null;
			if (value instanceof Friend) {
				icon = ((Friend) value).getIcon();

			}

			if (value instanceof Group) {
				icon = ((Group) value).getIcon();
			}

			if (icon == null) {
				System.out.println("WARNING : icon == null");
				icon = new ImageIcon(Resources.MORSE_ICON);
			}

			setIcon(icon);

			return this;
		}
	}

	public MainWindow() {
		initComponents();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initComponents() {
		menuBar1 = new JMenuBar();
		menu = new JMenu();
		addFriendMenuItem = new JMenuItem();
		signOutMenuItem = new JMenuItem();
		quitMenuItem = new JMenuItem();
		scrollPane = new JScrollPane();
		friendTree = new JTree();
		popupMenu = new JPopupMenu();
		removeFriend = new JMenuItem();
		initiateChat = new JMenuItem();
		preferencesMenuItem = new JMenuItem();
		edit = new JMenu();
		profileMenuItem = new JMenuItem();
		groupManagmentMenuItem = new JMenuItem();
		help = new JMenu();
		morseAlphabetMenuItem = new JMenuItem();

		chatWindows = new HashMap<String, ChatWindow>();

		// ======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] { 0,
				0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 0, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				1.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				1.0, 1.0E-4 };

		// ======== menuBar1 ========
		{

			// ======== menu ========
			{
				menu.setText(Resources.MAIN_MENU);

				// ---- addFriendMenuItem ----
				addFriendMenuItem.setText(Resources.ADD_FRIEND_OPTION);
				addFriendMenuItem.addActionListener(this);
				menu.add(addFriendMenuItem);

				// ---- signOutMenuItem ----
				signOutMenuItem.setText(Resources.SIGN_OUT_OPTION);
				signOutMenuItem.addActionListener(this);
				menu.add(signOutMenuItem);

				// ---- quitMenuItem ----
				quitMenuItem.setText(Resources.QUIT_OPTION);
				quitMenuItem.addActionListener(this);
				menu.add(quitMenuItem);

				// ---- preferences ----
				preferencesMenuItem.setText(Resources.PREFERENCES_OPTION);
				preferencesMenuItem.addActionListener(this);
				menu.add(preferencesMenuItem);
			}
			menuBar1.add(menu);

			{
				edit.setText(Resources.EDIT_MENU);

				profileMenuItem.setText(Resources.PROFILE_OPTION);
				profileMenuItem.addActionListener(this);
				edit.add(profileMenuItem);

				groupManagmentMenuItem
						.setText(Resources.GROUP_MANAGMENT_OPTION);
				groupManagmentMenuItem.addActionListener(this);
				edit.add(groupManagmentMenuItem);

			}
			menuBar1.add(edit);

			{
				help.setText(Resources.HELP_MENU);

				morseAlphabetMenuItem.setText(Resources.MORSE_ALPHABET_OPTION);
				morseAlphabetMenuItem.addActionListener(this);
				help.add(morseAlphabetMenuItem);
			}
			menuBar1.add(help);
		}
		setJMenuBar(menuBar1);

		// ======== scrollPane ========
		{

			// ---- friendTree ----
			// friendTree.setRootVisible(false);

			DefaultMutableTreeNode root = new DefaultMutableTreeNode("Friends");
			Group group = new Group("Grup1");

			root.add(group);

			for (int i = 0; i < 5; i++) {
				group.add(new Friend("vasile" + i));
			}

			friendTree = new JTree(root);

			/* Expandare arbore */
			expandAll(friendTree, true);
			friendTree.setCellRenderer(new MyIconNodeRenderer());

			scrollPane.setViewportView(friendTree);
		}

		{
			removeFriend.setText(Resources.REMOVE_FRIEND_OPTION);
			initiateChat.setText(Resources.INITIATE_CHAT_OPTION);
			friendTree.addMouseListener(this);

			/* Adaug listeneri */
			removeFriend.addActionListener(this);
			initiateChat.addActionListener(this);

			/* Adaug itemi la meniu */
			popupMenu.add(removeFriend);
			popupMenu.add(initiateChat);
		}

		contentPane.add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		setSize(250, 425);
		setLocationRelativeTo(null);
	}

	private void expandAll(JTree tree, boolean expand) {
		TreeNode root = (TreeNode) tree.getModel().getRoot();

		expandAll(tree, new TreePath(root), expand);
	}

	private void expandAll(JTree tree, TreePath parent, boolean expand) {
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandAll(tree, path, expand);
			}
		}

		if (expand) {
			tree.expandPath(parent);
		} else {
			tree.collapsePath(parent);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == removeFriend) {
			removeFriend();
		}

		if (e.getSource() == initiateChat) {

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) friendTree
					.getLastSelectedPathComponent();

			/* Nod neselectat */
			if (node == null) {
				return;
			}

			if (node instanceof Friend) {
				initiateChat((Friend) node);
			}
		}

		if (e.getSource() == addFriendMenuItem) {
			addFriendAction();
		}

		if (e.getSource() == signOutMenuItem) {
			signOutAction();
		}

		if (e.getSource() == quitMenuItem) {
			quitAction();
		}

		if (e.getSource() == preferencesMenuItem) {
			preferencesAction();
		}

		if (e.getSource() == profileMenuItem) {
			profileAction();
		}

		if (e.getSource() == morseAlphabetMenuItem) {
			morseAlphabetAction();
		}

		if (e.getSource() == groupManagmentMenuItem) {
			groupManagmentAction();
		}
	}

	private void groupManagmentAction() {
		System.out.println("Group Managment Action");
	}

	private void morseAlphabetAction() {
		new MorseAlphabetWindow().setVisible(true);
	}

	public void profileAction() {
		new ProfileWindow().setVisible(true);
	}

	private void removeFriend() {
		System.out.println("Remove Friend");
	}

	private void initiateChat(Friend friend) {
		if (!chatWindows.containsKey(friend.name)) {
			ChatWindow cw = new ChatWindow(friend.name, this);
			chatWindows.put(friend.name, cw);
			cw.setVisible(true);
		}
	}

	private void preferencesAction() {
		new PreferencesWindow().setVisible(true);
	}

	private void quitAction() {
		setVisible(false);
		dispose();
	}

	private void signOutAction() {
		System.out.println("signOutAction");
	}

	private void addFriendAction() {
		System.out.println("addFriendAction");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) friendTree
					.getLastSelectedPathComponent();

			/* Nod neselectat */
			if (node == null) {
				return;
			}

			if (node instanceof Friend) {
				initiateChat((Friend) node);
			}
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
		if (e.isPopupTrigger()) {

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) friendTree
					.getLastSelectedPathComponent();

			/* Nod neselectat */
			if (node == null) {
				return;
			}

			popupMenu.show(e.getComponent(), e.getX(), e.getY());
			if (node.isLeaf()) {
				System.out.println(((Friend) node).name);
			}
		}
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) friendTree
				.getLastSelectedPathComponent();

		if (node == null) {
			System.out.println("Dubiosenii");
		}

		System.out.println("sadsd");

	}

	public void closeChatWindow(String username) {
		chatWindows.remove(username);
	}

	public static void main(String[] args) {
		GUISettings.getSettings();
		new MainWindow();
	}
}