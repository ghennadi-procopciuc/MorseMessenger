package db;

import java.awt.Toolkit;

import javax.swing.JOptionPane;

/**
 * @author Ghennadi Procopciuc
 * 
 */

public class Warning extends JOptionPane {
	
	private static final long serialVersionUID = 1L;
	
	Warning(){
		Toolkit.getDefaultToolkit().beep();
		showMessageDialog(null, "", "Aten\u021bie" , WARNING_MESSAGE );
	}
	
	public Warning(String message){
		Toolkit.getDefaultToolkit().beep();
		showMessageDialog(null, message, "Aten\u021bie" , WARNING_MESSAGE );
	}
}
