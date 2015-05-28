package com.compsci.user;

import java.io.Serializable;

public abstract class User implements Serializable {
	
	private static final long serialVersionUID = 7945909636295301742L;
	
	private String name;
	private EnumAuthorityLevel authority;
	
	private static int nextAvailableID = 0;
	private int userID;
	
	public User(EnumAuthorityLevel level, String userName) {
		authority = level;
		name = userName;
		//for creating a new user. If they re-login, get the userID from their file.
		userID = nextAvailableID++;
	}
	
	public EnumAuthorityLevel getAuthority() {
		return authority;
	}
	
	public void setAuthority(EnumAuthorityLevel level) {
		authority = level;
	}
	
	public String getName() {
		return name;
	}
	
	public int getUserID() {
		return userID;
	}
}
