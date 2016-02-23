package com.compsci.user;

public enum EnumAction {

	DISCONNECT(0), MOD(1), ADMIN(2), MUTE(3), UNMOD(4), UNADMIN(5), UNMUTE(6), NICK(7), AFK(8);
	
	private final int ACTION;
	
	private EnumAction(int action) {
		ACTION = action;
	}
	
	public int getAction() {
		return ACTION;
	}
}
