package com.compsci.format;

import com.compsci.core.ConnectionManager;

public class CommandHub {

	private static final String[] commandList = new String[] {
		"", "",
	};
	
	/**
	 * Precondition: Send in an unformatted String.
	 */
	public static boolean isCommand(String message) {
		return (message != null && message.length() > 1 && message.charAt(0) == '/');
	}
	
	public static void computeCommand(String message) {
		
//		for (String s : commandList) {
//			if (message.equalsIgnoreCase(s)) {
//				return true;
//			}
//		}
		
		if (message.equalsIgnoreCase(commandList[0])) {
			
		}
//		else if ()
		
		ConnectionManager.sendBroadcast("SERVER", "ERROR: /'" + message + "/' is not a valid command.");
	}
}
