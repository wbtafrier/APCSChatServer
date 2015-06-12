package com.compsci.user;

import java.io.Serializable;

public class UserAction implements Serializable {

	private static final long serialVersionUID = 8021042238406256727L;
	
	private String user;
	private EnumAction action;
	
	public UserAction(String username, EnumAction currentAction) {
		user = username;
		action = currentAction;
	}
	
	public String getUsername() {
		return user;
	}
	
	public EnumAction getAction() {
		return action;
	}

}
