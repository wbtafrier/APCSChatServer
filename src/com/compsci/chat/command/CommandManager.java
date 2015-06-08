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
					if (t.getPlayer().getName().equals(arguments)) {
						t.getPlayer().setAdministator(m.getSender());
						return;
					}
				}
			}
			
			if (command.equals(EnumCommand.UNADMIN.getCommand())) {
				
				for (ConnectionThread t : ConnectionManager.getThreads()) {
					if (t.getPlayer().getName().equals(arguments)) {
						t.getPlayer().removeAdministrator(m.getSender());
						return;
					}
				}
			}
			
			if (command.equals(EnumCommand.MOD.getCommand())) {
				
				for (ConnectionThread t : ConnectionManager.getThreads()) {
					if (t.getPlayer().getName().equals(arguments)) {
						t.getPlayer().setModerator(m.getSender());
						return;
					}
				}
			}
			
			if (command.equals(EnumCommand.UNMOD.getCommand())) {
				
				for (ConnectionThread t : ConnectionManager.getThreads()) {
					if (t.getPlayer().getName().equals(arguments)) {
						t.getPlayer().removeModerator(m.getSender());
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
		}
		
		if (m.getSender().getAuthority().ordinal() >= EnumAuthorityLevel.MODERATOR.ordinal()) {
			
			if (command.equals(EnumCommand.MUTE.getCommand())) {
				for (ConnectionThread t : ConnectionManager.getThreads()) {
					if (t.getPlayer().getName().equals(arguments)) {
						t.getPlayer().mute(m.getSender());
						return;
					}
				}
			}
			
			if (command.equals(EnumCommand.UNMUTE.getCommand())) {
				for (ConnectionThread t : ConnectionManager.getThreads()) {
					if (t.getPlayer().getName().equals(arguments)) {
						t.getPlayer().unmute(m.getSender());
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
					if (t.getPlayer().getName().equals(arguments)) {
						InputManager.filterInput(new Message(m.getSender(), t.getPlayer(), arguments));
						return;
					}
				}
			}
		}
		
//		else if (m.getMessage().equals(""))
		//TODO: Check if the user has the correct authority, if not Log suspicious output and then send error message. else send to the method for that command.
		
		//if command is whisper, create new message with updated values.
		//if broadcast, create new message with updated values.
	}
}
