package com.server.user;


public abstract class User {

	private EnumAuthorityLevel authority;
	
	public User(EnumAuthorityLevel level) {
		authority = level;
	}
	
	public EnumAuthorityLevel getAuthority() {
		return authority;
	}
	
	public void setAuthority(EnumAuthorityLevel level) {
		authority = level;
	}
}
