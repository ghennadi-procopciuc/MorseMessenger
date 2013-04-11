/**
 * Paul Vlase
 * Andrei Epure
 */
package db;


import java.io.File;
import java.io.RandomAccessFile;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.ImageIcon;

import com.netdata.ArchiveMessage;
import com.netdata.ChangeProfileRequest;
import com.netdata.CreateGroupRequest;
import com.netdata.DeleteFriendRequest;
import com.netdata.DeleteGroupRequest;
import com.netdata.FriendMW;
import com.netdata.FriendSearch;
import com.netdata.LoginData;
import com.netdata.MoveFriendRequest;
import com.netdata.ShowProfileResponse;
import com.netdata.RegisterStep1;
import com.netdata.RegisterStep2;
import com.netdata.UserInfoData;
import com.netdata.WorldWindData;

public class DBFront
{
	private DBConnection dbConnection;
	private LoginData loginData;
	
	public DBFront()
	{
		dbConnection = new DBConnection();
		dbConnection.connect();
		dbConnection.setMultiplesQueries();
	}
	
	/**
	 * Check if the user exists in the database.
	 * 
	 * @param name
	 * 
	 * @return
	 * 		true	the username exists in the database
	 * 		false	the username DOES NOT EXIST in the database
	 */
	public Boolean dbUserExists(String username)
	{
		ResultSet				rs;
		Statement               st;
		String 			  		query;
		
		/* Check that username exists in USERS_TABLE. */
		query = "SELECT " + R.USERNAME + 
			    " FROM "  + R.USERS_TABLE +
			    " WHERE " + R.USERNAME + "='" + username +"'";
				
		try {
			st = dbConnection.createStatement();
			rs = st.executeQuery(query);
			
			/* Check that username exists in USERS_TABLE. */
			return rs.next();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public String dbGetUserByMail(String mail)
	{
		ResultSet				rs;
		Statement               st;
		String 			  		query, username;
		
		username = null;
		
		/* Check that username exists in USERS_TABLE. */
		query = "SELECT " + R.USERNAME + 
			    " FROM "  + R.USERS_TABLE +
			    " WHERE " + R.EMAIL + "='" + mail +"'";
				
		try {
			st = dbConnection.createStatement();
			rs = st.executeQuery(query);
			
			rs.next();
			username = rs.getString(R.USERNAME);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return username;
	}
	
	public void dbChangePasswordByMail(String mail, String newPassword)
	{
		String query;
		PreparedStatement ps;
		
		query = "UPDATE " + R.USERS_TABLE + 
				" SET " + R.PASSWORD + "=?" +  
				" WHERE " + R.EMAIL + "=?";
		
		ps = dbConnection.prepareStatement(query);
		
		try {
			ps.setString(1, newPassword);
			ps.setString(2, mail);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks if the given friendName is a friend of a current user. 
	 * @param friendName
	 * @return	true if friendName is friend
	 */
	public Boolean dbUserIsFriend(String friendName)
	{
		ResultSet				rs;
		Statement               st;
		String 			  		query;
		
		query = "SELECT " + R.FRIENDNAME + 
			    " FROM "  + R.GROUPNAME +
			    " WHERE " + R.USERNAME + "='" + loginData.getUsername() +"'";
				
		try {
			st = dbConnection.createStatement();
			rs = st.executeQuery(query);
			
			/* Check that username exists in GROUPNAME */
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * Returns true if the email address exists in the USER_TABLE. 
	 * @param mail
	 * @return
	 */
	public Boolean dbMailExists(String mail)
	{
		Statement statement;
		ResultSet rs;
		String query;
		
		/* order by groupname */
		query = "SELECT " + R.USERNAME  +
			    " FROM "  + R.USERS_TABLE +
			    " WHERE " + R.EMAIL + "='" + mail + "'";
		
		try {
			statement = dbConnection.createStatement();
			rs = statement.executeQuery(query);

			/* Check that username exists in USERS_TABLE. */
			if (! rs.next()) {
				return false;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * Checks if the given mail or username are already used.
	 * @param credentials
	 * @return		true if are valid (are not used)
	 */
	public Boolean dbValidCredentials(RegisterStep1 credentials)
	{
		ResultSet   rs;
		Statement	st;
		String		query;
		
		query = "SELECT " + R.USERNAME +
				" FROM " + R.USERS_TABLE +
				" WHERE " + R.USERNAME + "='" + credentials.getUsername() + 
					"' OR " + R.EMAIL + "='" + credentials.getMail() + "'";
		
		try {
			st = dbConnection.createStatement();
			rs = st.executeQuery(query);
			
			return !rs.next();			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}
	
	/**
	 * Retrieves the FriendMW of a friend in a list.
	 * It is used by dbGetFriends()
	 * @param friendName
	 * @return
	 */
	public FriendMW dbGetFriendMW(String friendName) 
	{
		String 		query;
		FriendMW 	friendMW = null;
		byte[]		icon;
		Statement statement;
		ResultSet rs;
		
		/*TODO check the friendName is a friend of loginData.getUsername()
		 * OR friendName == userName (we need this method to get the 
		 * logged user's FriendMW - used in Worker! in FriendProtocol-Accept*/ 
		
		query = "SELECT " + R.USERNAME + "," + R.IMAGE + "," + 
				R.NAME + "," + R.SURNAME + 
			    " FROM "  + R.USERS_TABLE +
			    " WHERE " + R.USERNAME + "='" + friendName +"'";
		
		try {
			statement = dbConnection.createStatement();
			rs = statement.executeQuery(query);
			/* Check that username exists in USERS_TABLE. */
			if (! rs.next()) {
				throw new InvalidNameException("Invalid username " + friendName + "'");
			}
			/* TODO need to extract image from database 
			 * --- rs.getString(R.IMAGE.toUpperCase()) would return the string
			 * --- rs.getObject(R.IMAGE.toUpperCase()) returns object 
			 */
			icon		= null;
			/* the server should check if user is offline */
			friendMW 	= new FriendMW(rs.getString(R.USERNAME.toUpperCase()),
										rs.getString(R.NAME.toUpperCase()) +
										rs.getString(R.SURNAME.toUpperCase()),
									   R.USER_OFFLINE, 
									   icon);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidNameException e) {
			e.printStackTrace();
		}
		return friendMW;
	}
	
	public String dbGetGroup(String user1, String user2)
	{
		Statement statement;
		ResultSet rs;
		String query, result;
		
		result = null;
		
		query = "SELECT " + R.GROUPNAME +  
			    " FROM "  + R.GROUPS_TABLE +
			    " WHERE " + R.USERNAME + "='" + user1 +"'" + " AND " +
			    R.FRIENDNAME + "='" + user2 +"'";
		
		try {
			statement = dbConnection.createStatement();
			rs = statement.executeQuery(query);
			/* Check that username exists in USERS_TABLE. */
			if (! rs.next()) {
				throw new InvalidNameException("Invalid username " + 
							loginData.getUsername() + "'");
			}
			result  = rs.getString(R.GROUPNAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Returns a map of <GroupName, <friendsInGroupArray>>.
	 * The user must be authentified (i.e. loginData is initialized)
	 * @return
	 */
	public TreeMap<String, ArrayList<FriendMW>> dbGetFriends()
	{
		ArrayList<FriendMW>	friendList;
		TreeMap<String, ArrayList<FriendMW>> groupMap;
		FriendMW	friendMW;
		Statement statement;
		ResultSet rs;
		String friendName, groupName, query;
		
		groupMap = new TreeMap<String, ArrayList<FriendMW>>();
		
		/* order by groupname */
		query = "SELECT " + R.USERNAME + "," + R.FRIENDNAME + "," + R.GROUPNAME +  
			    " FROM "  + R.GROUPS_TABLE +
			    " WHERE " + R.USERNAME + "='" + loginData.getUsername() +"'" +
			    " ORDER BY " + R.GROUPNAME;
		
		try {
			statement = dbConnection.createStatement();
			rs = statement.executeQuery(query);
			/* Check that username exists in USERS_TABLE. */
			if (! rs.next()) {
				throw new InvalidNameException("Invalid username " + 
							loginData.getUsername() + "'");
			}
			/* already at first line */
			do {
				friendName = rs.getString(R.FRIENDNAME);
				/* check if friendName is null */
				if (friendName.compareTo(R.NULL_VALUE) != 0) {
					friendMW = dbGetFriendMW(friendName);	

					groupName = rs.getString(R.GROUPNAME);
					if (groupMap.containsKey(groupName)) {
						friendList = groupMap.get(groupName);
						
						if (friendList == null) {
							friendList = new ArrayList<FriendMW>();
						}
						
						friendList.add(friendMW);
						groupMap.put(groupName, friendList);
					} else {
						friendList = new ArrayList<FriendMW>();
						friendList.add(friendMW);
						groupMap.put(groupName, friendList);
					}
				} else {
					friendMW = null;
					
					groupName = rs.getString(R.GROUPNAME);
					if (!groupMap.containsKey(groupName)) {
						groupMap.put(groupName, null);
					}
				}
			} while (rs.next());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidNameException e) {
			e.printStackTrace();
		}
		return groupMap;
	}
	
	/**
	 * Returns a list of hobbies.
	 * @param username
	 * @return
	 */
	public ArrayList<String> dbGetHobbies(String username)
	{
		Statement s;
		ResultSet rs;
		String query;
		ArrayList<String> hobbies;
	
		hobbies = new ArrayList<String>();
		
		query = "SELECT * FROM " + R.HOBBIES_TABLE + " WHERE " +
				R.USERNAME + "='" + username + "'";
		
		try{
			s = dbConnection.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				hobbies.add(rs.getString(R.HOBBY));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return hobbies;
	}
	
	/**
	 * Returns an array of usernames: the logged user friends.
	 * Uses loginData
	 * @return
	 */
	public ArrayList<String> dbGetFriendUsernames()
	{
		Map<String, ArrayList<FriendMW>> listOfFriends;
		Set<Entry<String, ArrayList<FriendMW>>> groupsSet;
		Entry<String, ArrayList<FriendMW>> groupEntry;
		Iterator<Entry<String, ArrayList<FriendMW>>> groupIt;
		ArrayList<FriendMW> friendsInGroup;
		ArrayList<String> friendUsernames;
				
		/* list of friends */
		listOfFriends = dbGetFriends();
		groupsSet = listOfFriends.entrySet();
		groupIt = groupsSet.iterator();
		friendUsernames = new ArrayList<String>();
		while (groupIt.hasNext()) {
			groupEntry = groupIt.next();
			System.out.println(groupEntry.getKey() +" "+groupEntry.getValue());
			friendsInGroup = groupEntry.getValue();
			if (friendsInGroup != null) {
				for (int i = 0; i < friendsInGroup.size(); i++) {
					friendUsernames.add(friendsInGroup.get(i).getUsername());
				}
			}
		}
		return friendUsernames;
	}

	/**
	 * Retrieves a list of userInfoData based upon on given search criteria. 
	 * @param fs
	 * @return
	 */
	public ArrayList<UserInfoData> dbGetStrangers(FriendSearch fs)
	{
		ArrayList<UserInfoData> infoArray;
		ArrayList<String> hobbies;
		UserInfoData uInfo;
		String query, outerQuery; 
		Statement statement;
		ResultSet rs;
		/* if previous query elements were added */
		Boolean setAnd, usersBit;
		
		infoArray = new ArrayList<UserInfoData>();
		
		/* we will not query the users table */
		usersBit = false;
		setAnd = false;
		
		query = "SELECT " + R.ALL + " FROM " + R.USERS_TABLE + " WHERE ";
		 
		
		System.out.println(fs.getUsername());
		System.out.println(fs.getUsername().compareTo("") != 0);
		/**
		 * Check which criteria was given by user.
		 */
		if (fs.getUsername().compareTo("") != 0)  {
			query += "" + R.USERNAME + "='" + fs.getUsername() + "'";
			setAnd = true;
			/* we will query users table */
			usersBit = true;
		}
		if (fs.getFirstName().compareTo("") != 0) {
			if (setAnd)
				query += " AND ";
			else 
				setAnd = true;
			query += "" + R.NAME + "='" + fs.getFirstName() + "'";
			/* we will query users table */
			usersBit = true;
		}
		if (fs.getLastName().compareTo("") != 0) {
			if (setAnd)
				query += " AND ";
			else 
				setAnd = true;
			query += "" + R.SURNAME + "='" + fs.getLastName() + "'";
			/* we will query users table */
			usersBit = true;
		}
		if (fs.getMail().compareTo("") != 0) {
			if (setAnd) 
				query += " AND ";
			else 
				setAnd = true;
			query += "" + R.EMAIL + "='" + fs.getMail() + "'";
			/* we will query users table */
			usersBit = true;
		}
		if (fs.getCountry().compareTo("") != 0) {
			if (setAnd) 
				query += " AND ";
			query += "" + R.LOCATION + "='" + fs.getCountry() + "'";
			/* we will query users table */
			usersBit = true;
		}
		
		System.out.println(query);
		
		if (usersBit) {
			if (fs.getHobby().compareTo("") != 0) {

				outerQuery = 
						String.format("SELECT %s FROM (%s) u, %s h "+
				" WHERE u.%s=h.%s AND h.%s='%s'",
				R.ALL, query, R.HOBBIES_TABLE, 
				R.USERNAME, R.USERNAME, R.HOBBY, fs.getHobby() );
				System.out.println("users and hobbies");
				System.out.println(outerQuery);
			} else {
				/* not searching after hobby */
				outerQuery = query;
				System.out.println("users without hobbies");
				System.out.println(outerQuery);
			}
		} else {
			if (fs.getHobby().compareTo("") != 0) {
				
				query = String.format("SELECT %s FROM %s WHERE %s='%s'",
						R.USERNAME, R.HOBBIES_TABLE, R.HOBBY, fs.getHobby());
				
				outerQuery = 
						String.format("SELECT %s FROM %s u, (%s) h " + 
				" WHERE u.%s=h.%s" , 
						R.ALL, R.USERS_TABLE, query, 
						R.USERNAME, R.USERNAME);
				System.out.println("hobbies without users");
				System.out.println(outerQuery);
			} else {
				outerQuery = "";
				System.out.println("none");
				System.out.println(outerQuery);
			}
		}
		
		try {
			statement = dbConnection.createStatement();
			rs = statement.executeQuery(outerQuery);
			while (rs.next()) {
				uInfo = new UserInfoData();
				uInfo.setUsername(rs.getString(R.USERNAME));
				uInfo.setFirstName(rs.getString(R.NAME));
				uInfo.setLastName(rs.getString(R.SURNAME));
				uInfo.setMail(rs.getString(R.EMAIL));
				uInfo.setCountry(rs.getString(R.LOCATION));
				uInfo.setBirthDate(rs.getLong(R.BIRTH_DATE));
				uInfo.setAvatar(rs.getBytes(R.IMAGE));
				hobbies = dbGetHobbies(uInfo.getUsername());
				uInfo.setHobbies(hobbies);
				infoArray.add(uInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return infoArray;
	}
	
	/**
	 * Gets the logged user information. Uses the ResultSet class.
	 * Does not use DBConnection methods. It uses loginData.
	 * 
	 * @return
	 */
	public UserInfoData dbGetUserInfo()
			throws InvalidNameException
	{
		UserInfoData result;
		String 	query;
		Statement statement;
		ResultSet rs;

		query = "SELECT " + R.ALL +
				" FROM "  + R.USERS_TABLE +
				" WHERE " + R.USERNAME + "='" + loginData.getUsername() +"'";
		
		result = new UserInfoData();
		
		try {
			statement = dbConnection.createStatement();
			rs = statement.executeQuery(query);
			/* Check that username exists in USERS_TABLE. */
			if (! rs.next()) {
				throw new InvalidNameException("Invalid username" 
							+ loginData.getUsername() + "'");
			}
			result.setUsername(rs.getString(R.USERNAME.toUpperCase()));
			result.setMail(rs.getString(R.EMAIL.toUpperCase()));
			result.setBirthDate(rs.getLong(R.BIRTH_DATE.toUpperCase()));
			result.setFirstName(rs.getString(R.NAME.toUpperCase()));
			result.setLastName(rs.getString(R.SURNAME.toUpperCase()));
			result.setCountry(rs.getString(R.LOCATION.toUpperCase()));
			result.setAvatar(rs.getBytes(R.IMAGE.toUpperCase()));
			/* adds user hobbies */
			result.setHobbies(dbGetHobbies(loginData.getUsername()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Authentificate user.
	 * @param login		username + password
	 * @return
	 */
	public Boolean dbUserAuth(LoginData login)
	{
		String 	query;
		Vector<Vector<String>>	queryResult;

		query = "SELECT " + R.USERNAME +
				" FROM "  + R.USERS_TABLE +
				" WHERE username='" + login.getUsername() +
					"' AND password='" + login.getPassword() + "'";

		queryResult = dbConnection.select(query, 1);
		
		/* cannot login */
		if (queryResult.size() == 0) {
			return false;
		}
		/* the user is authentified - we keep his data for current session */
		this.loginData = login;
		return true;
	}
	
	public void dbUserSignOut() 
	{
		this.loginData = null;
	}
	
	
	/**
	 * Registers the user data in Users and Hobbies tables.
	 * @param one 		the username + password
	 * @param two		other user data
	 */
	public void dbRegisterUser(RegisterStep1 one,
							   RegisterStep2 two) 
	{
		TreeMap<String,String> 	keyVal = new TreeMap<String,String>();
		PreparedStatement ps;
		String query;

		query = "INSERT INTO " + R.USERS_TABLE + " (" + 
				R.USERNAME + "," + R.PASSWORD + "," +
				R.EMAIL + "," + R.NAME + "," + R.SURNAME + "," +
				R.LOCATION + "," + R.BIRTH_DATE + "," +
				R.IMAGE + ") VALUES " + "(?,?,?,?,?,?,?,?)";
		
		ps = dbConnection.prepareStatement(query);
		try {
			/* security check */
			dbConnection.connection.setAutoCommit(false);
			
			ps.setString(1, one.getUsername());
			ps.setString(2, one.getPassword1());
			ps.setString(3, one.getMail());
			ps.setString(4, two.getName());
			ps.setString(5, two.getSurname());
			ps.setString(6, two.getLocation());
			ps.setLong(7, two.getBirthDate());
			ps.setBytes(8, two.getAvatar());
			ps.executeUpdate();
			
			/* add in hobbies table */
			query = "INSERT INTO " + R.HOBBIES_TABLE + " (" + 
					R.USERNAME + "," + R.HOBBY + ")" +
					" VALUES " + "(?,?)";
			ps = dbConnection.prepareStatement(query);
			for (int i = 0; i < two.getHobbies().size(); i++) {
				ps.setString(1, one.getUsername());
				ps.setString(2, two.getHobbies().get(i));
				ps.executeUpdate();
			}
			
			/* create Init */
			keyVal = new TreeMap<String,String>();
			keyVal.put(R.USERNAME,   one.getUsername());
			keyVal.put(R.GROUPNAME,  R.INIT_GROUP);
			keyVal.put(R.FRIENDNAME, R.NULL_VALUE);
			
			dbConnection.insert(R.GROUPS_TABLE, keyVal);
			
			/* commit the operations */
			dbConnection.connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Changes the password of the logged in user (i.e. loginData is initialized).
	 * @param newPassword
	 */
	public void dbChangePassword(String newPassword) 
	{
		Boolean res;
		TreeMap<String,String> keyVal = new TreeMap<String,String>();
		
		keyVal.put(R.PASSWORD, newPassword);
		res = dbConnection.update(R.USERS_TABLE,
								  loginData.getUsername(),
								  keyVal);
		
		if (!res) {
			System.err.println("Error at password change");
		}
	}
	
	/**
	 * Uses the class loginData (instantiated in dbUserAuth).
	 * @param friendName
	 * @return	true if user has friend
	 */
	public Boolean dbHasFriend(String friendName)
	{
		ResultSet  	rs;
		String 		query;
		Statement	st;
		
		query = "SELECT " + R.FRIENDNAME + 
				" FROM "  + R.GROUPS_TABLE + 
				" WHERE " + R.USERNAME + "='" + loginData.getUsername() +
					"' AND " + R.FRIENDNAME + "='" + friendName + "'"; 

		try {
			st = dbConnection.createStatement();
			rs = st.executeQuery(query);
			
			return rs.next();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	/**
	 * Adds a friend to the logged username's list, in a specified group.
	 * Uses the initialized loginData object.
	 * 
	 * @param friendName
	 * @param groupName		
	 */
	public void dbAddFriendRequest(String friendName)
			throws InvalidNameException
	{
		PreparedStatement ps;
		String query;
		ArrayList<String> friendsUsernames;
		
		/* Check that friendName exists 
		 * (if its not the null case - at dbRegisterUser) */
		if ((friendName != R.NULL_VALUE)) {
			if (!dbUserExists(friendName))
			{
				throw new InvalidNameException("Invalid friendName " +
						"'" + friendName + "'");
			}
		}
		
		friendsUsernames = dbGetFriendUsernames();
		if (friendsUsernames.contains(friendName)) {
			throw new InvalidNameException("Friend already added '" + friendName + "'");
		}
		
		query = String.format(
				"INSERT INTO %s" +
				"(%s, %s, %s) " +
				"VALUES (?,?,?)",
				R.GROUPS_TABLE,
				R.USERNAME, R.GROUPNAME, R.FRIENDNAME);
		
		ps = dbConnection.prepareStatement(query);
		System.out.println(query);
		try {
			/*TODO add friendName in R.__SENT_REQUEST al userului curent */
			ps.setString(1, loginData.getUsername());
			ps.setString(2, R.__SENT_REQUEST);
			ps.setString(3, friendName);
			ps.executeUpdate();
			/*TODO add loginData.get(username) in  R.__INCOMING_REQUEST */
			ps.setString(1, friendName);
			ps.setString(2, R.__INCOMING_REQUEST);
			ps.setString(3, loginData.getUsername());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/*TODO Accept friend*/
	public void dbAcceptFriend(String friendName)
	{
		PreparedStatement ps;
		String 			  query;

		query = "UPDATE " + R.GROUPS_TABLE + 
				" SET "   + R.GROUPNAME    + "='"   + R.INIT_GROUP + "'" +
				" WHERE " + R.USERNAME     + "=?" +
				" AND "   + R.GROUPNAME    + "=?" +
				" AND "   + R.FRIENDNAME   + "=?";
		ps = dbConnection.prepareStatement(query);
		
		try {
			ps.setString(1, loginData.getUsername());
			ps.setString(2, R.__INCOMING_REQUEST);
			ps.setString(3, friendName);
			ps.executeUpdate();
			
			ps.setString(1, friendName);
			ps.setString(2, R.__SENT_REQUEST);
			ps.setString(3, loginData.getUsername());
			ps.executeUpdate();
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	
	/*TODO Deny friend*/
	public void dbDenyFriend(String friendName)
	{
		PreparedStatement 	ps;
		String				query;
	
		query = "DELETE"  +
				" FROM "  + R.GROUPS_TABLE + 
				" WHERE " + R.USERNAME     + "=?" +
				" AND "   + R.GROUPNAME    + "=?" +
				" AND "	  + R.FRIENDNAME   + "=?";
		ps = dbConnection.prepareStatement(query);
		
		try {
			ps.setString(1, loginData.getUsername());
			ps.setString(2, R.__INCOMING_REQUEST);
			ps.setString(3, friendName);
			ps.executeUpdate();
			
			ps.setString(1, friendName);
			ps.setString(2, R.__SENT_REQUEST);
			ps.setString(3, loginData.getUsername());
			ps.executeUpdate();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}


	public void dbCreateGroup(CreateGroupRequest createGroupRequest)
	{
		String query = String.format(
				"INSERT INTO %s" +
				"(%s, %s, %s) " +
				"VALUES (?,?,?)",
				R.GROUPS_TABLE,
				R.USERNAME, R.GROUPNAME, R.FRIENDNAME);
		
		PreparedStatement ps = dbConnection.prepareStatement(query);
		System.out.println(query);
		try {
			ps.setString(1, loginData.getUsername());
			ps.setString(2, createGroupRequest.getGroupName());
			ps.setString(3, R.NULL_VALUE);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void dbDeleteGroup(DeleteGroupRequest deleteGroupRequest)
	{
		Statement 	st;
		String	  	updateQuery, deleteQuery;		
		
		updateQuery = String.format(
				"UPDATE %s " +
				"SET %s='%s' " +
				"WHERE %s='%s' AND %s='%s'",
				R.GROUPS_TABLE,
				R.GROUPNAME, R.INIT_GROUP,
				R.USERNAME, loginData.getUsername(),
				R.GROUPNAME, deleteGroupRequest.getGroupName());

		deleteQuery = String.format(
				"DELETE FROM %s " +
				"WHERE %s='%s' " +
				"AND %s='_null_'" +
				"AND %s='%s'",
				R.GROUPS_TABLE, 
				R.GROUPNAME, deleteGroupRequest.getGroupName(),
				R.FRIENDNAME,
				R.USERNAME, loginData.getUsername());
		
		
		try {
			dbConnection.connection.setAutoCommit(false);
			st = dbConnection.createStatement();
			st.executeUpdate(deleteQuery);
			st.executeUpdate(updateQuery);
			dbConnection.connection.commit();
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	/**
	 * Moves friend from its group to a new group.
	 * @param friendName	
	 * @param groupName		the new group
	 */
	public void dbMoveFriend(MoveFriendRequest moveFriendRequest)
	{
		String query;
		PreparedStatement ps;
		
		query = "UPDATE " + R.GROUPS_TABLE + 
				" SET " + R.GROUPNAME + "=?" +  
				" WHERE " + R.USERNAME + "=?" + " AND " +
						R.FRIENDNAME + "=?";
		
		ps = dbConnection.prepareStatement(query);
		
		try {
			ps.setString(1, moveFriendRequest.getGroupName());
			ps.setString(2, loginData.getUsername());
			ps.setString(3, moveFriendRequest.getFriendName());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();			
		}
	}
	
	
	/**
	 * Removes the friendName from the list of username (by removing him from
	 * the group). Uses the data from loginData.
	 * 
	 * @param friendName
	 * @param groupName
	 */
	public void dbRemoveFriend(DeleteFriendRequest dfr)
	{
		String update;

		dbConnection.connect();
		
		update = "DELETE FROM " +
				 R.GROUPS_TABLE +
				 " WHERE (" +
				 R.USERNAME + ", " + 
				 R.FRIENDNAME + ")=('" + 
				 loginData.getUsername() + "', '" + 
				 dfr.getFriendName() + "')";

		System.out.println(update);
		
		try {
			dbConnection.st.executeUpdate(update);
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	/**
	 * Adds a simple message from in the archive.
	 * 
	 * @param message		an object containing source, destination
	 * 						date and content
	 */
	public void dbAddToArchive(ArchiveMessage message)
	{
		TreeMap<String,String> keyVal = new TreeMap<String,String>();
		
		keyVal.put(R.SENDER,    message.getSender());
		keyVal.put(R.RECEIVER,  message.getReceiver());
		keyVal.put(R.DATE,    message.getDate().toString());
		keyVal.put(R.MESSAGE, message.getMessage());
		
		/* TODO check if the message is already there */
		
		dbConnection.insert(R.ARCHIVE_TABLE, keyVal);
	}
	
	/**
	 * Gets the archive messages between the logged user and a friend.
	 * Users loginData object.
	 * @param friend
	 * @return				a list of archive messages
	 */
	public ArrayList<ArchiveMessage> dbGetFromArchive(String friend)
	{
		ArrayList<ArchiveMessage> messages = new ArrayList<ArchiveMessage>();
		ArchiveMessage am;
		String query;
		ResultSet rs;
		
		/*SELECT * FROM archive WHERE (sender='emil6' AND receiver='jimy0')
		 *  OR (sender='jimy0' AND receiver='emil6') ORDER BY date 
		 */
		
		query = "SELECT " + R.ALL +
				" FROM "  + R.ARCHIVE_TABLE +
				" WHERE (" + R.SENDER + "='" + loginData.getUsername() + 
				"' AND " + R.RECEIVER + "='" + friend + "')" +
				" OR (" + R.SENDER + "='" + friend + 
				"' AND " + R.RECEIVER + "='" + loginData.getUsername() + "')";
		
		System.out.println(query);
		try {
			rs = dbConnection.st.executeQuery(query);
			/* Check that username exists in USERS_TABLE. */
			if (! rs.next()) {
				throw new InvalidNameException("Invalid username" + loginData.getUsername() + "'");
			}
			/* already at first line */
			do {
				am = new ArchiveMessage(rs.getString(R.SENDER), 
						rs.getString(R.RECEIVER), 
						rs.getLong(R.DATE),
						rs.getString(R.MESSAGE));
				messages.add(am);
			} while (rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidNameException e) {
			e.printStackTrace();
		}
		
		return messages;
	}
	
	/**
	 * Adds the data of the pin of the logged user.
	 * @param wwData		Contains username, longitude, latitude, color.
	 * @throws InvalidNameException 
	 */
	public void dbAddPin(WorldWindData wwData) 
			throws InvalidNameException
	{
		PreparedStatement ps;
		String query;
		
		if (!loginData.getUsername().equals(wwData.getUsername()))
		{
			/* user should be the logged in user */
			throw new InvalidNameException("Invalid add - not the logged in user '"
						+ wwData.getUsername() + "'");
		}
		
		/* add in user table */
		query = "INSERT INTO " + 
				R.WWPINS_TABLE +
				"( " + R.USERNAME + "," + R.LAT + "," + R.LONG + "," + R.COLOR +
				") " + "VALUES" + "(?,?,?,?)";
		ps = dbConnection.prepareStatement(query);
		
		try {
			ps.setString(1, wwData.getUsername());
			/* R.LAT */
			ps.setDouble(2, wwData.getLatitude());
			/* R.LONG */
			ps.setDouble(3, wwData.getLongitude());
			ps.setInt(4, wwData.getColor());
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves the existing pins of the logged user.
	 * @return 			WorldWindData.PIN array
	 * @throws InvalidNameException 
	 */
	
	public ArrayList<WorldWindData> dbGetPins() 
	{
		ArrayList<WorldWindData> pins;
		ArrayList<String> usersWithPins;
		PreparedStatement ps;
		ResultSet rs;
		String query, user;
		float latid, longit;
		Integer color;
		
		pins = new ArrayList<WorldWindData>();
		/* add the user friends */
		usersWithPins = dbGetFriendUsernames();
		/* add the logged user */
		usersWithPins.add(loginData.getUsername());
		
		/* order by groupname */
		query = "SELECT " + R.USERNAME + "," + R.LONG + "," + R.LAT + "," + R.COLOR +
			    " FROM "  + R.WWPINS_TABLE +
			    " WHERE " + R.USERNAME + "= ?";
		ps = dbConnection.prepareStatement(query);
		
		try {
			
			/* for the user and each of his friends */
			for (int i = 0; i < usersWithPins.size(); i++) {
				ps.setString(1, usersWithPins.get(i));
				/* retrieve the Pins */
				rs = ps.executeQuery();
				while(rs.next()) {
					user = rs.getString(R.USERNAME);
					latid = rs.getFloat(R.LAT);
					longit = rs.getFloat(R.LONG);
					color = rs.getInt(R.COLOR);
					
					pins.add(new WorldWindData(user,
											   latid,
											   longit,
											   color.toString(),
											   WorldWindData.PIN));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pins;
	}
	
	/**
	 * Adds a label to the logged user wwGlobe.
	 * @param wwData
	 * @throws InvalidNameException
	 */
	public void dbAddLabel(WorldWindData wwData) 
			throws InvalidNameException
	{
		PreparedStatement ps;
		String query;
		
		if (!loginData.getUsername().equals(wwData.getUsername()))
		{
			throw new InvalidNameException("This is not the logged user '" + wwData.getUsername() + "'");
		}
		
		/* add in user table */
		query = "INSERT INTO " + 
				R.WWLABELS_TABLE +
				"( " + R.USERNAME + "," + R.LAT + "," + R.LONG + "," + R.TEXT +
				") " + "VALUES" + "(?,?,?,?)";
		ps = dbConnection.prepareStatement(query);
		
		try {
			ps.setString(1, wwData.getUsername());
			/* R.LAT */
			ps.setDouble(2, wwData.getLatitude());
			/* R.LONG */
			ps.setDouble(3, wwData.getLongitude());
			ps.setString(4, wwData.getText());
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves the labels of the current user and his friends.
	 * @return
	 */
	public ArrayList<WorldWindData> dbGetLabels() 
			throws InvalidNameException
	{
		ArrayList<WorldWindData> labels;
		ArrayList<String> usersWithLabels;
		PreparedStatement ps;
		ResultSet rs;
		String query, user, text;
		float latid, longit;
				
		labels = new ArrayList<WorldWindData>();
		usersWithLabels = dbGetFriendUsernames();
		usersWithLabels.add(loginData.getUsername());
		
		/* order by groupname */
		query = "SELECT " + R.USERNAME + "," + R.LONG + "," + R.LAT + "," + R.TEXT +
			    " FROM "  + R.WWLABELS_TABLE +
			    " WHERE " + R.USERNAME + "= ?";
		/* user PreparedStatement for efficiency */
		ps = dbConnection.prepareStatement(query);
		
		try {
			/* we are using a separate statement to be able to use dbFunction calls
			 * one in another (so that the statement isn't destroyed)
			 */
			for (int i = 0; i < usersWithLabels.size(); i++) {
				ps.setString(1, usersWithLabels.get(i));
				rs = ps.executeQuery();	
				/* already at first line */
				while(rs.next()) {
					user = rs.getString(R.USERNAME);
					latid = rs.getFloat(R.LAT);
					longit = rs.getFloat(R.LONG);
					text = rs.getString(R.TEXT);
					
					labels.add(new WorldWindData(user,
											     latid,
											     longit,
											     text,
											     WorldWindData.LABEL));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return labels;
	}
	
	public void dbSetProfile(ChangeProfileRequest req)
	{
		/*
		 * 	query = "UPDATE " + R.USERS_TABLE + 
				" SET " + R.PASSWORD + "=?" +  
				" WHERE " + R.EMAIL + "=?";
		 */
		String query;
		Statement s;
		ResultSet rs;
		boolean setComma, usersBit;
		
		query = "UPDATE " + R.USERS_TABLE + " SET ";
		setComma = false;
		usersBit = false;

		if (req.getSurname().compareTo("") != 0) {
			query += R.SURNAME + "='" + req.getSurname()+"'";
			setComma = true;
			usersBit = true;
		}
		
		if (req.getName().compareTo("") != 0) {
			if (setComma) {
				query +=  ",";
			} else {
				setComma = true;
			}
			usersBit = true;
			query += R.NAME + "='" + req.getName()+"'";
		}
		if (req.getLocation().compareTo("") != 0) {
			if (setComma) {
				query +=  ",";
			} else {
				setComma = true;
			}
			usersBit = true;
			query += R.LOCATION + "='" + req.getLocation()+"'";
		}
		if (req.getBirthDate() != 0) {
			if (setComma) {
				query +=  ",";
			} else {
				setComma = true;
			}
			usersBit = true;
			query += R.BIRTH_DATE + "=" + req.getBirthDate();		
		}
		
		if (req.getAvatar() != null) {
			if (setComma) {
				query +=  ",";
			} else {
				setComma = true;
			}
			usersBit = true;
			query += R.IMAGE + "='" + req.getAvatar()+"'";		
		}

		System.out.println( "password = " + req.getPassword() + "; newpassword = " + req.getNewPassword());
		if (req.getNewPassword() != null) {
			if (setComma) {
				query +=  ",";
			} else {
				setComma = true;
			}
			usersBit = true;
			query += R.IMAGE + "='" + req.getNewPassword()+"'";		
		}		
		
		if (usersBit) {
			query += " WHERE " + R.USERNAME + "='" + req.getUsername()+"'";
		}
		
		try {
			System.out.println(query);
			s = dbConnection.createStatement();
			s.executeUpdate(query); 
		
			/* add in hobbies table */
			query = "INSERT INTO " + R.HOBBIES_TABLE + " (" + 
					R.USERNAME + "," + R.HOBBY + ")" +
					" VALUES " + "(?,?)";
			PreparedStatement ps = dbConnection.prepareStatement(query);
			for (int i = 0; i < req.getHobbies().size(); i++) {
				ps.setString(1, req.getUsername());
				ps.setString(2, req.getHobbies().get(i));
				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public DBConnection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public LoginData getLoginData() {
		return loginData;
	}

	public void setLoginData(LoginData loginData) {
		this.loginData = loginData;
	}
}
