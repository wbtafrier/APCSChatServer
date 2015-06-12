package com.compsci.user;

public enum EnumAction {

	DISCONNECT(0), MOD(1), ADMIN(2), MUTE(3), DEMOD(4), DEADMIN(5), UNMUTE(6);
	
	private final int ACTION;
	
	private EnumAction(int action) {
		ACTION = action;
	}
	
	public int getAction() {
		return ACTION;
	}
}
