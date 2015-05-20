package com.compsci.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public enum EnumCommand {

	//abortstop cancels the time stop.
	ABORTCOUNTDOWN("/abortcountdown"),
	
	//broadcast sends everyone a message with a special title and message. (Players with moderator powers can use this.)
	BROADCAST("/broadcast"),
	
	//timestop requires the amount of minutes before the server shuts down.
	COUNTDOWNSTOP("/countdownstop"),
	
	//help shows a list of all the commands.
	COMMANDHELP("/help"),
	
	//forcestop closes the server immediately.
	FORCESTOP("/stop");
	
	private final String COMMAND;
	private EnumCommand(String c) {
		COMMAND = c;
	}
	
	public String getCommand() {
		return COMMAND;
	}
	
	public static List<String> getCommandList() {
		List<String> commandList = new ArrayList<>();
		
		for (int i = 0; i < EnumCommand.values().length; i++) {
			commandList.add(EnumCommand.values()[i].getCommand());
		}
		
		Collections.sort(commandList, new Comparator<String>() {
	        @Override
	        public int compare(String s1, String s2) {
	            return s1.compareToIgnoreCase(s2);
	        }
	    });
		
		return commandList;
	}
}
