package com.compsci.user;

import java.io.Serializable;

public class UserAction implements Serializable {

	private static final long serialVersionUID = 8021042238406256727L;
	
	private User user;
	private Object arguments;
	private EnumAction action;
	
	public UserAction(User u, Object args, EnumAction currentAction) {
		user = u;
		arguments = args;
		action = currentAction;
	}
	
	public UserAction(User u, EnumAction currentAction) {
		this(u, null, currentAction);
	}
	
	public String getUsername() {
		return user.getName();
	}
	
	public Object getArguments() {
		return arguments;
	}
	
	public EnumAction getAction() {
		return action;
	}

}
