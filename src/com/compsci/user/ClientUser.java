package com.compsci.user;


public abstract class ClientUser extends User {
	
	private static final long serialVersionUID = -2906357802993633627L;
	
	public ClientUser(EnumAuthorityLevel level, String userName) {
		super(level, userName);
	}
}
