package com.gui;

import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;

import com.netdata.FriendMW;

public class FriendNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 1L;
	public String name;
	private ImageIcon icon;

	public FriendNode(FriendMW friend) {
		this(friend.getUsername(), friend.getIcon());
	}

	public FriendNode(String name, ImageIcon avatar) {
		super(name, false);
		this.name = name;

		if (avatar == null) {
			System.out.println("Imaginea : " + Resources.DEFAULT_AVATAR_IMAGE);
			icon = new ImageIcon(Resources.DEFAULT_AVATAR_IMAGE);
		} else {
			icon = avatar;
		}
	}

	public ImageIcon getIcon() {
		return icon;
	}
}