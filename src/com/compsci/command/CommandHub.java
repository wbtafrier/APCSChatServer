package com.compsci.command;

import com.compsci.core.ConnectionManager;
import com.compsci.core.PlayerConnectionThread;
import com.compsci.core.SloverseServer;

public class CommandHub {
	
//	private static final String SPACE = " ";
	
	/**
	 * Precondition: Send in an unformatted String.
	 */
	public static boolean isCommandFormat(String message) {
		return (message != null && message.length() > 1 && message.charAt(0) == '/');
	}
	
	public static void computeCommand(PlayerConnectionThread moderator, String input) {
		
		if (input.equalsIgnoreCase(EnumCommand.COMMANDHELP.getCommand())) {
			
			String commandList = CommandControlPanel.formatHelp();
			
			if (moderator == null) {
				ConnectionManager.sendErrorMessage("SYSTEM", commandList);
			}
			else {
				ConnectionManager.sendErrorMessage(moderator, "SYSTEM", commandList);
			}
			
		}
		else if (input.startsWith(EnumCommand.BROADCAST.getCommand() + " ")) {
				String[] broadcastCommand = CommandControlPanel.formatBroadcast(input);
				ConnectionManager.sendBroadcast(moderator, broadcastCommand[0], broadcastCommand[1]);
		}
		else if (input.equalsIgnoreCase(EnumCommand.COUNTDOWNSTOP.getCommand())) {
			
			
			
		}
		else if (input.equalsIgnoreCase(EnumCommand.FORCESTOP.getCommand())) {
			SloverseServer.closeServer();
		}
		else if (input.equalsIgnoreCase(EnumCommand.ABORTCOUNTDOWN.getCommand())) {
			//Stop the timer that is counting down for the server to stop.
		}
		else {
			
			String suggestedCommands = CommandControlPanel.formatSuggestedCommands(input);
			
			if (moderator == null) {
				ConnectionManager.sendErrorMessage("SYSTEM", suggestedCommands);
			}
			else {
				ConnectionManager.sendErrorMessage(moderator, "SYSTEM", suggestedCommands);
			}
			
//			if (moderator == null) {
//				ConnectionManager.sendErrorMessage("SERVER", "ERROR: \'" + input + "\' is not a valid command.");
//			}
//			else {
//				ConnectionManager.sendErrorMessage(moderator, "SERVER", "ERROR: \'" + input + "\' is not a valid command.");
//			}
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
