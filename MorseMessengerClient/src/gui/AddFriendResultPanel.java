/*
 * Created by JFormDesigner on Mon Apr 30 17:08:39 EEST 2012
 */

package gui;

import java.awt.*;
import javax.swing.*;

/**
 * @author Ghennadi
 */
public class AddFriendResultPanel extends JFrame {
	public AddFriendResultPanel() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		mainPanel = new JPanel();
		scrollPane = new JScrollPane();
		resultTable = new JTable();
		panel2 = new JPanel();
		button1 = new JButton();
		findButton = new JButton();

		//======== this ========
		setVisible(true);
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {15, 0, 10, 0};
		((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {15, 0, 10, 0};
		((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
		((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

		//======== mainPanel ========
		{
			mainPanel.setLayout(new GridBagLayout());
			((GridBagLayout)mainPanel.getLayout()).columnWidths = new int[] {0, 0};
			((GridBagLayout)mainPanel.getLayout()).rowHeights = new int[] {170, 0, 0};
			((GridBagLayout)mainPanel.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
			((GridBagLayout)mainPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

			//======== scrollPane ========
			{

				//---- resultTable ----
				resultTable.setAutoCreateRowSorter(true);
				resultTable.setCellSelectionEnabled(true);
				scrollPane.setViewportView(resultTable);
			}
			mainPanel.add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 0), 0, 0));

			//======== panel2 ========
			{
				panel2.setLayout(new GridBagLayout());
				((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
				((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0};
				((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 1.0E-4};
				((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

				//---- button1 ----
				button1.setText("Cancel");
				panel2.add(button1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

				//---- findButton ----
				findButton.setText("Finish");
				panel2.add(findButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
					new Insets(0, 0, 0, 0), 0, 0));
			}
			mainPanel.add(panel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(mainPanel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));
		setSize(555, 245);
		setLocationRelativeTo(null);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel mainPanel;
	private JScrollPane scrollPane;
	private JTable resultTable;
	private JPanel panel2;
	private JButton button1;
	private JButton findButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
