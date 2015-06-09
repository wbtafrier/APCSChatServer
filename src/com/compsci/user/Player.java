package com.compsci.user;



public class Player extends ClientUser {

	private static final long serialVersionUID = -5453952520070522951L;
	
	public Player(String userName) {
		super(EnumAuthorityLevel.PLAYER, userName);
	}
	
	protected Player(EnumAuthorityLevel level, String userName) {
		super(level, userName);
	}
}
