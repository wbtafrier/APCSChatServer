package com.compsci.user;

public abstract class ClientUser extends User {
	
	private static final long serialVersionUID = -2906357802993633627L;
	
	private boolean isMuted;
	private boolean isBanned;
	
	public ClientUser(EnumAuthorityLevel level, String userName, String password) {
		super(level, userName);
		init(userName, password);
	}
	
	private void init(String userName, String password) {
		//isMuted = Get from userfile with password
		//isBanned = Get from userfile with password
	}
	
	public boolean isMuted() {
		return isMuted;
	}
	
	public boolean isBanned() {
		return isBanned;
	}
}
