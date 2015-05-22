package com.server.user;

public class ServerUser extends User {

	public ServerUser() {
		super(EnumAuthorityLevel.SERVER, "SERVER");
	}
}
