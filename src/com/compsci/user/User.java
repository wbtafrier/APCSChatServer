package com.compsci.user;

import java.io.IOException;
import java.io.Serializable;

import com.compsci.chat.InputManager;
import com.compsci.chat.Message;
import com.compsci.core.SloverseServer;

public abstract class User implements Serializable {
	
	private static final long serialVersionUID = 7945909636295301742L;
	
	private String name;
	private EnumAuthorityLevel authority;
	private boolean isMuted;
	private boolean isBanned;
	
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
	
	public boolean isMuted() {
		return isMuted;
	}
	
	public boolean isBanned() {
		return isBanned;
	}
	
	public void mute(User user) {
		try {
			InputManager.filterInput(new Message(SloverseServer.SERVER, this.getName() + " has been muted by " + user.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		isMuted = true;
	}
	
	public void unmute(User user) {
		try {
			InputManager.filterInput(new Message(SloverseServer.SERVER, this.getName() + " has been unmuted by " + user.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		isMuted = false;
	}
}
