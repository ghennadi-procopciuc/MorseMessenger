/*
 * Created by JFormDesigner on Sat May 05 21:34:48 EEST 2012
 */

package com.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * @author Ghennadi
 */
public class MorseAlphabetWindow extends JFrame {
	private JScrollPane scrollPane1;
	private JPanel panel1;

	public MorseAlphabetWindow() {
		initComponents();

		setTitle("Morse alphabet");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		scrollPane1 = new JScrollPane();
		panel1 = new JPanel();

		// ======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] {
				15, 143, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 15,
				227, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				0.0, 0.0, 0.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				0.0, 0.0, 0.0, 1.0E-4 };

		// ======== scrollPane1 ========
		{

			// ======== panel1 ========
			{
				panel1.setLayout(new GridLayout(0, 1));

				for (int i = 0; i < Resources.morseCodes.length; i++) {
					panel1.add(new MorseAlphabetPanel(Resources.asciiCodes[i],
							Resources.morseCodes[i]));
				}
			}
			scrollPane1.setPreferredSize(new Dimension(180, 200));
			scrollPane1.setViewportView(panel1);
		}
		contentPane.add(scrollPane1, new GridBagConstraints(1, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));
		pack();
		setPreferredSize(new Dimension(180, 200));
		setLocationRelativeTo(getOwner());
	}

	public static void main(String[] args) {
		GUISettings.getSettings();
		MorseAlphabetWindow maw = new MorseAlphabetWindow();

		maw.setDefaultCloseOperation(EXIT_ON_CLOSE);
		maw.setVisible(true);
	}
}
