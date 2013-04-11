package backend;

import java.io.EOFException;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.netdata.ArchiveMessage;
import com.netdata.CreateGroupRequest;
//import com.netdata.DeleteFriendRequest;
import com.netdata.DeleteGroupRequest;
import com.netdata.ForgotPassword;
import com.netdata.FriendMW;
import com.netdata.FriendProtocol;
import com.netdata.FriendSearch;
import com.netdata.InitiateChat;
import com.netdata.LoginData;
import com.netdata.LoginDataResponse;
//import com.netdata.MorseInvitation;
import com.netdata.ChangeProfileRequest;
import com.netdata.ChangeProfileResponse;
import com.netdata.DeleteFriendRequest;
import com.netdata.MorseInvitation;
import com.netdata.MoveFriendRequest;
import com.netdata.NewFriend;
import com.netdata.NewFriendList;
import com.netdata.ShowProfileRequest;
import com.netdata.ShowProfileResponse;
import com.netdata.RegisterStep1;
import com.netdata.RegisterStep1Response;
import com.netdata.RegisterStep2;
import com.netdata.SignInNotification;
import com.netdata.SignOutNotification;
import com.netdata.TransferProtocol;
import com.netdata.UserInfoData;
import com.netdata.FriendSearchResponse;
import com.netdata.ViewArchive;
import com.netdata.ViewArchiveResponse;
import com.netdata.WorldWindData;
import com.netdata.WorldWindRequest;
import com.netdata.WorldWindResponse;

import db.DBFront;
import db.InvalidNameException;
import db.R;


public class Worker extends Thread{
	private DBFront dbFront;
	private Socket	connection;
	private ObjectOutputStream outStream;
	private ObjectInputStream	inStream;
	private RandomString generator;
	private final int PASSWD_LENGTH = 10;
	
	/*
	 * This thread is responsible for a specific user.
	 * username - set when Login is confirmed
	 */
	private String username;
	
	public Worker(Socket connection)
	{
		this.connection = connection;
		this.dbFront	= new DBFront();
		generator = new RandomString(PASSWD_LENGTH);
		
		try {
			outStream = new ObjectOutputStream(this.connection.getOutputStream());
			outStream.flush();
		} catch (IOException e) {
			new Error(e.getMessage());
		}
		
		try {
			inStream = new ObjectInputStream(this.connection.getInputStream());
			System.out.println(inStream);
		} catch (IOException e) {
			new Error(e.getMessage());
		}
	}
/*	
	public void signOut()
	{
		SignOutNotification signOutNotification;
		TreeMap<String, ArrayList<FriendMW>> groupsMap;
	
		System.out.println("[WORKER] Client Closed (IGNORE StackTrace)");
	
		signOutNotification  = new SignOutNotification(this.username);
		 
		groupsMap = dbFront.dbGetFriends();
		//MorseServer.onlineUsers.get(this.username);
		
		Iterator<Map.Entry<String, ArrayList<FriendMW>>> 
			groupEntries = groupsMap.entrySet().iterator();
		while (groupEntries.hasNext()) {
		  Map.Entry<String, ArrayList<FriendMW>> groupEntry = groupEntries.next();
		  String groupName = groupEntry.getKey();
		  ArrayList<FriendMW> friendList = groupEntry.getValue();
		  
		  System.out.print(groupName + " : ");
		  
		  Iterator<FriendMW> friendEntries = friendList.iterator();
		  while (friendEntries.hasNext()) {
			  FriendMW friendEntry = friendEntries.next();
			  System.out.println(friendEntry.getUsername() + ", ");
			  
			  try {
				  MorseServer.onlineUsers.get(friendEntry.getUsername()).writeObject(signOutNotification);
			  }
			  catch (IOException ioe) {
				  ioe.printStackTrace();
			  }
		  }
		}
	}
	*/
	
