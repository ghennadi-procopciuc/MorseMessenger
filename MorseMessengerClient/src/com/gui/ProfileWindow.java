/*
 * Created by JFormDesigner on Tue May 01 21:21:03 EEST 2012
 */

package com.gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import sun.util.locale.StringTokenIterator;

import com.client.GUIThread;
import com.netdata.ChangeProfileRequest;
import com.netdata.ShowProfileRequest;
import com.netdata.ShowProfileResponse;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @author Ghennadi
 */
public class ProfileWindow extends JFrame implements ActionListener,
		MouseListener {

	private JPanel panel1;
	private JLabel usernameLabel;
	private JLabel avatarLabel;
	private JButton uploadButton;
	private JPanel panel5;
	private JLabel label7;
	private JTextField nameTextField;
	private JLabel label8;
	private JTextField surnameTextField;
	private JLabel label9;
	private JTextField birthDateTextField;
	private JLabel label10;
	private JTextField countryTextField;
	private JLabel label18;
	private JTextField hobbiesTextField;
	private JLabel label11;
	private JTextField newPasswordTextField;
	private JLabel label15;
	private JTextField passwordConfirmationTextField;
	private JLabel label12;
	private JTextField passwordTextField;
	private JPanel panel2;
	private JButton saveButton;

	private JPanel loadingBarPanel;
	private JProgressBar progressBar1;

	private GUIThread guiThread;

	public ProfileWindow(GUIThread guiThread) {
		super("Profile");
		this.guiThread = guiThread;
		initComponents();
		getUserInfo();
		showLoadingBar();
	}

	private void initComponents() {
		panel1 = new JPanel();
		usernameLabel = new JLabel();
		avatarLabel = new JLabel();
		uploadButton = new JButton();
		panel5 = new JPanel();
		label7 = new JLabel();
		nameTextField = new JTextField();
		label8 = new JLabel();
		surnameTextField = new JTextField();
		label9 = new JLabel();
		birthDateTextField = new JTextField();
		label10 = new JLabel();
		countryTextField = new JTextField();
		label18 = new JLabel();
		hobbiesTextField = new JTextField();
		label11 = new JLabel();
		newPasswordTextField = new JTextField();
		label15 = new JLabel();
		passwordConfirmationTextField = new JTextField();
		label12 = new JLabel();
		passwordTextField = new JTextField();
		panel2 = new JPanel();
		saveButton = new JButton();

		// ======== this ========
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] {
				15, 0, 15, 0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 15,
				0, 0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				0.0, 0.0, 0.0, 1.0, 0.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				0.0, 1.0, 0.0, 0.0, 1.0E-4 };

		// ======== panel1 ========
		{
			panel1.setLayout(new GridBagLayout());
			((GridBagLayout) panel1.getLayout()).columnWidths = new int[] {
					100, 0 };
			((GridBagLayout) panel1.getLayout()).rowHeights = new int[] { 0,
					105, 0, 0, 0 };
			((GridBagLayout) panel1.getLayout()).columnWeights = new double[] {
					0.0, 1.0E-4 };
			((GridBagLayout) panel1.getLayout()).rowWeights = new double[] {
					0.0, 0.0, 0.0, 1.0, 1.0E-4 };

			// ---- usernameLabel ----
			usernameLabel.setText("<html><center>Username</center></html>");
			usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			usernameLabel.setFont(usernameLabel.getFont().deriveFont(
					usernameLabel.getFont().getStyle() | Font.BOLD));
			panel1.add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

			// ---- avatarLabel ----
			panel1.add(avatarLabel, new GridBagConstraints(0, 1, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));

			// ---- uploadButton ----
			uploadButton.setText("Upload");
			uploadButton.addActionListener(this);
			panel1.add(uploadButton, new GridBagConstraints(0, 2, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 0), 0, 0));
		}
		contentPane.add(panel1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));

		// ======== panel5 ========
		{
			panel5.setLayout(new GridBagLayout());
			((GridBagLayout) panel5.getLayout()).columnWidths = new int[] { 0,
					0, 0, 0, 0 };
			((GridBagLayout) panel5.getLayout()).rowHeights = new int[] { 0, 0,
					0, 15, 0, 0, 0 };
			((GridBagLayout) panel5.getLayout()).columnWeights = new double[] {
					0.0, 1.0, 0.0, 1.0, 1.0E-4 };
			((GridBagLayout) panel5.getLayout()).rowWeights = new double[] {
					0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };

			// ---- label7 ----
			label7.setText("Name :");
			panel5.add(label7, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel5.add(nameTextField, new GridBagConstraints(1, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

			// ---- label8 ----
			label8.setText("Surname :");
			panel5.add(label8, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel5.add(surnameTextField, new GridBagConstraints(3, 0, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

			// ---- label9 ----
			birthDateTextField.addMouseListener(this);
			label9.setText("Birth date :");
			panel5.add(label9, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel5.add(birthDateTextField, new GridBagConstraints(1, 1, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

			// ---- label10 ----
			label10.setText("Country :");
			panel5.add(label10, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel5.add(countryTextField, new GridBagConstraints(3, 1, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

			// ---- label18 ----
			label18.setText("Hobbies :");
			panel5.add(label18, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel5.add(hobbiesTextField, new GridBagConstraints(1, 2, 3, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

			// ---- label11 ----
			label11.setText("<html><center>New<br>password :</center></html>");
			panel5.add(label11, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel5.add(newPasswordTextField, new GridBagConstraints(1, 4, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

			// ---- label15 ----
			label15.setText("<html><center>New<br>password :</center></html>");
			panel5.add(label15, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));
			panel5.add(passwordConfirmationTextField, new GridBagConstraints(3,
					4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

			// ---- label12 ----
			label12.setText("Password :");
			panel5.add(label12, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));
			panel5.add(passwordTextField, new GridBagConstraints(1, 5, 1, 1,
					0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
		}
		contentPane.add(panel5, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));

		// ======== panel2 ========
		{
			panel2.setLayout(new GridBagLayout());
			((GridBagLayout) panel2.getLayout()).columnWidths = new int[] { 0,
					0, 0 };
			((GridBagLayout) panel2.getLayout()).rowHeights = new int[] { 0, 0 };
			((GridBagLayout) panel2.getLayout()).columnWeights = new double[] {
					1.0, 0.0, 1.0E-4 };
			((GridBagLayout) panel2.getLayout()).rowWeights = new double[] {
					0.0, 1.0E-4 };

			// ---- saveButton ----
			saveButton.setText("Save");
			saveButton.addActionListener(this);
			panel2.add(saveButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(panel2, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 5, 5), 0, 0));
		setSize(475, 275);
		setLocationRelativeTo(getOwner());

	}

	private void getUserInfo() {
		ShowProfileRequest spr = new ShowProfileRequest(guiThread
				.getMainWindow().getLoginDataResponse().getUsername());
		System.out.println("USERNAME :::::: "
				+ guiThread.getMainWindow().getLoginDataResponse()
						.getUsername());
		guiThread.getwThread().unblock(spr);
	}

	public void setUsername(String username) {
		usernameLabel.setText(username);
	}

	public void setAvatar(ImageIcon avatar) {
		avatarLabel.setIcon(avatar);
	}

	public void setName(String name) {
		nameTextField.setText(name);
	}

	public void setSurname(String surname) {
		surnameTextField.setText(surname);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == uploadButton) {
			uploadAvatarAction();
		}

		if (e.getSource() == saveButton) {
			saveAction();
		}
	}

	public static byte[] serializeObj(Object obj) throws IOException {
		ByteArrayOutputStream baOStream = new ByteArrayOutputStream();
		ObjectOutputStream objOStream = new ObjectOutputStream(baOStream);

		objOStream.writeObject(obj); // object must be serializable
		objOStream.flush();
		objOStream.close();
		return baOStream.toByteArray(); // returns stream as byte array
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

	private void saveAction() {
		System.out.println("Save action");
		Long birthdate;
		if (birthDateTextField.getText().trim().length() == 0) {
			birthdate = (long) 0;
		} else {
			birthdate = getBirthDate().getTime();
		}

		ArrayList<String> hobbies = new ArrayList<String>();
		StringTokenIterator tk = new StringTokenIterator(
				hobbiesTextField.getText(), " ,./\\|;");
		while (tk.hasNext()) {
			String s = tk.next();

			if (!s.isEmpty()) {
				hobbies.add(s);
			}
		}

		String newPassword;
		if (passwordConfirmationTextField.getText().trim().length() == 0) {
			newPassword = passwordTextField.getText();
		} else {
			newPassword = passwordConfirmationTextField.getText();
		}

		if (passwordTextField.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this, "Password field is empty !!!",
					"Info", JOptionPane.INFORMATION_MESSAGE, null);
			return;
		}

		if (newPasswordTextField.getText().compareTo(
				passwordConfirmationTextField.getText()) != 0
				&& newPasswordTextField.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "The passwords don't match !!",
					"Info", JOptionPane.INFORMATION_MESSAGE, null);
			return;

		}

		ChangeProfileRequest cpr = null;
		try {
			cpr = new ChangeProfileRequest(usernameLabel.getText(),
					countryTextField.getText(), nameTextField.getText(),
					surnameTextField.getText(), birthdate,
					serializeObj(avatarLabel.getIcon()), hobbies,
					passwordTextField.getText(), newPassword);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		guiThread.getwThread().unblock(cpr);
	}

	private void uploadAvatarAction() {
		System.out.println("Upload");

		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setFileFilter(new ExtensionFileFilter("Image file",
				new String[] { "JPG", "JPEG", "GIF", "PNG" }));

		fileChooser.setDialogTitle("Open Image");

		int status = fileChooser.showOpenDialog(this);

		if (status != JFileChooser.APPROVE_OPTION)
			return;

		ImageIcon image = new ImageIcon(fileChooser.getSelectedFile()
				.getAbsolutePath().toString());
		Image img = image.getImage();
		Image newimg = img.getScaledInstance(100, 100,
				java.awt.Image.SCALE_SMOOTH);
		avatarLabel.setIcon(new ImageIcon(newimg));
		// TextField.setText(fileChooser.getSelectedFile().getAbsolutePath()
		// .toString());
	}

	public void showLoadingBar() {
		for (int i = 0; i < this.getContentPane().getComponentCount(); i++) {
			this.getContentPane().getComponent(i).setVisible(false);
		}

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

	}

	public void showContent() {
		for (int i = 0; i < this.getContentPane().getComponentCount(); i++) {
			this.getContentPane().getComponent(i).setVisible(false);
		}

		initComponents();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == birthDateTextField) {
			System.out.println("AM AJUNS");
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

	public static InputStream scaleImage(InputStream p_image, int p_width,
			int p_height) throws Exception {

		InputStream imageStream = new BufferedInputStream(p_image);
		Image image = (Image) ImageIO.read(imageStream);

		int thumbWidth = p_width;
		int thumbHeight = p_height;

		// Make sure the aspect ratio is maintained, so the image is not skewed
		double thumbRatio = (double) thumbWidth / (double) thumbHeight;
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		double imageRatio = (double) imageWidth / (double) imageHeight;
		if (thumbRatio < imageRatio) {
			thumbHeight = (int) (thumbWidth / imageRatio);
		} else {
			thumbWidth = (int) (thumbHeight * imageRatio);
		}

		// Draw the scaled image
		BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);

		// Write the scaled image to the outputstream
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
		int quality = 100; // Use between 1 and 100, with 100 being highest
							// quality
		quality = Math.max(0, Math.min(quality, 100));
		param.setQuality((float) quality / 100.0f, false);
		encoder.setJPEGEncodeParam(param);
		encoder.encode(thumbImage);
		ImageIO.write(thumbImage, "JPG", out);

		// Read the outputstream into the inputstream for the return value
		ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());

		return bis;
	}

	public void setData(ShowProfileResponse showProfileResponse) {
		showContent();

		System.out.println("Am afisat contetul !!!!");
		usernameLabel.setText(showProfileResponse.getUsername());
		System.out.println("Username : " + showProfileResponse.getUsername());

		if (showProfileResponse.getAvatar() == null) {
			System.out.println("NULL here");
			avatarLabel.setIcon(new ImageIcon(
					Resources.DEFAULT_BIG_AVATAR_IMAGE));
		} else {
			System.out.println("Not NULL here");
			avatarLabel.setIcon(new ImageIcon(showProfileResponse.getAvatar()));
		}

		nameTextField.setText(showProfileResponse.getName());
		surnameTextField.setText(showProfileResponse.getSurname());

		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");

		if (showProfileResponse.getBirthDate() != null) {

			birthDateTextField.setText(format.format(new Date(
					showProfileResponse.getBirthDate())));
		}

		countryTextField.setText(showProfileResponse.getLocation());

		String hobbies = "";

		if (showProfileResponse.getHobbies() != null) {

			for (String hobby : showProfileResponse.getHobbies()) {
				hobbies += hobby + ", ";
			}

			if (hobbies.length() >= 2) {
				hobbies = hobbies.substring(0, hobbies.length() - 2);
				hobbiesTextField.setText(hobbies);
			}
		}
	}

	public static void main(String[] args) {
		GUISettings.getSettings();

		ProfileWindow pw = new ProfileWindow(null);
		pw.showLoadingBar();
		pw.showContent();
		pw.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pw.setUsername("unix140");
		pw.setAvatar(new ImageIcon("avatar1.jpg"));
		pw.setVisible(true);
	}
}
