package com.compsci.user;

import java.io.IOException;

import com.compsci.chat.InputManager;
import com.compsci.chat.Message;
import com.compsci.core.SloverseServer;


public class Player extends ClientUser {

	private static final long serialVersionUID = -5453952520070522951L;
	
	private boolean isModerator;
	private boolean isAdministrator;
	
	public Player(String userName) {
		super(EnumAuthorityLevel.PLAYER, userName);
	}
	
	protected Player(EnumAuthorityLevel level, String userName) {
		super(level, userName);
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
