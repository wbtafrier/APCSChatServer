package com.compsci.command;

import java.util.ArrayList;
import java.util.List;

public class CommandControlPanel {

	private static final List<String> COMMAND_LIST = EnumCommand.getCommandList();
	private static final int MAX_COMMAND_LENGTH = 20;
	
	public static String[] formatBroadcast(String input) {
		
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
	
	public static void countdownStop(String input) {
		
		
		
	}
	
	public static String formatHelp() {
		
		String commands = "Here is a list of all the commands:\n";
		for (int i = 0; i < COMMAND_LIST.size() - 1; i++) {
			commands += " * " + COMMAND_LIST.get(i) + "\n";
		}
		commands += " * " + COMMAND_LIST.get(COMMAND_LIST.size() - 1);
		return commands;
	}
	
	//This can definitely be simplified.
	public static String formatSuggestedCommands(String input) {
		int letterCounter = 0;
		
		if (input.length() > MAX_COMMAND_LENGTH) {
			return "Unsure what command you were trying to enter. " + formatHelp();
		}
		List<String> suggestedList = new ArrayList<>();
		String commands = "Did you mean...\n";
		for (int i = 0; i < COMMAND_LIST.size(); i++) {
			for (int j = 0; j < input.length(); j++) {
				if (COMMAND_LIST.get(i).contains(input.substring(j, j + 1))) {
					letterCounter++;
				}
			}
			
			if (COMMAND_LIST.get(i).equals(EnumCommand.COMMANDHELP.getCommand()) || letterCounter > COMMAND_LIST.get(i).length() / 3) {
				suggestedList.add(COMMAND_LIST.get(i));
			}
			letterCounter = 0;
		}
		
		for (int i = 0; i < suggestedList.size() - 1; i++) {
			commands += suggestedList.get(i) + "\n";
		}
		commands += suggestedList.get(suggestedList.size() - 1);
		return commands;
	}
}
