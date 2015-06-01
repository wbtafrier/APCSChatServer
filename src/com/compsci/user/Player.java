package com.compsci.user;


public class Player extends ClientUser {

	private static final long serialVersionUID = -5453952520070522951L;
	
	private boolean isModerator;
	private boolean isAdministrator;
	
	public Player(String userName) {
		super(EnumAuthorityLevel.PLAYER, userName);
	}
	
	protected Player(EnumAuthorityLevel level, String userName) {
		super(level, userName);
	}
	
	public boolean isModerator() {
		return isModerator;
	}
	
	public void setModerator() {
		
		if (isAdministrator)  isAdministrator = false;
		isModerator = true;
		setAuthority(EnumAuthorityLevel.MODERATOR);
	}
	
	public void removeModerator() {
		isModerator = false;
		setAuthority(EnumAuthorityLevel.PLAYER);
	}
	
	public void setAdministator() {
		
		if (isModerator) isModerator = false;
		isAdministrator = true;
		setAuthority(EnumAuthorityLevel.ADMINISTRATOR);
	}
	
	public void removeAdministrator() {
		
		isAdministrator = false;
		setAuthority(EnumAuthorityLevel.PLAYER);
	}
}
