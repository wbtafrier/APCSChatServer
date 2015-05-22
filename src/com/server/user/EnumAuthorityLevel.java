package com.server.user;

public enum EnumAuthorityLevel {

	PLAYER(0), MODERATOR(1), SERVER(2);
	
	private final int AUTHORITY_LEVEL;
	
	private EnumAuthorityLevel(int level) {
		AUTHORITY_LEVEL = level;
	}
	
	public int getAuthority() {
		return AUTHORITY_LEVEL;
	}
}