	/**
	 * A user tries to log in.
	 * I have to send him back the Main Window data.
	 * TreeMap<groupname, users>
	 * If the LoginData is invalid, send NULL.
	 * this.username is set 
	 */
	public void userLogin(LoginData loginData)
	{
		this.username = loginData.getUsername();
		
		System.out.println("[SERV-LOG] LoginData: " + loginData.getUsername());
		System.out.println("[SERV-LOG] VALID? "+dbFront.dbUserAuth(loginData));
		TreeMap<String, ArrayList<FriendMW>> groupsMap, newGroupsMap;
		ArrayList<FriendMW> usersInGroup;
		UserInfoData uid;
		String completeName;
		FriendMW fmw;
//
		debugOnlineUsers();
//
		newGroupsMap = null;
		groupsMap = null;
		completeName = null;
		fmw = null;
		
		if (dbFront.dbUserAuth(loginData)) {
			try {
				newGroupsMap = new TreeMap<String, ArrayList<FriendMW>>();
				uid = dbFront.dbGetUserInfo();
				groupsMap = dbFront.dbGetFriends();
				completeName = uid.getFirstName() + " " + uid.getLastName();
				MorseServer.onlineUsers.put(this.username, outStream);
				fmw = dbFront.dbGetFriendMW(loginData.getUsername());
				/* key set - the group names */
				Set<String> groups = groupsMap.keySet();
				Set<Entry<String,ArrayList<FriendMW>>> e = groupsMap.entrySet();
				ArrayList<FriendMW> newGroup; 
				Iterator<String> it = groups.iterator();
				
				String groupName;
				FriendMW temp;
				SignInNotification note;
				
				while (it.hasNext()) {
					
					newGroup = new ArrayList<FriendMW>();
					groupName = it.next();
					usersInGroup = groupsMap.get(groupName);
					System.out.println("[SERV-LOG--]: Group " + groupName);
					
					if (usersInGroup != null) {
						
						System.out.println("[SERV-LOG--]: Group size " + usersInGroup.size());
						
						for (int i = 0; i < usersInGroup.size(); i++) {
							
							System.out.println("[SERV-LOG--]: User " + usersInGroup.get(i).getUsername());
							
							if (MorseServer.onlineUsers.containsKey(usersInGroup.get(i).getUsername())) {
								System.out.println("----------- is online");
					
								newGroup.add(usersInGroup.get(i));
								/**
								 * should send to friend notification - I am online now
								 */
								String onlineFriendname = usersInGroup.get(i).getUsername();
								note = new SignInNotification(dbFront.dbGetFriendMW(username), 
										dbFront.dbGetGroup(onlineFriendname, username));
								
								System.out.println("[SERV-LOG- sent notification to " + onlineFriendname +"  with group " + note.getGroupname() + " " + note.getUserMW().getUsername());
								
								MorseServer.onlineUsers.get(onlineFriendname).writeObject(note);
							} else {
								if (groupName.compareTo(R.__INCOMING_REQUEST) == 0) { 
									System.out.println("----------- is incoming");
									newGroup.add(usersInGroup.get(i));
								}
							}
						}
					}
					
					if (newGroup != null) {
						System.out.println("Groupname= " + groupName + " size = " + newGroup.size());
						for (int i = 0 ;i < newGroup.size(); i++)
							System.out.println(newGroup.get(i).getUsername());
					}
					
					newGroupsMap.put(groupName, newGroup);
					System.out.println(groupsMap);
					
				}
			} catch (InvalidNameException | IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		
		LoginDataResponse ldr = new LoginDataResponse(
				newGroupsMap,
				completeName,
				loginData.getUsername(),
				fmw);
		
		System.out.println("[SERVER]");
		System.out.println("WILL SEND " + ldr.getResponse());
		System.out.println("Complete: " + ldr.getCompleteName());
		System.out.println("Username: " + ldr.getUsername());
		try {
			System.out.println("[SERVER]: " + ldr.getClass() + " " + ldr);
			outStream.writeObject(ldr);
		} catch (IOException e1) {
			System.out.println("EROARE");
			e1.printStackTrace();
		} 
		
		///System.out.println("AAAA");	
	}
	
	public void forgotPassword(Object receivedData)
	{
		/**
		 * Gonna send mail: user + newpass
		 * newpass is random
		 */
		
		System.out.println("[SERVER] Received ForgotPassword");
		String mail = ((ForgotPassword)receivedData).getMail();
		if (dbFront.dbMailExists(mail)) {
			String username = dbFront.dbGetUserByMail(mail);
			String password = generator.getRandomString();
			GMailTool mailTool = new GMailTool(mail, username, password);
			mailTool.sendMail();
			/* modify the database */
			dbFront.dbChangePasswordByMail(mail, password);
		}
	}
	
	public void registerUser(Object receivedData)
	{
		/*
		 * If user and password are valid, send boolean true
		 * and wait for a RegisterStep2 content.
		 */
		RegisterStep1Response resp = new RegisterStep1Response(false);
		System.out.println("[SERVER] RegisterStep1: " + ((RegisterStep1)receivedData).getUsername());
		
		/**
		 * TODO: check username & send boolean accordingly
		 */
		
		resp.setValid(dbFront.dbValidCredentials((RegisterStep1)receivedData));
		System.out.println("IS VALID? "+dbFront.dbValidCredentials((RegisterStep1)receivedData));
		try {
			outStream.writeObject(resp);
		} catch (IOException e) {
			new Error(e.getMessage());
		}
	
		if(resp.getValid()) {	
			/*
			 * 	valid = true, so wait for RegisterStep2 content
			 */
			RegisterStep2 step2 = null;
			try {
				step2 = (RegisterStep2) inStream.readObject();
			} catch (ClassNotFoundException e) {
				new Error(e.getMessage());
			} catch (IOException e) {
				new Error(e.getMessage());
			}
			System.out.println("[SERVER] RegisterStep2: " + step2.getName() + " " + step2.getSurname());
			dbFront.dbRegisterUser((RegisterStep1)receivedData, step2);
		}
	}
	
	public void friendSearch(Object receivedData)
	{
		debugOnlineUsers();
		
		System.out.println("[SERVER] Received FriendSearch");
		
		ArrayList<UserInfoData> list = 
				dbFront.dbGetStrangers((FriendSearch)receivedData);
		
		FriendSearchResponse uidac = new FriendSearchResponse(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getUsername());
		}
		try {
			outStream.writeObject(uidac);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void friendProtocol(Object receivedData)
	{
		FriendProtocol receivedDataFP = (FriendProtocol) receivedData;
		String dest = receivedDataFP.getDestination().getUsername();
		
		switch(receivedDataFP.getType()){
		case FriendProtocol.REQUEST:
			System.out.println("[SERV-REQ]:Received request");
			/* the source is the initiator */
			
			debugOnlineUsers();
			System.out.println("[SERV-REQ] - dest is " + dest);
			
			if(MorseServer.onlineUsers.containsKey(dest)) {
				/* User is online */
				try {
					/* Forwarding request to dest */
					MorseServer.onlineUsers.get(dest).writeObject(receivedData);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				/* log request */
				dbFront.dbAddFriendRequest(dest);
			} catch (InvalidNameException e) {
				e.printStackTrace();
			}
			break;
			
		case FriendProtocol.ACCEPT:
			System.out.println("[SERV-ACC]:Received accept");
				
			dbFront.dbAcceptFriend(dest);
			
			//NewFriendList listOfFriends = new NewFriendList(dbFront.dbGetFriends());
			NewFriend nf = new NewFriend(dbFront.dbGetFriendMW(username));
			
			if(MorseServer.onlineUsers.containsKey(dest)) {
				/* User is online */
				try {
					MorseServer.onlineUsers.get(dest).writeObject(nf);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			break;
			
		case FriendProtocol.DENY:
			System.out.println("[SERV-DEN]:Received deny");
			/* the user that received a request denies the request and modifies
			 * both its and the requester's database by denying */
			dbFront.dbDenyFriend(dest);
			break;
		}
	}
	
	public void deleteFriend(Object receivedData)
	{
		DeleteFriendRequest dfr = (DeleteFriendRequest) receivedData;
		
		dbFront.dbRemoveFriend(dfr);
	}
	
	public void initiateChat(Object receivedData)
	{
		debugOnlineUsers();
		
		InitiateChat ic = (InitiateChat)receivedData;
		String dest = ic.getTo();
		System.out.println("[SERVER]:Initiate chat");
		System.out.println("FROM: " + ic.getFrom() );
		System.out.println("TO: " + ic.getTo() );
		System.out.println("DATA: " + ic.getData());
		ArchiveMessage am = new ArchiveMessage(
									ic.getFrom(), 
									ic.getTo(), 
									System.currentTimeMillis(), 
									ic.getData());
		dbFront.dbAddToArchive(am);
		try {
			MorseServer.onlineUsers.get(dest).writeObject(receivedData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void morseInvitation(Object receivedData)
	{
		MorseInvitation mi = (MorseInvitation) receivedData;
		
		try {
			MorseServer.onlineUsers.get(mi.getTo()).writeObject(mi);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void createGroup(Object receivedData)
	{
		CreateGroupRequest createGroupRequest = (CreateGroupRequest) receivedData;
		dbFront.dbCreateGroup(createGroupRequest);
	}
	
	public void deleteGroup(Object receivedData)
	{
		DeleteGroupRequest deleteGroupRequest = (DeleteGroupRequest) receivedData;
		System.out.println("[WORKER " + this.username + "]" + "Delete group : " + deleteGroupRequest.getGroupName());
		dbFront.dbDeleteGroup(deleteGroupRequest);
	}
	
	public void moveFriend(Object receivedData)
	{
		MoveFriendRequest moveFriendRequest = (MoveFriendRequest) receivedData;
		/* TODO verify group - is it necessary? */
		if (dbFront.dbHasFriend(moveFriendRequest.getFriendName()))
				dbFront.dbMoveFriend(moveFriendRequest);
	}
	
	public void viewArchive(Object receivedData)
	{
		ViewArchive va = (ViewArchive)receivedData;
		ViewArchiveResponse resp;
		System.out.println("[SERVER] Received viewArchive");
		
		resp = new ViewArchiveResponse(
				va.getUsername(),
				dbFront.dbGetFromArchive(va.getUsername())
				);
		
		try {
			outStream.writeObject(resp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showProfileRequest(Object receivedData)
	{
		ShowProfileResponse resp;	
		
		try {
			resp = new ShowProfileResponse(dbFront.dbGetUserInfo());
			
			System.out.println("[WORKER " + resp.getUsername() + "] " + resp.getName() + ", " + resp.getSurname() );
			System.out.println("[WORKER " + this.username + "] " + resp.getUsername() + ", " + resp.getName() + ", " + resp.getSurname() );
			
			outStream.writeObject(resp);
		} catch (InvalidNameException ine) {
			ine.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void changeProfileRequest(Object receivedData)
	{
		ChangeProfileRequest req;
		ChangeProfileResponse resp;
		
		System.out.println("[WORKER " + this.username + "] changeProfileRequest");
		
		req		= (ChangeProfileRequest) receivedData;
		resp 	= new ChangeProfileResponse(false);
		
		if (dbFront.dbUserAuth(new LoginData(this.username, req.getPassword()))) {
			dbFront.dbSetProfile(req);
			
			resp.setResult(true);
		}
		
		System.out.println("Here");
		
		
		try {
			outStream.writeObject(resp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void worldWindRequest(Object receivedData) {
		WorldWindRequest req = (WorldWindRequest)receivedData;
		WorldWindResponse resp;
		switch(req.getType()) {
		case WorldWindData.LABEL:
			try {
				resp = new WorldWindResponse(username,
						WorldWindData.LABEL,
						dbFront.dbGetLabels());
				outStream.writeObject(resp);
			} catch (InvalidNameException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case WorldWindData.PIN:
			try {
				resp = new WorldWindResponse(username,
						WorldWindData.PIN,
						dbFront.dbGetPins());
				outStream.writeObject(resp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void signOut() {
		
		debugOnlineUsers();
		
		SignOutNotification son = new SignOutNotification(username);
		/* not online anymore */
		MorseServer.onlineUsers.remove(son.getFriendName());
		/* for friends */
		TreeMap<String, ArrayList<FriendMW>> friendMap = dbFront.dbGetFriends();
		Collection<ArrayList<FriendMW>> friendCol = friendMap.values();
		Iterator<ArrayList<FriendMW>> it = friendCol.iterator();
		ArrayList<FriendMW> friendGroup;
		String friendUsername;
		
		while(it.hasNext()) {
			friendGroup = it.next();
			if (friendGroup == null)
				continue;
			for (int i = 0; i < friendGroup.size(); i++) {
				friendUsername = friendGroup.get(i).getUsername();
				if (MorseServer.onlineUsers.containsKey(friendUsername)) {
					/* write the SignOutNotification to the online friend */
					System.out.print("[SERVER-SIGN-OUT]: " + friendUsername);
					System.out.println(" IS ON -> will send signOutNot");
					try {
						MorseServer.onlineUsers.get(friendUsername).writeObject(son);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		/* set dbFront.loginData to null */
		dbFront.dbUserSignOut();
	}
	
	private void transferFile(Object receivedData) {
		debugOnlineUsers();
		
		TransferProtocol tp = (TransferProtocol) receivedData;
		System.out.println("[SERVER] " + tp.getFilename());
		System.out.println("FROM " + tp.getFrom() );
		System.out.println("TO " + tp.getTo());
		System.out.println("TYPE " + tp.getType());
		try {
			//outStream.writeObject(receivedData);
			MorseServer.onlineUsers.get(tp.getTo()).writeObject(receivedData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		Object receivedData = null;
		
		while(true) {
			
			try {
				synchronized(inStream){
					receivedData = inStream.readObject();	
				}
			}
			catch (EOFException eofe) {
				System.out.println("A IESIT FORTAT");
				eofe.printStackTrace();
				signOut( );
				return;
			} 
			catch (ClassNotFoundException e) {
				System.out.println("A IESIT FORTAT");
				e.printStackTrace();
				signOut( );
				return;
			}
			catch (IOException e) {
				System.out.println("A IESIT FORTAT");
				e.printStackTrace();
				signOut( );
				return;
			} 
		
			System.out.println("[SERVER] Received "+receivedData.getClass());
			
			/**
			 * Now, I have to know the type of the received data ...
			 */
			if(receivedData instanceof RegisterStep1) {
				registerUser(receivedData);
			}
			
			if(receivedData instanceof LoginData) {
				userLogin((LoginData) receivedData);
				//LoginData loginData = (LoginData) receivedData;	
			}
			
			if(receivedData instanceof ForgotPassword) {
				forgotPassword(receivedData);
			}
			
			if(receivedData instanceof FriendSearch) {
				friendSearch(receivedData);
				
			}
			
			if	(receivedData instanceof FriendProtocol) {
				System.out.println("[SERVER]: FRIEND PROTOCOL");
				friendProtocol(receivedData);
			}
			
			if (receivedData instanceof DeleteFriendRequest) {
				deleteFriend(receivedData);
			}
			
			if (receivedData instanceof MorseInvitation) {
				morseInvitation(receivedData);
			}
			
			if (receivedData instanceof CreateGroupRequest) {
				createGroup(receivedData);
			}
			
			if (receivedData instanceof DeleteGroupRequest) {
				deleteGroup(receivedData);
			}
			
			if (receivedData instanceof MoveFriendRequest) {
				moveFriend(receivedData);
			}
			
			if (receivedData instanceof InitiateChat) {
				System.out.println("[SERVER]:will call initiateChat()");
				initiateChat(receivedData);
			}
			if (receivedData instanceof ViewArchive) {
				viewArchive(receivedData);
			}
			if (receivedData instanceof WorldWindRequest) {
				worldWindRequest(receivedData);
			}
			if (receivedData instanceof SignOutNotification) {
				signOut();
			}
			if (receivedData instanceof TransferProtocol) {
				transferFile(receivedData);
			}
			if (receivedData instanceof ShowProfileRequest) {
				showProfileRequest(receivedData);
			}
			if (receivedData instanceof ChangeProfileRequest) {
				changeProfileRequest(receivedData);
			}
		}
	}
	
	public void debugOnlineUsers()
	{
		System.out.print("[SERVER-ONLINE-USERS]: ");
		Set<String> usersSet = MorseServer.onlineUsers.keySet();
		Iterator<String> it = usersSet.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + ",");
		}
		System.out.println("");
	}

}
