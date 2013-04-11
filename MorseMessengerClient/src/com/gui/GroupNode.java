package com.gui;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

public class GroupNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 1L;
	public String name;
	private Icon icon;

	public GroupNode(String name) {
		super(name, true);
		this.name = name;
		icon = null;// new ImageIcon(Resources.GROUP_ICON);
	}

	public Icon getIcon() {
		return icon;
	}
}