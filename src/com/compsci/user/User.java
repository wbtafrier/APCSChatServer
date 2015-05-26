package com.compsci.user;

import java.io.Serializable;

public abstract class User implements Serializable {
	
	private static final long serialVersionUID = 7945909636295301742L;
	
	private String name;
	private EnumAuthorityLevel authority;
	
	public User(EnumAuthorityLevel level, String userName) {
		authority = level;
		name = userName;
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
