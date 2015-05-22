package com.server.chat;

import com.server.chat.command.CommandManager;

public class InputManager {

	public static void filterInput(Message m) {
		
		if (m != null && m.getMessage() != null && (m.getMessage().length() > 1) && (m.getMessage().substring(0, 1).equals("/"))) {
			CommandManager.filterCommand(m);
		}
		else {
			ChatManager.filterMessage(m);
		}
	}
	
	public static boolean isMessageAcceptable(String input) {
		if ((input != null) && (!input.isEmpty()) && (!input.contains("\t")))
			return true;
		return false;
	}
}
