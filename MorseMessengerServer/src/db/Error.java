package db;

import java.awt.Toolkit;

import javax.swing.JOptionPane;

/**
 * @author Ghennadi Procopciuc
 */

public class Error extends JOptionPane {
	
	private static final long serialVersionUID = 1L;
	
	public Error(){
//       javax.swing.SwingUtilities.invokeLater(new Runnable() {
//           public void run() {
        		Toolkit.getDefaultToolkit().beep();
        		showMessageDialog(null, "", "Eroare" , ERROR_MESSAGE);
//            }
//        });	

	}
	
	public Error(final String message){
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
        		Toolkit.getDefaultToolkit().beep();
        		showMessageDialog(null, message, "Eroare" , ERROR_MESSAGE);
//            }
//        });	
	}
}


