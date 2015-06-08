package com.compsci.chat;

import java.io.IOException;

import com.compsci.chat.command.CommandManager;
import com.compsci.core.SloverseServer;

public class InputManager {

	public static void filterInput(Message m) throws IOException {
		
		if (!isMessageAcceptable(m.getMessage())) {
			return;
		}
		
		if (m instanceof Broadcast) {
			ChatManager.publicMessage(m);
		}
		else if (m != null && m.getSender().isMuted()) {
			ChatManager.filterMessage(new Message(SloverseServer.SERVER, m.getSender(), "You are muted in the chat!"));
		}
		else if (m != null && m.getMessage() != null && (m.getMessage().length() > 1) && (m.getMessage().substring(0, 1).equals("/"))) {
			CommandManager.filterCommand(m);
		}
		else {
			ChatManager.filterMessage(m);
		}
	}
	
	public static boolean isMessageAcceptable(String input) {
		if ((input != null) && (!input.isEmpty())  && (!input.contains("\t"))) {
			return true;
		}
		return false;
	}
}
