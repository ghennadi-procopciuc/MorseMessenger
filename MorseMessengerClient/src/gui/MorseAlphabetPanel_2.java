/*
 * Created by JFormDesigner on Sat May 05 21:38:17 EEST 2012
 */

package gui;

import java.awt.*;
import javax.swing.*;

/**
 * @author Ghennadi
 */
public class MorseAlphabetPanel_2 extends JPanel {
	public MorseAlphabetPanel_2() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		asciiLabel = new JLabel();
		morseLabel = new JLabel();
		playButton = new JButton();

		//======== this ========
		setLayout(new GridBagLayout());
		((GridBagLayout)getLayout()).columnWidths = new int[] {0, 15, 0, 15, 0, 0};
		((GridBagLayout)getLayout()).rowHeights = new int[] {8, 0, 3, 0};
		((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
		((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

		//---- asciiLabel ----
		asciiLabel.setText("A");
		asciiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(asciiLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//---- morseLabel ----
		morseLabel.setText("..-.");
		morseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		morseLabel.setFont(new Font("sansserif", Font.BOLD, 22));
		add(morseLabel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//---- playButton ----
		playButton.setText("Play");
		add(playButton, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 0), 0, 0));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel asciiLabel;
	private JLabel morseLabel;
	private JButton playButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
