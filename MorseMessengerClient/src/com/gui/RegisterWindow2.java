/*
 * Created by JFormDesigner on Mon Apr 30 12:38:21 EEST 2012
 */

package com.gui;

import gui.CalendarWindow;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.client.GUIThread;
import com.netdata.RegisterStep2;

/**
 * @author Ghennadi
 */
public class RegisterWindow2 extends JFrame implements ActionListener,
		MouseListener {
	private JPanel panel1;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JLabel surnameLabel;
	private JTextField surnameTextField;
	private JLabel birthDateLabel;
	private JTextField birthDateTextField;
	private JLabel hobbiesLabel;
	private JTextField hobbiesTextField;
	private JPanel panel2;
	private JLabel avatarLabel;
	private JTextField avatarTextField;
	private JLabel countryLabel;
	private JLabel errorLabel;
	private JTextField countryTextField;
	private JButton browserButton;
	private JButton finishButton;
	private Date birthDate;

	private GUIThread guiThread;

	public RegisterWindow2(GUIThread guiThread) {
		super("Register");
		this.guiThread = guiThread;
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		panel1 = new JPanel();
		nameLabel = new JLabel();
		nameTextField = new JTextField();
		surnameLabel = new JLabel();
		surnameTextField = new JTextField();
		birthDateLabel = new JLabel();
		birthDateTextField = new JTextField();
		hobbiesLabel = new JLabel();
		hobbiesTextField = new JTextField();
		countryLabel = new JLabel();
		countryTextField = new JTextField();
		panel2 = new JPanel();
		avatarLabel = new JLabel();
		avatarTextField = new JTextField();
		browserButton = new JButton();
		errorLabel = new JLabel();
		finishButton = new JButton();

		// ======== this ========
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] {
				15, 0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 15,
				0, 0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				0.0, 1.0, 0.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				0.0, 0.0, 0.0, 0.0, 1.0E-4 };

		// ======== panel1 ========
		{
			panel1.setLayout(new GridBagLayout());
			((GridBagLayout) panel1.getLayout()).columnWidths = new int[] { 0,
					0, 0, 0, 0 };
			((GridBagLayout) panel1.getLayout()).rowHeights = new int[] { 0, 0,
					0, 0 };
			((GridBagLayout) panel1.getLayout()).columnWeights = new double[] {
					0.0, 1.0, 0.0, 1.0, 1.0E-4 };
			((GridBagLayout) panel1.getLayout()).rowWeights = new double[] {
					0.0, 0.0, 0.0, 1.0E-4 };

			// ---- nameLabel ----
			nameLabel.setText("Name :");
			panel1.add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel1.add(nameTextField, new GridBagConstraints(1, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

			// ---- surnameLabel ----
			surnameLabel.setText("Surname :");
			panel1.add(surnameLabel, new GridBagConstraints(2, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel1.add(surnameTextField, new GridBagConstraints(3, 0, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

			// ---- birthDateLabel ----
			birthDateLabel.setText("Birth date :");
			panel1.add(birthDateLabel, new GridBagConstraints(0, 1, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			birthDateTextField.addMouseListener(this);
			panel1.add(birthDateTextField, new GridBagConstraints(1, 1, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

			// ---- hobbiesLabel ----
			hobbiesLabel.setText("Hobbies :");
			panel1.add(hobbiesLabel, new GridBagConstraints(2, 1, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel1.add(hobbiesTextField, new GridBagConstraints(3, 1, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

			// ---- countryLabel ----
			countryLabel.setText("Country :");
			panel1.add(countryLabel, new GridBagConstraints(0, 2, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));
			panel1.add(countryTextField, new GridBagConstraints(1, 2, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
		}
		contentPane.add(panel1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));

		// ======== panel2 ========
		{
			panel2.setLayout(new GridBagLayout());
			((GridBagLayout) panel2.getLayout()).columnWidths = new int[] { 0,
					0, 0, 0 };
			((GridBagLayout) panel2.getLayout()).rowHeights = new int[] { 0,
					15, 0, 0 };
			((GridBagLayout) panel2.getLayout()).columnWeights = new double[] {
					0.0, 1.0, 0.0, 1.0E-4 };
			((GridBagLayout) panel2.getLayout()).rowWeights = new double[] {
					0.0, 0.0, 0.0, 1.0E-4 };

			// ---- avatarLabel ----
			avatarLabel.setText("Avatar :      ");
			panel2.add(avatarLabel, new GridBagConstraints(0, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			avatarTextField.setEditable(false);
			panel2.add(avatarTextField, new GridBagConstraints(1, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

			// ---- browserButton ----
			browserButton.setText("Browse");
			browserButton.addActionListener(this);
			panel2.add(browserButton, new GridBagConstraints(2, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

			// ---- errorLabel ----
			errorLabel.setText("text");
			errorLabel.setVisible(false);
			errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
			panel2.add(errorLabel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

			// ---- finishButton ----
			finishButton.setText("Finish");
			finishButton.addActionListener(this);
			panel2.add(finishButton, new GridBagConstraints(2, 2, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));
		setSize(400, 215);
		setLocationRelativeTo(null);
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents
	}

	public void setError(String text) {
		errorLabel.setText("<html><center><font color=\"red\">" + text
				+ "</font></center></html>");
		errorLabel.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == birthDateTextField) {
			new CalendarWindow(birthDateTextField);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == browserButton) {
			browseAction();
		}

		if (e.getSource() == finishButton) {
			finishAction();
		}
	}

	private void browseAction() {
		System.out.println("Send file Action");

		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setFileFilter(new ExtensionFileFilter("Image file",
				new String[] { "JPG", "JPEG", "GIF", "PNG" }));

		fileChooser.setDialogTitle("Open Image");

		int status = fileChooser.showOpenDialog(this);

		if (status != JFileChooser.APPROVE_OPTION)
			return;

		avatarTextField.setText(fileChooser.getSelectedFile().getAbsolutePath()
				.toString());
		System.out.println("Selected file : "
				+ fileChooser.getSelectedFile().getAbsolutePath().toString());
	}

	private Date getBirthDate() {
		String year;
		String month;
		String day;

		StringTokenizer tk = new StringTokenizer(birthDateTextField.getText(),
				"-");
		year = tk.nextToken();
		month = tk.nextToken();
		day = tk.nextToken();

		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(year), Integer.parseInt(month),
				Integer.parseInt(day));

		return calendar.getTime();
	}

	private void finishAction() {
		ArrayList<String> hobbies = new ArrayList<String>();
		File file;
		byte[] buf = null;

		if (birthDateTextField.getText().trim().length() != 0) {
			birthDate = getBirthDate();
		}

		// Name and surname
		{
			if (nameTextField.getText().trim().length() == 0
					|| surnameTextField.getText().trim().length() == 0) {
				setError("Name and surname are mandatory fields");
				return;
			}
		}

		// Hobbies
		{
			StringTokenizer tk = new StringTokenizer(
					hobbiesTextField.getText(), " ;,.\t");

			while (tk.hasMoreTokens()) {
				hobbies.add(tk.nextToken());
			}
		}

		// Avatarul
		{
			if (avatarTextField.getText().trim().length() != 0) {
				file = new File(avatarTextField.getText());
				RandomAccessFile raf;
				try {
					raf = new RandomAccessFile(file, "r");
					buf = new byte[(int) file.length()];
					raf.readFully(buf);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// Country
		{
			if (countryTextField.getText().trim().length() != 0) {
				boolean accepted = false;
				for (int i = 0; i < Resources.countries.length; i++) {
					if (Resources.countries[i].toLowerCase().compareTo(
							countryTextField.getText().toLowerCase()) == 0) {
						accepted = true;
						countryTextField.setText(Resources.countries[i]);
						break;
					}
				}

				if (accepted == false) {
					setError("Wrong country name !");
					return;
				}
			}
		}

		// TODO creaza obiectul si trimite-l pe retea
		System.out.println("Finish");
		if (guiThread != null) {
			guiThread.getwThread().unblock(
					new RegisterStep2(nameTextField.getText(), surnameTextField
							.getText(), birthDate.getTime(), countryTextField
							.getText(), hobbies, buf));

		}
		dispose();
	}

	public static void main(String[] args) {
		RegisterWindow2 rg = new RegisterWindow2(null);
		rg.setDefaultCloseOperation(EXIT_ON_CLOSE);
		rg.setVisible(true);
	}
}
