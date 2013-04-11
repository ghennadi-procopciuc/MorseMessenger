package gui;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;

public class Friend extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 1L;
	public String name;
	private Icon icon;

	public Friend(String name) {
		super(name, false);
		this.name = name;
		icon = new ImageIcon(Resources.GROUP_ICON);
	}

	public Icon getIcon() {
		return icon;
	}
}