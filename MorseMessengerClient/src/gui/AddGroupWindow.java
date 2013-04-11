/*
 * Created by JFormDesigner on Mon May 07 12:17:12 EEST 2012
 */

package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.netdata.CreateGroupRequest;

/**
 * @author Ghennadi
 */
public class AddGroupWindow extends JFrame implements ActionListener {
	public AddGroupWindow() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		groupNameLabel = new JLabel();
		groupNameTextField = new JTextField();
		label1 = new JLabel();
		panel1 = new JPanel();
		addButton = new JButton();

		// ======== this ========
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] {
				15, 0, 116, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 15,
				0, 0, 0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				0.0, 0.0, 0.0, 0.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };

		// ---- groupNameLabel ----
		groupNameLabel.setText("Group name :");
		contentPane.add(groupNameLabel, new GridBagConstraints(1, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));
		contentPane.add(groupNameTextField, new GridBagConstraints(2, 1, 1, 1,
				0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

		// ---- label1 ----
		label1.setText("Group already exists !");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label1, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));

		// ======== panel1 ========
		{
			panel1.setLayout(new GridBagLayout());
			((GridBagLayout) panel1.getLayout()).columnWidths = new int[] { 0,
					0, 0 };
			((GridBagLayout) panel1.getLayout()).rowHeights = new int[] { 0, 0 };
			((GridBagLayout) panel1.getLayout()).columnWeights = new double[] {
					1.0, 0.0, 1.0E-4 };
			((GridBagLayout) panel1.getLayout()).rowWeights = new double[] {
					0.0, 1.0E-4 };

			// ---- addButton ----
			addButton.setText("Add");
			panel1.add(addButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel1, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));
		pack();
		setLocationRelativeTo(null);
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JLabel groupNameLabel;
	private JTextField groupNameTextField;
	private JLabel label1;
	private JPanel panel1;
	private JButton addButton;

	// JFormDesigner - End of variables declaration //GEN-END:variables

	public static void main(String[] args) {
		AddGroupWindow agw = new AddGroupWindow();
		agw.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == groupNameTextField) {
			CreateGroupRequest req = new CreateGroupRequest(
					groupNameTextField.getText());

		}

	}
}
