/*
 * Created by JFormDesigner on Tue May 01 11:12:36 EEST 2012
 */

package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

/**
 * @author Ghennadi
 */
public class PreferencesWindow extends JFrame implements ActionListener {

	private JLabel label1;
	private JComboBox lookAndFeelComboBox;
	private JLabel label2;
	private JSpinner textSizeSpinner;
	private JPanel panel1;
	private JButton okButton;
	private UIManager.LookAndFeelInfo[] lookAndFeelInfos;

	public PreferencesWindow() {
		initComponents();

		getLookAndFeels();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		label1 = new JLabel();
		lookAndFeelComboBox = new JComboBox(getLookAndFeels());
		label2 = new JLabel();
		textSizeSpinner = new JSpinner();
		panel1 = new JPanel();
		okButton = new JButton();

		// ======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] {
				15, 0, 0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 15,
				0, 0, 0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				0.0, 0.0, 0.0, 0.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };

		// ---- label1 ----
		label1.setText("Look and feel :");
		contentPane.add(label1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));
		contentPane.add(lookAndFeelComboBox, new GridBagConstraints(2, 1, 1, 1,
				0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

		// ---- label2 ----
		label2.setText("Text size :");
		contentPane.add(label2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));
		contentPane.add(textSizeSpinner, new GridBagConstraints(2, 2, 1, 1,
				0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

		/* Setez spinner-ul */
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(UIManager
				.getDefaults().getFont("TabbedPane.font").getSize(),
				Resources.MIN_FONT_SIZE, Resources.MAX_FONT_SIZE, 1);
		textSizeSpinner.setModel(spinnerModel);

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

			// ---- okButton ----
			okButton.setText("OK");
			okButton.addActionListener(this);
			panel1.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel1, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));
		pack();
		setLocationRelativeTo(null);
	}

	private String[] getLookAndFeels() {
		lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
		String[] lookAndFeelsNames = new String[lookAndFeelInfos.length];
		for (int i = 0; i < lookAndFeelInfos.length; i++) {
			UIManager.LookAndFeelInfo lookAndFeelInfo = lookAndFeelInfos[i];

			String name = lookAndFeelInfo.getName();
			lookAndFeelsNames[i] = name;

			String className = lookAndFeelInfo.getClassName();
		}

		return lookAndFeelsNames;
	}

	public static void main(String[] args) {
		new PreferencesWindow().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			okAction();
		}
	}

	private void okAction() {
		/* Salvez setarile in fiser */
		GUISettings.saveSettings(
				UIManager.getDefaults().getFont("TabbedPane.font"),
				(int) textSizeSpinner.getValue(),
				lookAndFeelInfos[lookAndFeelComboBox.getSelectedIndex()]
						.getClassName());
	}
}
