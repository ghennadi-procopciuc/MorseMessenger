package gui;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;

public class Group extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 1L;
	public String name;
	private Icon icon;

	public Group(String name) {
		super(name, true);
		this.name = name;
		icon = new ImageIcon(Resources.GROUP_ICON);
	}

	public Icon getIcon() {
		return icon;
	}
}