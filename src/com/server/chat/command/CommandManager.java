package com.server.chat.command;

import com.server.chat.ChatManager;
import com.server.chat.Message;

public class CommandManager {

	public static void filterCommand(Message m) {
		
		if (!isCommandAcceptable(m)) {
			ChatManager.filterMessage(m);
		}
		else {
			computeCommand(m);
		}
	}
	
	/**
	 * Checks if the command is an actual command.
	 * @param m : The message.
	 * @return : True if the command is real.
	 */
	private static boolean isCommandAcceptable(Message m) {
		
		String command;
		if (m.getMessage().contains(" ")) {
			command = m.getMessage().substring(0, m.getMessage().indexOf(" "));
		}
		else {
			command = m.getMessage();
		}
		
		for (int i = 0; i < EnumCommand.values().length; i++) {
			if (command.equalsIgnoreCase(EnumCommand.values()[i].getCommand())) {
				return true;
			}
		}
		return false;
	}
	
	
	private static void computeCommand(Message m) {
		System.out.println("Derp!");
		//TODO: Check if the user has the correct authority, if not Log suspicious output and then send error message. else send to the method for that command.
	}
}
