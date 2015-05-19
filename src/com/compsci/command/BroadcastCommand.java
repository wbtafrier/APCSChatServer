package com.compsci.command;

public class BroadcastCommand {

	public static String[] formatCommand(String input) {
		
		String[] formattedCommand = new String[2];
		int startLabel = input.indexOf("[");
		int endLabel = input.indexOf("]");
		
		if (startLabel != -1 && endLabel != -1) {
			formattedCommand[0] = input.substring(startLabel + 1, endLabel);
			
			if (input.length() > endLabel + 1) {
				formattedCommand[1] = input.substring(endLabel + 1).trim();
			}
		}
		else {
			formattedCommand[0] = "BROADCAST";
			
			if (input.length() > EnumCommand.BROADCAST.getCommand().length() + 1) {
				formattedCommand[1] = input.substring(EnumCommand.BROADCAST.getCommand().length()).trim();
			}
		}
		return formattedCommand;
	}
}
