/*
 * Created by JFormDesigner on Sat May 05 21:34:48 EEST 2012
 */

package gui;

import java.awt.*;
import javax.swing.*;

/**
 * @author Ghennadi
 */
public class MorseAlphabetWindow_2 extends JFrame {
	public MorseAlphabetWindow_2() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		scrollPane1 = new JScrollPane();
		panel1 = new JPanel();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {15, 143, 10, 0};
		((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {15, 227, 10, 0};
		((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
		((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

		//======== scrollPane1 ========
		{

			//======== panel1 ========
			{
				panel1.setLayout(new GridLayout(0, 1));
			}
			scrollPane1.setViewportView(panel1);
		}
		contentPane.add(scrollPane1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane scrollPane1;
	private JPanel panel1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
