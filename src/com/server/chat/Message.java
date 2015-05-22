package com.server.chat;

import java.util.logging.Level;

import com.server.user.User;
import com.server.util.SloverseLogger;

public class Message {

//	private static final int MAXIMUM_LENGTH = 300;
	private String message;
	private User user;
	
	public Message(User u, String input) {
		if (!checkInput(input)) return;
		initMessage(u, input);
	}
	
	private void initMessage(User u, String input) {
		user = u;
		message = input;
	}
	
	private boolean checkInput(String input) {
		if (!InputManager.isMessageAcceptable(input)) {
			SloverseLogger.logErrorMessage(Level.WARNING, "Message is not in the correct format!");
			return false;
		}
		return true;
	}
	
	public User getUser() {
		return user;
	}
	
	public String getMessage() {
		return message;
	}
}
