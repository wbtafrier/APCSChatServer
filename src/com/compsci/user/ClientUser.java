package com.compsci.user;

public abstract class ClientUser extends User {
	
	private static final long serialVersionUID = -2906357802993633627L;
	
	private boolean isMuted;
	private boolean isBanned;
	
	public ClientUser(EnumAuthorityLevel level, String userName) {
		super(level, userName);
	}
	
	public boolean isMuted() {
		return isMuted;
	}
	
	public boolean isBanned() {
		return isBanned;
	}
}
