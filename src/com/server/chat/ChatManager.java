package com.server.chat;


public class ChatManager {

	private static boolean showPrivateMessage = false;
	
	public static void setMessagePrivacy(boolean b) {
		showPrivateMessage = b;
	}
	
}
