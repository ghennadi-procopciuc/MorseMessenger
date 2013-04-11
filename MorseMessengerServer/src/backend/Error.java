package backend;


import java.awt.Toolkit;

import javax.swing.JOptionPane;

/**
 * @author Ghennadi Procopciuc
 */

public class Error extends JOptionPane {
	
	private static final long serialVersionUID = 1L;
	
	public Error(){
		Toolkit.getDefaultToolkit().beep();
		showMessageDialog(null, "", "Eroare" , ERROR_MESSAGE);
	}
	
	public Error(String message){
		Toolkit.getDefaultToolkit().beep();
		showMessageDialog(null, message, "Eroare" , ERROR_MESSAGE);
	}
}


