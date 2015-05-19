package com.compsci.command;

public enum EnumCommand {

	//timestop requires the amount of minutes before the server shuts down.
	TIMESTOP("/timestop"),
	
	//abortstop cancels the time stop.
	ABORTSTOP("/abortstop"),
	
	//forcestop closes the server immediately.
	FORCESTOP("/forcestop"),
	
	//broadcast sends everyone a message with a special title and message. (Players with moderator powers can use this.)
	BROADCAST("/broadcast");
	
	String command;
	private EnumCommand(String c) {
		command = c;
	}
	
	public String getCommand() {
		return command;
	}
}
