package com.server.chat;

import java.util.logging.Level;

import com.server.user.User;
import com.server.util.SloverseLogger;

public class InputManager {

	public static void filterInput(User user, String input) {
		
		if (!isMessageAcceptable(input)) {
			SloverseLogger.logErrorMessage(Level.WARNING, "");
			return;
		}
		
	}
	
	public static boolean isMessageAcceptable(String input) {
		if ((input != null) && (!input.isEmpty()) && (!input.contains("\t")))
			return true;
		return false;
	}
}
