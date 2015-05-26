package com.compsci.user;

public enum EnumAuthorityLevel {

	PLAYER(0), MODERATOR(1), ADMINISTRATOR(2), SERVER(3);
	
	private final int AUTHORITY_LEVEL;
	
	private EnumAuthorityLevel(int level) {
		AUTHORITY_LEVEL = level;
	}
	
	public int getAuthority() {
		return AUTHORITY_LEVEL;
	}
}
