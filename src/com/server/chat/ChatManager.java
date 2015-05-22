package com.server.chat;

import java.util.List;

import com.server.connection.ConnectionManager;
import com.server.connection.ConnectionThread;
import com.server.user.EnumAuthorityLevel;


public class ChatManager {

	private static boolean showPrivateMessage = false;

	/**
	 * Filters the input depending if it is a public message, 
	 * @param user
	 * @param input
	 */
	public static void filterMessage(Message m) {
		
		if (m.getType().equals(EnumMessageType.PUBLIC)) {
			publicMessage(m);
		}
		else {
			privateMessage(m);
		}
	}
	
	/**
	 * Prints a message to everyone in the same room, including the server console.
	 * @param user : The user that said the message.
	 * @param input : The message from the user.
	 */
	public static void publicMessage(Message m) {
		
		ServerConsole.printMessage(m);
		List<ConnectionThread> connectedThreads = ConnectionManager.getThreads();
		for (ConnectionThread t : connectedThreads) {
			t.getOutputStream().println(m);
		}
	}
	
	/**
	 * Finds the sender and receiver of the message from everyone in the server and sends both of them the private message.
	 * @param m
	 */
	public static void privateMessage(Message m) {
		
		boolean isServer = (m.getSender().getAuthority() == EnumAuthorityLevel.SERVER);
		
		List<ConnectionThread> connectedThreads = ConnectionManager.getThreads();
		for (ConnectionThread t : connectedThreads) {
			String currentUser = t.getPlayer().getName();

			if (currentUser.equals(m.getReceiver().getName())) {
				t.getOutputStream().println(m);
			}
			
			if (isServer) {
				ServerConsole.printMessage(m);
				break;
			}
			
			if (currentUser.equals(m.getSender().getName())) {
				t.getOutputStream().println(m);
			}
		}
	}
	
	public static void setMessagePrivacy(boolean b) {
		showPrivateMessage = b;
	}
	
	public static boolean getMessagePrivacy() {
		return showPrivateMessage;
	}
}
