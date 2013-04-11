package db;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.ImageIcon;

import com.netdata.ArchiveMessage;
import com.netdata.ChangeProfileRequest;
import com.netdata.CreateGroupRequest;
import com.netdata.FriendMW;
import com.netdata.FriendSearch;
import com.netdata.LoginData;
import com.netdata.MoveFriendRequest;
import com.netdata.ShowProfileResponse;
import com.netdata.RegisterStep1;
import com.netdata.RegisterStep2;
import com.netdata.UserInfoData;
import com.netdata.WorldWindData;


public class TestClass
{
	public static DBFront dbFront;
	public static LoginData ld;
	public static Random r;
	
	public TestClass(String username, String pass) {
		ld = new LoginData(username, pass);
		dbFront = new DBFront();
		r = new Random();
	}
	
	public void testProfile() {
		
		
		System.out.println(dbFront.dbGetGroup("nimeni2", "xkpablo"));
		
		long l = 24234324;
		
		/*
		ChangeProfileRequest opt = new ChangeProfileRequest
				("andrei.epure", 
						"Ghwoeh", 
						"Jimy",
						l,
						"GL", 
						null,
						null,
						null,
						null);
		
		dbFront.dbSetProfile(opt);
		*/
	}

	public void testRegister(String username, String mail) {
		ArrayList<String> al = new ArrayList<>();
		byte[] byteArray = new byte[] {87, 79, 87, 46, 46, 46};
		al.add("cladiri"); al.add("bucuresti");
		RegisterStep1 one = new RegisterStep1(username, "save", "save", mail);
		RegisterStep2 two = new RegisterStep2("John", "Wayine", (long)11111111, 
				"USA", al, byteArray);
		dbFront.dbRegisterUser(one, two);
	}
	
	public void testAuth() {
		Boolean res;
		/* auth */
		res = dbFront.dbUserAuth(ld);
		if (!res) {
			System.out.println(res);
		}

	}
	
	public void testWorldWind() 
	{
		ArrayList<WorldWindData> wwd;
		
		
		try {
			dbFront.dbAddLabel(new WorldWindData(ld.getUsername(), 
					r.nextFloat(), 
					r.nextFloat(), 
					"RANDOM", 
					WorldWindData.LABEL));
			dbFront.dbAddPin(new WorldWindData(ld.getUsername(),
					r.nextFloat(),
					r.nextFloat(), 
					"100", 
					WorldWindData.PIN));
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wwd = dbFront.dbGetPins();
		for (int j = 0; j < wwd.size(); j++) {
			System.out.println(wwd.get(j));
		}
		
		try {
			wwd = dbFront.dbGetLabels();
			
			for (int j = 0; j < wwd.size(); j++) {
				System.out.println(wwd.get(j));
			}
		}
		catch (InvalidNameException ine) {
			ine.printStackTrace();
		}
		
		try {
			wwd = dbFront.dbGetLabels();
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int j = 0; j < wwd.size(); j++) {
			System.out.println(wwd.get(j));
		}

	}
	
	public void testStrangers()
	{
		ArrayList<UserInfoData> strangers;
		strangers = dbFront.dbGetStrangers(new FriendSearch(R.NULL_VALUE,"NCIT", "trolling", "Emil", "Heri", null));
		for (int i = 0; i < strangers.size(); i++) {
			System.out.println(strangers.get(i));
		}
	}
	
	public void testGetHobbies(String user)
	{
		ArrayList<String> hb;
		hb = dbFront.dbGetHobbies(user);
		for (int i = 0; i < hb.size(); i++) {
			System.out.println(hb.get(i));
		}
	}
	
	public void testChangePassword(String newP){
		dbFront.dbChangePassword(newP);
	}
	
	public void testArchive(String friend)
	{
		/* add to archive */
		dbFront.dbAddToArchive(new ArchiveMessage(
				ld.getUsername(), 
				friend, 
				r.nextLong(), 
				"message1"));
		
		/* get messages */
		System.out.println(dbFront.dbGetFromArchive(friend).size());
	}
	
	
	public void testValidCredentials(String username, String mail) {
		/* valid credentials */
		System.out.println("emil0 with fake email?" + 
				dbFront.dbValidCredentials(
						new RegisterStep1("emil0", 
										"1223", 
										"1223", 
										"fake")));
		System.out.println("emil0's email" + 
				dbFront.dbValidCredentials(
						new RegisterStep1("something", 
										"1223", 
										"1223", 
										"heri@cti100")));
		System.out.println("new user & email" + 
				dbFront.dbValidCredentials(
						new RegisterStep1(username, 
										"1223", 
										"1223", 
										mail)));
	}

	public static void testMoveFriend()
	{
		Boolean res;
		DBFront myDbFront;
		LoginData loginData;
		
		loginData 	= new LoginData("unix140", "student");
		myDbFront 	= new DBFront();
		
		res = myDbFront.dbUserAuth(loginData);
		if (!res) {
			System.out.println(res);
		}
		
		MoveFriendRequest moveFriendRequest = new MoveFriendRequest("xkpablo", "UPB");
		
		myDbFront.dbMoveFriend(moveFriendRequest);
		System.out.println("Move Friend Test");
	}
	
	public static void restoreMoveFriend()
	{
		Boolean res;
		DBFront myDbFront;
		LoginData loginData;
		
		loginData 	= new LoginData("unix140", "student");
		myDbFront 	= new DBFront();
		
		res = myDbFront.dbUserAuth(loginData);
		if (!res) {
			System.out.println(res);
		}
		
		MoveFriendRequest moveFriendRequest = new MoveFriendRequest("xkpablo", "marmote");		

		myDbFront.dbMoveFriend(moveFriendRequest);
		System.out.println("Move Friend Restore");		
	}
	
	public void testCreateGroup(String groupName)
	{
		dbFront.dbCreateGroup(new CreateGroupRequest(groupName));
	}

	/**
	 * @param args
	 * @throws InvalidNameException 
	 */
	public static void main(String[] args) throws InvalidNameException
	{
		TestClass tc = new TestClass("homer", "save");		
		
		//tc.testRegister("homer", "simpson");
		//	tc.testAuth();
		//tc.testValidCredentials("nimeni2", "mailul2");
		tc.testAuth();
		
		tc.testValidCredentials("nimeni2", "mailul2");

		//tc.testCreateGroup("GRUPUL_MAGIC_DE+TEST");
		tc.testProfile();
		//test.dbAcceptFriend("xkpablo");
		//test.dbRemoveFriend("xkpablo", R.INIT_GROUP);
		//test.dbAddFriend("xkpablo");
		//test.dbDenyFriend("homer");
		//test.getDbConnection().close();

		//System.out.println(dbFront.dbGetUserByMail("email3"));
		//dbFront.dbChangePasswordByMail("email3", "newPassword");

		/* Paulinux was here. */
		//TestClass.testMoveFriend();
		//TestClass.restoreMoveFriend();
	}
}
