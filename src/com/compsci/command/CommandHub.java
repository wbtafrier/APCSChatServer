package com.compsci.command;

import com.compsci.core.ConnectionManager;
import com.compsci.core.SloverseServer;

public class CommandHub {
	
//	private static final String SPACE = " ";
	
	/**
	 * Precondition: Send in an unformatted String.
	 */
	public static boolean isCommandFormat(String message) {
		return (message != null && message.length() > 1 && message.charAt(0) == '/');
	}
	
	public static void computeCommand(String input) {
		
		if (input.startsWith(EnumCommand.BROADCAST.getCommand() + " ")) {
			
			String[] broadcastCommand = BroadcastCommand.formatCommand(input);
			ConnectionManager.sendBroadcast(broadcastCommand[0], broadcastCommand[1]);
		}
		else if (input.equalsIgnoreCase(EnumCommand.FORCESTOP.getCommand())) {
			SloverseServer.closeServer();
		}
		else if (input.equalsIgnoreCase(EnumCommand.ABORTSTOP.getCommand())) {
			//Stop the timer that is counting down for the server to stop.
		}
		else {
			ConnectionManager.sendBroadcast("SERVER", "ERROR: \'" + input + "\' is not a valid command.");
		}
	}
	
//	private static List<String> splitCommand(String command) {
//		
//		List<String> parameters = new ArrayList<>();
//		int startIndex = 0, endIndex = 0;
//		
//		for (int i = 0; i < command.length() - 1; i++) {
//			if (command.substring(i, i + 1).equals(SPACE)) {
//				endIndex = i;
//				parameters.add(command.substring(startIndex, endIndex));
//				command = command.substring(endIndex).trim();
//				i = 0;
//				startIndex = i;
//			}
//			if (i == command.length() - 2) {
//				parameters.add(command.substring(startIndex));
//			}
//		}
//		return parameters;
//	}
}
