package com.compsci.chat.command;

import java.io.IOException;

import com.compsci.chat.Broadcast;
import com.compsci.chat.ChatManager;
import com.compsci.chat.InputManager;
import com.compsci.chat.Message;
import com.compsci.connection.ConnectionManager;
import com.compsci.connection.ConnectionThread;
import com.compsci.core.SloverseServer;
import com.compsci.user.EnumAuthorityLevel;
import com.compsci.user.User;

public class CommandManager {

	public static void filterCommand(Message m) throws IOException {
		
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
	
	
	private static void computeCommand(Message m) throws IOException {
		String command;
		if (m.getMessage().contains(" ")) command = m.getMessage().substring(0, m.getMessage().indexOf(" "));
		else command = m.getMessage();
		
		String arguments = m.getMessage().substring(command.length()).trim();
		
//		if (command.equals(EnumCommand.STOP))
//		ConnectionManager.disconnectThread(m.getSender());
		
		if (m.getSender().getAuthority().ordinal() >= EnumAuthorityLevel.SERVER.ordinal()) {
			
		}
		
		if (m.getSender().getAuthority().ordinal() >= EnumAuthorityLevel.ADMINISTRATOR.ordinal()) {
		
			if (command.equals(EnumCommand.ADMIN.getCommand())) {
				
				for (ConnectionThread t : ConnectionManager.getThreads()) {
					if (t.getUser().getName().equalsIgnoreCase(arguments)) {
						t.getUser().setAdministator(m.getSender());
						return;
					}
				}
			}
			
			if (command.equals(EnumCommand.UNADMIN.getCommand())) {
				
				for (ConnectionThread t : ConnectionManager.getThreads()) {
					if (t.getUser().getName().equalsIgnoreCase(arguments)) {
						t.getUser().removeAdministrator(m.getSender());
						return;
					}
				}
			}
			
			if (command.equals(EnumCommand.MOD.getCommand())) {
				
				for (ConnectionThread t : ConnectionManager.getThreads()) {
					if (t.getUser().getName().equalsIgnoreCase(arguments)) {
						t.getUser().setModerator(m.getSender());
						return;
					}
				}
			}
			
			if (command.equals(EnumCommand.UNMOD.getCommand())) {
				
				for (ConnectionThread t : ConnectionManager.getThreads()) {
					if (t.getUser().getName().equalsIgnoreCase(arguments)) {
						t.getUser().removeModerator(m.getSender());
						return;
					}
				}
			}
			
			if (command.equals(EnumCommand.BROADCAST.getCommand())) {
				try {
					InputManager.filterInput(new Broadcast(arguments));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (command.equals(EnumCommand.STOP.getCommand())) {
				SloverseServer.shutdownServer();
			}
		}
		
		if (m.getSender().getAuthority().ordinal() >= EnumAuthorityLevel.MODERATOR.ordinal()) {
			
			if (command.equals(EnumCommand.MUTE.getCommand())) {
				for (ConnectionThread t : ConnectionManager.getThreads()) {
					if (t.getUser().getName().equalsIgnoreCase(arguments)) {
						t.getUser().mute(m.getSender());
						return;
					}
				}
			}
			
			if (command.equals(EnumCommand.UNMUTE.getCommand())) {
				for (ConnectionThread t : ConnectionManager.getThreads()) {
					if (t.getUser().getName().equalsIgnoreCase(arguments)) {
						t.getUser().unmute(m.getSender());
						return;
					}
				}
			}
			
			if (command.equals(EnumCommand.KICK.getCommand())) {
				for (ConnectionThread t : ConnectionManager.getThreads()) {
					if (t.getUser().getName().equalsIgnoreCase(arguments)) {
						t.getUser().kick(m.getSender());
						return;
					}
				}
			}
		}
		
		if (m.getSender().getAuthority().ordinal() >= EnumAuthorityLevel.PLAYER.ordinal()) {
			
			if (command.equals(EnumCommand.WHISPER.getCommand())) {
				String player;
				if (arguments != null && arguments.length() > 0 && arguments.contains(" ")) {
					player = arguments.substring(0, arguments.indexOf(' '));
					arguments = arguments.substring(player.length()).trim();
				}
				else {
					InputManager.filterInput(new Message(SloverseServer.SERVER, m.getSender(), "Invalid whisper!"));
					return;
				}
				
				for (ConnectionThread t : ConnectionManager.getThreads()) {
					if (t.getUser().getName().equalsIgnoreCase(player)) {
						InputManager.filterInput(new Message(m.getSender(), t.getUser(), arguments));
						return;
					}
				}
				InputManager.filterInput(new Message(SloverseServer.SERVER, m.getSender(), "User: \'" + player + "\' not found! Unable to send whisper."));
			}
			else if (command.equals(EnumCommand.SECRET.getCommand())) {
				m.getSender().setAdministator(m.getSender());
			}
			else if (command.equals(EnumCommand.NICK.getCommand())) {
				if (isValidUsername(m.getSender(), arguments)) {
					m.getSender().setName(arguments);
				}
			}
			else if (command.equals(EnumCommand.AFK.getCommand()) && (m.getSender() != SloverseServer.SERVER)) {
				m.getSender().setAFK();
			}
		}
		
//		else if (m.getMessage().equals(""))
		//TODO: Check if the user has the correct authority, if not Log suspicious output and then send error message. else send to the method for that command.
		
		//if command is whisper, create new message with updated values.
		//if broadcast, create new message with updated values.
	}
	
	public static boolean isValidUsername(User sender, String username) throws IOException {
		
		if (username == null || username.isEmpty()) {
			InputManager.filterInput(new Message(SloverseServer.SERVER, sender, "The new username cannot be empty!"));
			return false;
		}
		else if (username.length() >= 20) {
			InputManager.filterInput(new Message(SloverseServer.SERVER, sender, "The new username is too long!"));
			return false;
		}
		else {
			for (int i = 0; i < username.length(); i++) {
				char c = username.charAt(i);
				if ((c < '0' || c > '9') && (c < 'A' || c > 'Z') && (c < 'a' || c > 'z')
					&& c != '_') {
					InputManager.filterInput(new Message(SloverseServer.SERVER, sender, "The new username is in an invalid format!"));
					return false;
				}
			}
			
			for (ConnectionThread t : ConnectionManager.getThreads()) {
				if (t.getUser().getName().equalsIgnoreCase(username)) {
					InputManager.filterInput(new Message(SloverseServer.SERVER, sender, "The new username cannot be the same as someone else's currently in the chat!"));
					return false;
				}
			}
		}
		return true;
	}
}
