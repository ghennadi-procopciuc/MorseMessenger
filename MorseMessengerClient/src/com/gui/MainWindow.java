/*
 * Created by JFormDesigner on Thu Apr 12 01:33:37 EEST 2012
 */

package com.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.client.GUIThread;
import com.netdata.ArchiveFriendsResponse;
import com.netdata.DeleteFriendRequest;
import com.netdata.FriendMW;
import com.netdata.FriendProtocol;
import com.netdata.FriendSearchResponse;
import com.netdata.InitiateChat;
import com.netdata.LoginDataResponse;
import com.netdata.MoveFriendRequest;
import com.netdata.SignInNotification;

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
	private JPopupMenu friendPopupMenu;
	private JMenuItem removeFriend;
	private JMenuItem initiateChat;
	private JMenu edit;
	private JMenuItem profileMenuItem;
	private JMenuItem archiveMenuItem;
	private JMenu groupManagmentMenu;
	private JMenuItem addGroupMenuItem;
	private JMenuItem removeGroupMenuItem;
	private JMenu addFriendToGroupMenu;
	private JMenu help;
	private JMenuItem morseAlphabetMenuItem;

	private HashMap<String, ChatWindow> chatWindows;

	private LoginDataResponse loginDataResponse;
	private AddFriendWindow addFriendWindow;
	private SendFileWindow sendFileWindow;
	private ProfileWindow profileWindow;
	private ArchiveWindow archiveWindow;

	private GUIThread guiThread;

	private class MyIconNodeRenderer extends DefaultTreeCellRenderer {

		public Component getTreeCellRendererComponent(JTree tree, Object value,
				boolean sel, boolean expanded, boolean leaf, int row,
				boolean hasFocus) {

			super.getTreeCellRendererComponent(tree, value, sel, expanded,
					leaf, row, hasFocus);

			Icon icon = null;
			if (value instanceof FriendNode) {
				icon = ((FriendNode) value).getIcon();
				// System.out.println("WARNING [FRIEND] : icon == null");

			}

			if (value instanceof GroupNode) {
				icon = ((GroupNode) value).getIcon();
				// System.out.println("WARNING [GROUP] : icon == null");
			}

			if (icon == null) {
				// System.out.println("WARNING : icon == null");
				// icon = new ImageIcon(Resources.MORSE_ICON);
			}

			setIcon(icon);

			return this;
		}
	}

	public MainWindow(LoginDataResponse loginDataResponse, GUIThread guiThread) {
		super("Morse Messenger");

		this.guiThread = guiThread;
		this.loginDataResponse = loginDataResponse;
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
		friendPopupMenu = new JPopupMenu();
		removeFriend = new JMenuItem();
		initiateChat = new JMenuItem();
		preferencesMenuItem = new JMenuItem();
		edit = new JMenu();
		profileMenuItem = new JMenuItem();
		groupManagmentMenu = new JMenu();
		addGroupMenuItem = new JMenuItem();
		removeGroupMenuItem = new JMenuItem();
		addFriendToGroupMenu = new JMenu();
		help = new JMenu();
		morseAlphabetMenuItem = new JMenuItem();
		archiveMenuItem = new JMenuItem();

		sendFileWindow = new SendFileWindow(guiThread);
		chatWindows = new HashMap<String, ChatWindow>();

		archiveWindow = new ArchiveWindow(guiThread);
		archiveWindow.setVisible(false);

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

				// ---- View archive ----
				archiveMenuItem.setText(Resources.VIEW_ARCHIVE_OPTION);
				archiveMenuItem.addActionListener(this);
				menu.add(archiveMenuItem);

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

				{
					groupManagmentMenu.setText(Resources.GROUP_MANAGMENT_MENU);

					addGroupMenuItem.setText(Resources.ADD_GROUP_OPTION);
					addGroupMenuItem.addActionListener(this);
					groupManagmentMenu.add(addGroupMenuItem);

					removeGroupMenuItem.setText(Resources.REMOVE_GROUP_OPTION);
					removeGroupMenuItem.addActionListener(this);
					groupManagmentMenu.add(removeGroupMenuItem);
				}
				edit.add(groupManagmentMenu);

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
			drawFriendsTree(loginDataResponse);
		}

		{
			removeFriend.setText(Resources.REMOVE_FRIEND_OPTION);
			initiateChat.setText(Resources.INITIATE_CHAT_OPTION);
			addFriendToGroupMenu.setText(Resources.ADD_FRIEND_TO_GROUP_MENU);

			/* Adaug listeneri */
			removeFriend.addActionListener(this);
			initiateChat.addActionListener(this);

			/* Adaug itemi la meniu */
			friendPopupMenu.add(removeFriend);
			friendPopupMenu.add(initiateChat);
			friendPopupMenu.add(addFriendToGroupMenu);
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

	public void archiveFriends(ArchiveFriendsResponse resp) {
		archiveWindow.archiveFriends(resp);
	}

	public void drawFriendsTree(LoginDataResponse loginDataResponse) {

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Friends");

		if (loginDataResponse != null) {
			if (loginDataResponse.getResponse() != null) {

				ArrayList<FriendMW> acceptedFriends = new ArrayList<FriendMW>();
				ArrayList<FriendMW> rejectedFriends = new ArrayList<FriendMW>();

				for (String groupName : loginDataResponse.getResponse()
						.keySet()) {

					/* Grupul de utilizatori care urmeaza a-mi fi prieteni */
					if (groupName.trim().compareTo(
							Resources.INCOMING_REQUESTS_GROUP) == 0) {

						for (FriendMW friendMW : loginDataResponse
								.getResponse().get(groupName)) {
							int response = showAcceptDialog(friendMW
									.getUsername());
							if (response == JOptionPane.YES_OPTION) {
								acceptedFriends.add(friendMW);
							} else {
								rejectedFriends.add(friendMW);
							}
						}

						continue;
					}

					if (groupName.trim().compareTo(
							Resources.SENT_REQUEST_GROUP_NAME) == 0) {
						continue;
					}

					GroupNode group = new GroupNode(groupName);
					root.add(group);

					if (loginDataResponse.getResponse().get(groupName) != null) {
						for (FriendMW friend : loginDataResponse.getResponse()
								.get(groupName)) {
							if (friend != null) {
								FriendNode x = new FriendNode(friend);
								group.add(x);
							} else {
								break;
							}
						}
					}
				}

				for (FriendMW friend : acceptedFriends) {
					guiThread.getwThread().unblock(
							new FriendProtocol(null, friend,
									FriendProtocol.ACCEPT));
					System.out.println(friend.getUsername());

					if (loginDataResponse.getResponse().get(
							Resources.INIT_GROUP_NAME) == null) {
						ArrayList<FriendMW> list = new ArrayList<FriendMW>();
						list.add(friend);

						loginDataResponse.getResponse().put(
								Resources.INIT_GROUP_NAME, list);
					} else {
						loginDataResponse.getResponse()
								.get(Resources.INIT_GROUP_NAME).add(friend);
					}
				}

				for (FriendMW friend : rejectedFriends) {
					guiThread.getwThread().unblock(
							new FriendProtocol(null, friend,
									FriendProtocol.DENY));
				}

				/* Golesc lista de prieteni in asteptare */
				loginDataResponse.getResponse().remove(
						Resources.INCOMING_REQUESTS_GROUP);

				loginDataResponse.getResponse().put(
						Resources.INCOMING_REQUESTS_GROUP,
						new ArrayList<FriendMW>());
			}
		}

		this.loginDataResponse = loginDataResponse;
		friendTree = new JTree(root);

		/* Expandare arbore */
		expandAll(friendTree, true);
		friendTree.setCellRenderer(new MyIconNodeRenderer());
		friendTree.addMouseListener(this);

		scrollPane.setViewportView(friendTree);

		System.out.println("SIZE : "
				+ loginDataResponse.getResponse()
						.get(Resources.INCOMING_REQUESTS_GROUP).size());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == removeFriend) {
			removeFriend();
			return;
		}

		if (e.getSource() == initiateChat) {

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) friendTree
					.getLastSelectedPathComponent();

			/* Nod neselectat */
			if (node == null) {
				return;
			}

			if (node instanceof FriendNode) {
				initiateChat((FriendNode) node);
			}
			return;
		}

		if (e.getSource() == addFriendMenuItem) {
			addFriendAction();
			return;
		}

		if (e.getSource() == signOutMenuItem) {
			signOutAction();
			return;
		}

		if (e.getSource() == quitMenuItem) {
			quitAction();
			return;
		}

		if (e.getSource() == preferencesMenuItem) {
			preferencesAction();
			return;
		}

		if (e.getSource() == profileMenuItem) {
			profileAction();
			return;
		}

		if (e.getSource() == morseAlphabetMenuItem) {
			morseAlphabetAction();
			return;
		}

		if (e.getSource() == addGroupMenuItem) {
			new AddGroupWindow(this, loginDataResponse).setVisible(true);
			return;
		}

		if (e.getSource() == removeGroupMenuItem) {
			new RemoveGroupWindow(this, loginDataResponse).setVisible(true);
			return;
		}

		if (e.getSource() == archiveMenuItem) {
			viewArchive();
			return;
		}

		if (e.getSource() instanceof JMenuItem) {

			String itemName = ((JMenuItem) e.getSource()).getName();
			System.out.println("Mut utiliztorul in grup !!!");
			moveFriendAction(itemName);
		}
	}

	private void viewArchive() {
		new ArchiveWindow(guiThread).setVisible(true);
	}

	private int showAcceptDialog(String username) {
		Object[] options = { "Allow this person to add me",
				"Do not allow this person to add me" };
		int n = JOptionPane
				.showOptionDialog(
						this,
						username
								+ " would like to add you to his or here Morse Messenger List.",
						"Friend Request", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		return n;
	}

	private void moveFriendAction(String itemName) {

		if (loginDataResponse.getResponse().containsKey(itemName)) {
			System.out.println("Ai apasat pe " + itemName);

			if (loginDataResponse.getResponse().get(itemName) == null) {
				loginDataResponse.getResponse().put(itemName,
						new ArrayList<FriendMW>());
			}
		}

		FriendNode friendNode = (FriendNode) friendTree
				.getLastSelectedPathComponent();

		for (String group : loginDataResponse.getResponse().keySet()) {
			Boolean found = false;

			if (loginDataResponse.getResponse().get(group) == null) {
				loginDataResponse.getResponse().put(group,
						new ArrayList<FriendMW>());
			}

			for (FriendMW friendMW : loginDataResponse.getResponse().get(group)) {
				if (friendMW.getUsername().compareTo(friendNode.name) == 0) {
					/* getResponse retrieves the list of friends */
					loginDataResponse.getResponse().get(itemName).add(friendMW);
					loginDataResponse.getResponse().get(group).remove(friendMW);
					/* backend job */
					MoveFriendRequest req = new MoveFriendRequest(
							friendMW.getUsername(), itemName);
					guiThread.getwThread().unblock(req);
					found = true;
					break;
				}
			}

			if (found) {
				break;
			}
		}

		// TODO Intervine Liviu - done

		drawFriendsTree(loginDataResponse);

		addFriendToGroupMenu.removeAll();
	}

	private void groupManagmentAction() {
		System.out.println("Group Managment Action");
	}

	private void morseAlphabetAction() {
		new MorseAlphabetWindow().setVisible(true);
	}

	public void profileAction() {
		ProfileWindow pf = new ProfileWindow(guiThread);
		profileWindow = pf;
		pf.setVisible(true);
	}

	private void removeFriend() {
		FriendNode friendNode = (FriendNode) friendTree
				.getLastSelectedPathComponent();
		FriendMW friend = null;
		String group = null;

		Boolean found = false;

		for (String groupName : loginDataResponse.getResponse().keySet()) {
			if (loginDataResponse.getResponse().get(groupName) != null) {

				for (FriendMW friendMW : loginDataResponse.getResponse().get(
						groupName)) {

					if (friendMW.getUsername().compareTo(friendNode.name) == 0) {
						// TODO Intervine Liviu - in if(found), down
						friend = friendMW;
						group = groupName;
						found = true;
					}
				}

				if (found) {
					break;
				}
			}
		}

		if (found) {
			/* backend dirty job */
			DeleteFriendRequest req = new DeleteFriendRequest(
					friend.getUsername());
			guiThread.getwThread().unblock(req);
			loginDataResponse.getResponse().get(group).remove(friend);
		}

		drawFriendsTree(loginDataResponse);
	}

	public void signOut(String username) {
		boolean found = false;

		for (String group : loginDataResponse.getResponse().keySet()) {
			if (loginDataResponse.getResponse().get(group) != null) {
				for (FriendMW friend : loginDataResponse.getResponse().get(
						group)) {
					if (username.compareTo(friend.getUsername()) == 0) {
						loginDataResponse.getResponse().get(group)
								.remove(friend);
						found = true;
						break;
					}
				}

				if (found) {
					break;
				}
			}
		}

		drawFriendsTree(loginDataResponse);
	}

	private void initiateChat(FriendNode friend) {
		if (!chatWindows.containsKey(friend.name)) {
			ChatWindow cw = new ChatWindow(friend.name, this);
			chatWindows.put(friend.name, cw);
			cw.setVisible(true);
		}
	}

	public LoginDataResponse getLoginDataResponse() {
		return loginDataResponse;
	}

	public void setLoginDataResponse(LoginDataResponse loginDataResponse) {
		this.loginDataResponse = loginDataResponse;
	}

	private void preferencesAction() {
		new PreferencesWindow().setVisible(true);
	}

	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}

		super.processWindowEvent(e);
	}

	private void quitAction() {
		setVisible(false);
		processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	private void signOutAction() {
		System.out.println("signOutAction");
		guiThread.signOut();
	}

	private void addFriendAction() {
		System.out.println("addFriendAction");
		addFriendWindow = new AddFriendWindow(guiThread);
		addFriendWindow.setVisible(true);
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

			if (node instanceof FriendNode) {
				initiateChat((FriendNode) node);
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

			if (node.isLeaf() && node instanceof FriendNode) {
				System.out.println(((FriendNode) node).name);

				addFriendToGroupMenu.removeAll();

				JMenuItem groupItem = null;
				for (String groupName : loginDataResponse.getResponse()
						.keySet()) {
					if (loginDataResponse.getResponse().get(groupName) != null) {
						Boolean found = false;
						for (FriendMW friendMW : loginDataResponse
								.getResponse().get(groupName)) {
							if (friendMW != null) {
								if (friendMW.getUsername().compareTo(
										((FriendNode) node).name) == 0) {
									found = true;
								}
							}
						}

						if (!found) {
							groupItem = new JMenuItem();
							groupItem.setName(groupName);
							groupItem.addActionListener(this);
							groupItem.setText(groupName);

							/* Nu pot sa mut un utilizator intr-un grup special */
							if (groupName
									.compareTo(Resources.SENT_REQUEST_GROUP_NAME) != 0
									&& groupName
											.compareTo(Resources.INCOMING_REQUESTS_GROUP) != 0) {
								addFriendToGroupMenu.add(groupItem);
							}
						}
					} else {
						groupItem = new JMenuItem();
						groupItem.setName(groupName);
						groupItem.addActionListener(this);
						groupItem.setText(groupName);

						/* Nu pot sa mut un utilizator intr-un grup special */
						if (groupName
								.compareTo(Resources.SENT_REQUEST_GROUP_NAME) != 0
								&& groupName
										.compareTo(Resources.INCOMING_REQUESTS_GROUP) != 0) {
							addFriendToGroupMenu.add(groupItem);
						}
					}
				}

				friendPopupMenu.show(e.getComponent(), e.getX(), e.getY());
				return;
			}

			if (node instanceof GroupNode) {
				System.out.println("Group");
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

	public void setFriendSearchResponse(
			FriendSearchResponse friendSearchResponse) {

		if (addFriendWindow != null) {
			addFriendWindow.dispose();
		}

		System.out.println("[MainWindow] friendSearchResponse dim : "
				+ friendSearchResponse.getList().size());
		new AddFriendResultWindow(friendSearchResponse, guiThread)
				.setVisible(true);
	}

	public void postMessage(InitiateChat message) {
		ChatWindow chatWindow;

		if (chatWindows.containsKey(message.getFrom())) {
			chatWindow = chatWindows.get(message.getFrom());
			chatWindow.setVisible(true);
			chatWindow.addMessage(message.getFrom(), false, message.getData());
		} else {
			chatWindow = new ChatWindow(message.getFrom(), this);
			chatWindow.addMessage(message.getFrom(), false, message.getData());
			chatWindows.put(message.getFrom(), chatWindow);
			chatWindow.setVisible(true);
		}
	}

	public GUIThread getGuiThread() {
		return guiThread;
	}

	public void setGuiThread(GUIThread guiThread) {
		this.guiThread = guiThread;
	}

	public SendFileWindow getSendFileWindow() {
		return sendFileWindow;
	}

	public void setSendFileWindow(SendFileWindow sendFileWindow) {
		this.sendFileWindow = sendFileWindow;
	}

	public void addNewFriend(FriendMW friend) {
		this.loginDataResponse.getResponse().get(Resources.INIT_GROUP_NAME)
				.add(friend);
		drawFriendsTree(loginDataResponse);
	}

	public ProfileWindow getProfileWindow() {
		return profileWindow;
	}

	public void setProfileWindow(ProfileWindow profileWindow) {
		this.profileWindow = profileWindow;
	}

	public static void main(String[] args) {
		GUISettings.getSettings();

		TreeMap<String, ArrayList<FriendMW>> data = new TreeMap<String, ArrayList<FriendMW>>();

		for (int i = 0; i < 5; i++) {
			String group = "group" + i;

			ArrayList<FriendMW> users = new ArrayList<FriendMW>();
			for (int j = 0; j < 5; j++) {
				users.add(new FriendMW("user" + i + j, 1, null));
			}

			System.out.println(users.size());
			data.put(group, users);
		}

		LoginDataResponse ldr = new LoginDataResponse(data,
				"Ghnennadi Procopciuc", "unix140", null);
		ldr.setUsername("unix140");
		ldr.setCompleteName("");
		new MainWindow(ldr, null);
	}

	public void signIn(SignInNotification receivedData) {
		if (loginDataResponse.getResponse().get(receivedData.getGroupname()) == null) {
			ArrayList<FriendMW> friends = new ArrayList<FriendMW>();
			friends.add(receivedData.getUserMW());
			loginDataResponse.getResponse().put(receivedData.getGroupname(),
					friends);
			drawFriendsTree(loginDataResponse);
		}

		loginDataResponse.getResponse().get(receivedData.getGroupname())
				.add(receivedData.getUserMW());
		drawFriendsTree(loginDataResponse);
	}
}