package com.compsci.user;

import java.io.IOException;
import java.io.Serializable;

import com.compsci.chat.InputManager;
import com.compsci.chat.Message;
import com.compsci.connection.ConnectionManager;
import com.compsci.core.SloverseServer;

public abstract class User implements Serializable {
	
	private static final long serialVersionUID = 7945909636295301742L;
	
	private String name;
	private EnumAuthorityLevel authority;
	private boolean isMuted;
	private boolean isBanned;
	
	private boolean isModerator;
	private boolean isAdministrator;
	
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
			InputManager.filterInput(new Message(SloverseServer.SERVER, this.getName() + " was muted by " + user.getName() + "."));
		} catch (IOException e) {
			e.printStackTrace();
		}
		isMuted = true;
	}
	
	public void unmute(User user) {
		try {
			InputManager.filterInput(new Message(SloverseServer.SERVER, this.getName() + " was unmuted by " + user.getName() + "."));
		} catch (IOException e) {
			e.printStackTrace();
		}
		isMuted = false;
	}
	
	public void kick(User user) {
		try {
			InputManager.filterInput(new Message(SloverseServer.SERVER, this.getName() + " was kicked by " + user.getName() + "!"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ConnectionManager.disconnectThread(this);
	}
	
	public boolean isModerator() {
		return isModerator;
	}
	
	public void setModerator(User granter) {
		if (isModerator) return;
		
		try {
			InputManager.filterInput(new Message(SloverseServer.SERVER, this.getName() + " has been granted the position of moderator by " + granter.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (isAdministrator)  isAdministrator = false;
		isModerator = true;
		setAuthority(EnumAuthorityLevel.MODERATOR);
	}
	
	public void removeModerator(User granter) {
		if (!isModerator) return;
		
		try {
			InputManager.filterInput(new Message(SloverseServer.SERVER, this.getName() + "'s privileges as moderator has been taken away by " + granter.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		isModerator = false;
		setAuthority(EnumAuthorityLevel.PLAYER);
	}
	
	public void setAdministator(User granter) {
		if (isAdministrator) return;
		try {
			InputManager.filterInput(new Message(SloverseServer.SERVER, this.getName() + " has been given administrative privileges by " + granter.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (isModerator) isModerator = false;
		isAdministrator = true;
		setAuthority(EnumAuthorityLevel.ADMINISTRATOR);
	}
	
	public void removeAdministrator(User granter) {
		if (!isAdministrator) return;
		try {
			InputManager.filterInput(new Message(SloverseServer.SERVER, this.getName() + "'s administrative privileges have been taken away by " + granter.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		isAdministrator = false;
		setAuthority(EnumAuthorityLevel.PLAYER);
	}
}
