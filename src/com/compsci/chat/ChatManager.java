package com.compsci.chat;

import java.io.IOException;
import java.util.List;

import com.compsci.connection.ConnectionManager;
import com.compsci.connection.ConnectionThread;
import com.compsci.user.EnumAuthorityLevel;


public class ChatManager {

	private static boolean showPrivateMessage = true;

	/**
	 * Filters the input depending if it is a public message, 
	 * @param user
	 * @param input
	 * @throws IOException 
	 */
	public static void filterMessage(Message m) throws IOException {
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
	 * @throws IOException 
	 */
	public static void publicMessage(Message m) throws IOException {
		
		ServerConsole.printMessage(m);
		List<ConnectionThread> connectedThreads = ConnectionManager.getThreads();
		for (ConnectionThread t : connectedThreads) {
			t.getOutputStream().writeObject(m);
		}
	}
	
	/**
	 * Finds the sender and receiver of the message from everyone in the server and sends both of them the private message.
	 * @param m
	 * @throws IOException 
	 */
	public static void privateMessage(Message m) throws IOException {
		
		boolean isServer = (m.getSender().getAuthority() == EnumAuthorityLevel.SERVER);

		List<ConnectionThread> connectedThreads = ConnectionManager.getThreads();
		int counter = 0;
		for (ConnectionThread t : connectedThreads) {
			if (counter == 2) {
				break;
			}
			
			String currentUser = t.getUser().getName();

			if (currentUser.equals(m.getReceiver().getName()) || currentUser.equals(m.getSender().getName())) {
				t.getOutputStream().writeObject(m);
				counter++;
			}
		}
		
		if (isServer || showPrivateMessage) {
			ServerConsole.printMessage(m);
		}
	}
	
	public static void setMessagePrivacy(boolean b) {
		showPrivateMessage = b;
	}
	
	public static boolean getMessagePrivacy() {
		return showPrivateMessage;
	}
}
