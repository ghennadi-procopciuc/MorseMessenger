/*
 * Created by JFormDesigner on Sat May 05 21:38:17 EEST 2012
 */

package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Ghennadi
 */
public class MorseAlphabetPanel extends JPanel implements ActionListener {
	// private JLabel asciiLabel;
	// private JLabel morseLabel;
	// private JButton playButton;
	private MorseSound sound;

	public MorseAlphabetPanel(String asciiCode, String morseCode) {
		initComponents();

		if (asciiCode.compareTo(" ") == 0) {
			asciiLabel.setText("' '");
		} else {
			asciiLabel.setText(asciiCode);
		}
		morseLabel.setText(morseCode);
		sound = new MorseSound(morseCode);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		asciiLabel = new JLabel();
		morseLabel = new JLabel();
		playButton = new JButton();

		// ======== this ========
		setLayout(new GridBagLayout());
		((GridBagLayout) getLayout()).columnWidths = new int[] { 27, 15, 48,
				15, 0, 0 };
		((GridBagLayout) getLayout()).rowHeights = new int[] { 8, 0, 3, 0 };
		((GridBagLayout) getLayout()).columnWeights = new double[] { 0.0, 0.0,
				0.0, 0.0, 0.0, 1.0E-4 };
		((GridBagLayout) getLayout()).rowWeights = new double[] { 0.0, 0.0,
				0.0, 1.0E-4 };

		// ---- asciiLabel ----
		asciiLabel.setText("A");
		asciiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(asciiLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));

		// ---- morseLabel ----
		morseLabel.setText("..-.");
		morseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		morseLabel.setFont(new Font("sansserif", Font.BOLD, 22));
		add(morseLabel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));

		// ---- playButton ----
		playButton.setText("Play");
		playButton.addActionListener(this);
		add(playButton, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 0), 0, 0));
		// //GEN-END:initComponents
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playButton) {
			sound.playSample();
		}
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JLabel asciiLabel;
	private JLabel morseLabel;
	private JButton playButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
