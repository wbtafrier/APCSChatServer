package com.server.chat;


public class ChatManager {

	private static boolean showPrivateMessage = false;

	/**
	 * Filters the input depending if it is a public message, 
	 * @param user
	 * @param input
	 */
	public static void filterMessage(Message m) {
		//TODO: Filter the message coming in and see which method the message should go to.
	}
	
	/**
	 * Prints a message to everyone in the same room, including the server console.
	 * @param user : The user that said the message.
	 * @param input : The message from the user.
	 */
	public static void publicMessage(Message m) {
		
	}
	
	public static void privateMessage(Message m) {
		
	}
	
	public static void setMessagePrivacy(boolean b) {
		showPrivateMessage = b;
	}
	
	public static boolean getMessagePrivacy() {
		return showPrivateMessage;
	}
}
