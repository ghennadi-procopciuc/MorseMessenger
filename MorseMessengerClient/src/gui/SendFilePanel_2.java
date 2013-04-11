/*
 * Created by JFormDesigner on Sat May 05 19:40:51 EEST 2012
 */

package gui;

import java.awt.*;
import javax.swing.*;

/**
 * @author Ghennadi
 */
public class SendFilePanel_2 extends JPanel {
	public SendFilePanel_2() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		usernameLabel = new JLabel();
		progressBar = new JProgressBar();
		pathLabel = new JLabel();
		stateLabel = new JLabel();

		//======== this ========
		setLayout(new GridBagLayout());
		((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 0, 20, 0};
		((GridBagLayout)getLayout()).rowHeights = new int[] {7, 25, 2, 0};
		((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
		((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

		//---- usernameLabel ----
		usernameLabel.setText("Username");
		usernameLabel.setFont(usernameLabel.getFont().deriveFont(usernameLabel.getFont().getStyle() | Font.BOLD));
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(usernameLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//---- progressBar ----
		progressBar.setIndeterminate(true);
		add(progressBar, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//---- pathLabel ----
		pathLabel.setText("Path");
		add(pathLabel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//---- stateLabel ----
		stateLabel.setText("text");
		add(stateLabel, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 0), 0, 0));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel usernameLabel;
	private JProgressBar progressBar;
	private JLabel pathLabel;
	private JLabel stateLabel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
