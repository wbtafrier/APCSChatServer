package com.server.user;


public abstract class User {
	
	private String name;
	private EnumAuthorityLevel authority;
	
	public User(EnumAuthorityLevel level, String userName) {
		authority = level;
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
}
