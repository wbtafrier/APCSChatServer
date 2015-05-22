package com.server.user;

public abstract class ClientUser extends User {
	
	private String name;
	private boolean isMuted;
	private boolean isBanned;
	
	public ClientUser(EnumAuthorityLevel level, String userName, String password) {
		super(level);
		init(userName, password);
	}
	
	private void init(String userName, String password) {
		name = userName;
		//isMuted = Get from userfile with password
		//isBanned = Get from userfile with password
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isMuted() {
		return isMuted;
	}
	
	public boolean isBanned() {
		return isBanned;
	}
}
