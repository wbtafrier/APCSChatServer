package com.server.user;


public class Player extends ClientUser {

	private boolean isModerator;
	
	public Player(String userName, String password) {
		super(EnumAuthorityLevel.PLAYER, userName, password);
	}
	
	protected Player(EnumAuthorityLevel level, String userName, String password) {
		super(level, userName, password);
	}
	
	public boolean isModerator() {
		return isModerator;
	}
	
	public void setModerator() {
		isModerator = true;
		setAuthority(EnumAuthorityLevel.MODERATOR);
	}
	
	public void removeModerator() {
		isModerator = false;
		setAuthority(EnumAuthorityLevel.PLAYER);
	}
	
//	public static Player initPlayer() {
		
		//Tell client to show the login screen. (Where they can say their a new user, or enter a username and password.
		//if not a new user and account is good, then let them enter.
		
		//depending on what their file says create a new version of that user.
		//if file says moderator:
		//return new Moderator()
		//ie
		
//		String name = ChatManager.prompt("What is your userName?");
		
		//for now it returns a new moderator
//		return new Moderator(name, "Spongeboob");
//	}
}
