package com.compsci.user;

public class ServerUser extends User {

	private static final long serialVersionUID = 4950193002040177480L;
	
	public ServerUser() {
		super(EnumAuthorityLevel.SERVER, "SERVER");
	}
}
