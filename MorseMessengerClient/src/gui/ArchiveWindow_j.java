/*
 * Created by JFormDesigner on Tue May 01 12:43:48 EEST 2012
 */

package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

/**
 * @author Ghennadi
 */
public class ArchiveWindow_j extends JFrame {
	public ArchiveWindow_j() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		scrollPane1 = new JScrollPane();
		friendList = new JList();
		progressBarPanel = new JPanel();
		progressBar = new JProgressBar();

		// ======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] {
				15, 167, 440, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 15,
				330, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				0.0, 0.0, 1.0, 0.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				0.0, 1.0, 0.0, 1.0E-4 };

		// ======== scrollPane1 ========
		{

			// ---- friendList ----
			friendList.setBackground(new Color(214, 217, 223));
			scrollPane1.setViewportView(friendList);
		}
		contentPane.add(scrollPane1, new GridBagConstraints(1, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

		// ======== progressBarPanel ========
		{
			progressBarPanel.setLayout(new GridBagLayout());
			((GridBagLayout) progressBarPanel.getLayout()).columnWidths = new int[] {
					0, 206, 0, 0 };
			((GridBagLayout) progressBarPanel.getLayout()).rowHeights = new int[] {
					0, 0, 0, 0 };
			((GridBagLayout) progressBarPanel.getLayout()).columnWeights = new double[] {
					1.0, 0.0, 1.0, 1.0E-4 };
			((GridBagLayout) progressBarPanel.getLayout()).rowWeights = new double[] {
					1.0, 0.0, 1.0, 1.0E-4 };

			// ---- progressBar ----
			progressBar.setIndeterminate(true);
			progressBarPanel.add(progressBar, new GridBagConstraints(1, 1, 1,
					1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
		}
		contentPane.add(progressBarPanel, new GridBagConstraints(2, 1, 1, 1,
				0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JScrollPane scrollPane1;
	private JList friendList;
	private JPanel progressBarPanel;
	private JProgressBar progressBar;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
