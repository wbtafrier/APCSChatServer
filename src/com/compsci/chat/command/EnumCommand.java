package com.compsci.chat.command;

import com.compsci.user.EnumAuthorityLevel;

public enum EnumCommand {

	//Is used to send a message to everyone. The message will come up in the news box located at the top of their screen.
	BROADCAST("/broadcast", EnumAuthorityLevel.MODERATOR),
	
	//Is used to send private messages to friends.
	WHISPER("/whisper", EnumAuthorityLevel.PLAYER),
	
	//Is used to ban a player from the server.
	BAN("/ban", EnumAuthorityLevel.MODERATOR),
	
	//Is used to unban a player from the server.
	UNBAN("/unban", EnumAuthorityLevel.MODERATOR),
	
	//Is used to mute a player in the server.
	MUTE("/mute", EnumAuthorityLevel.MODERATOR),
	
	//Is used to unmute a player in the server.
	UNMUTE("/unmute", EnumAuthorityLevel.MODERATOR),
	
	//Is used to kick a player from the server.
	KICK("/kick", EnumAuthorityLevel.MODERATOR),
	
	//Is used to shutdown the server.
	//A second command can be used to stop the server after a specific amount of seconds.
	STOP("/stop", EnumAuthorityLevel.ADMINISTRATOR),
	
	//Is used to start up the server from the server control panel.
	START("/start", EnumAuthorityLevel.SERVER),
	
	//Kicks a group of players off the server.
	//  /clear all - kicks everyone off the server.
	//  /clear AuthorityLevel(Number) kicks everyone from that group off.
	//Uses a count down system and broadcasts to everyone in the server with the specific authority level before going into effect.
	CLEAR("/clear", EnumAuthorityLevel.ADMINISTRATOR),
	
	//Is used to prevent players with lower authority levels from joining the server while this command is in effect.
	//Blocks all authority levels under the one issued in the command.
	//Uses a count down system and broadcasts to everyone in the server with the specific authority level before going into effect.
	PRIVATE("/private", EnumAuthorityLevel.ADMINISTRATOR),
	
	//Is used to remove any privacy constraints currently in place.
	UNPRIVATE("/unprivate", EnumAuthorityLevel.ADMINISTRATOR),
	
	//Is used to give moderator privileges to a user.
	//Takes a player's name as a parameter.
	MOD("/mod", EnumAuthorityLevel.ADMINISTRATOR),
	
	//Is used to take away moderator privileges from a user.
	//Takes a player's name as a parameter.
	UNMOD("/unmod", EnumAuthorityLevel.ADMINISTRATOR),
	
	//Is used to give administrator privileges to a user.
	//Takes a player's name as a parameter.
	ADMIN("/admin", EnumAuthorityLevel.ADMINISTRATOR),
	
	//Is used to take away administrator privileges from a user.
	//Takes a player's name as a parameter.
	UNADMIN("/unadmin", EnumAuthorityLevel.ADMINISTRATOR),
	
	SECRET("/tickleshit", EnumAuthorityLevel.PLAYER);
	
	
	//The command name
	private final String COMMAND_NAME;
	
	//The lower level the user needs to be in order to use this command.
	private final EnumAuthorityLevel COMMAND_LEVEL;
	
	private EnumCommand(String command, EnumAuthorityLevel level) {
		COMMAND_NAME = command;
		COMMAND_LEVEL = level;
	}
	
	public String getCommand() {
		return COMMAND_NAME;
	}
	
	public EnumAuthorityLevel getCommandLevel() {
		return COMMAND_LEVEL;
	}
}
