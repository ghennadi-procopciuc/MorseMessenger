/*
 * Created by JFormDesigner on Tue May 01 13:02:49 EEST 2012
 */

package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Ghennadi
 */
public class FriendPanelEntry extends JPanel {

	private JPanel panel1;
	private JLabel avatarLabel;
	private JLabel nameLabel;

	public FriendPanelEntry(String name, ImageIcon avatar) {
		avatarLabel = new JLabel();
		nameLabel = new JLabel();

		avatarLabel.setIcon(avatar);
		nameLabel.setText(name);

		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		panel1 = new JPanel();

		// ======== panel1 ========
		{
			panel1.setLayout(new BorderLayout());

			// ======== this ========
			{
				this.setLayout(new GridBagLayout());
				((GridBagLayout) getLayout()).columnWidths = new int[] { 6, 0,
						0, 0 };
				((GridBagLayout) getLayout()).rowHeights = new int[] { 7, 0, 2,
						0 };
				((GridBagLayout) getLayout()).columnWeights = new double[] {
						0.0, 0.0, 1.0, 1.0E-4 };
				((GridBagLayout) getLayout()).rowWeights = new double[] { 0.0,
						0.0, 0.0, 1.0E-4 };

				// ---- avatarLabel ----
				this.add(avatarLabel, new GridBagConstraints(1, 1, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

				// ---- nameLabel ----
				this.add(nameLabel, new GridBagConstraints(2, 1, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
			}
			panel1.add(this, BorderLayout.CENTER);
		}
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
