package com.client;

import com.gui.LoginWindow;
import com.gui.MainWindow;
import com.gui.RegisterWindow;
import com.gui.RegisterWindow2;
import com.gui.UserData;
import com.netdata.ForgotPassword;
import com.netdata.FriendMW;
import com.netdata.LoginData;
import com.netdata.LoginDataResponse;
import com.netdata.RegisterStep1;
import com.netdata.RegisterStep1Response;
import com.netdata.SignOutNotification;

public class GUIThread extends Thread {

	private WriteThread wThread;
	private MorseClient morseClient;
	private LoginWindow loginWindow;
	private MainWindow mainWindow;
	private RegisterWindow registerWindow;
	private RegisterWindow2 registerWindow2;
	private UserData userData;
	private FriendMW self;

	public FriendMW getSelf() {
		return self;
	}

	public void setSelf(FriendMW self) {
		this.self = self;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	public GUIThread(WriteThread wThread, MorseClient morseClient) {
		this.wThread = wThread;
		this.morseClient = morseClient;

		loginWindow = new LoginWindow(this);
		loginWindow.setVisible(true);
	}

	public void step1Request() {
		/**
		 * Register experiment
		 */
		System.out.println("[GUIThread] Begin register experiment");
		RegisterStep1 rs1 = new RegisterStep1("ghennadi", "password1",
				"password2", "ghennadi@mail.com");
		wThread.unblock(rs1);
	}

	public void loginRequest() {
		/**
		 * Login experiment
		 */
		System.out.println("[GUIThread] Begin login experiment");
		LoginData lData = new LoginData("Liviu", "parola");
		wThread.unblock(lData);
	}

	public void newPassRequest() {
		/**
		 * Forgot password experiment
		 */
		System.out.println("[GUIThread] Begin Forgot password experiment");
		ForgotPassword fp = new ForgotPassword("xkpablo@yahoo.com");
		wThread.unblock(fp);
	}

	public void step1Response(RegisterStep1Response recvData) {
		if (recvData.getValid() == true) {
			registerWindow.dispose();
			new RegisterWindow2(this).setVisible(true);
			// TODO Lanseaza al 2-lea pas
		} else {
			registerWindow.setError("Something is wrong :(");
		}

	}

	public void loginResponse(LoginDataResponse recvData) {
		System.out.println("[GUIThread] LoginDataResponse: " + recvData);

		loginWindow.setVisible(false);
		loginWindow.dispose();
		userData = new UserData(recvData.getUsername(),
				recvData.getCompleteName());
		System.out.println("CREEZ INTERFATA");

		self = recvData.getSelf();
		mainWindow = new MainWindow(recvData, this);
	}

	public void initClient() {
		// loginWindow.dispose();
		morseClient.initClient();
	}

	public void signOut() {
		System.out.println("[GUIThread] Sign out ");
		mainWindow.dispose();
		System.out.println("[GUIThread] Trimit pachet cu SignOut");
		getwThread().unblock(
				new SignOutNotification(mainWindow.getLoginDataResponse()
						.getUsername()));
		System.out.println("username : "
				+ mainWindow.getLoginDataResponse().getUsername());
		loginWindow = new LoginWindow(this);
		loginWindow.setVisible(true);
	}

	public WriteThread getwThread() {
		return wThread;
	}

	public void setwThread(WriteThread wThread) {
		this.wThread = wThread;
	}

	public LoginWindow getLoginWindow() {
		return loginWindow;
	}

	public void setLoginWindow(LoginWindow loginWindow) {
		this.loginWindow = loginWindow;
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	public RegisterWindow getRegisterWindow() {
		return registerWindow;
	}

	public void setRegisterWindow(RegisterWindow registerWindow) {
		this.registerWindow = registerWindow;
	}

	public RegisterWindow2 getRegisterWindow2() {
		return registerWindow2;
	}

	public void setRegisterWindow2(RegisterWindow2 registerWindow2) {
		this.registerWindow2 = registerWindow2;
	}
}
