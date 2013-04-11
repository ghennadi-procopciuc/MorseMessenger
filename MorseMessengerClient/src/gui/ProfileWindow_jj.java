/*
 * Created by JFormDesigner on Thu May 17 22:38:53 EEST 2012
 */

package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * @author Ghennadi
 */
public class ProfileWindow_jj extends JFrame {
	public ProfileWindow_jj() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		loadingBarPanel = new JPanel();
		progressBar1 = new JProgressBar();

		// ======== this ========
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] {
				15, 0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 0,
				0, 0, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				0.0, 1.0, 0.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				1.0, 0.0, 1.0, 1.0E-4 };

		// ======== loadingBarPanel ========
		{
			loadingBarPanel.setLayout(new GridBagLayout());
			((GridBagLayout) loadingBarPanel.getLayout()).columnWidths = new int[] {
					0, 0 };
			((GridBagLayout) loadingBarPanel.getLayout()).rowHeights = new int[] {
					24, 0 };
			((GridBagLayout) loadingBarPanel.getLayout()).columnWeights = new double[] {
					1.0, 1.0E-4 };
			((GridBagLayout) loadingBarPanel.getLayout()).rowWeights = new double[] {
					0.0, 1.0E-4 };

			// ---- progressBar1 ----
			progressBar1.setIndeterminate(true);
			loadingBarPanel.add(progressBar1, new GridBagConstraints(0, 0, 1,
					1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(loadingBarPanel, new GridBagConstraints(1, 1, 1, 1,
				0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));
		setSize(475, 275);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JPanel loadingBarPanel;
	private JProgressBar progressBar1;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
