package com.server.connection;

import java.util.ArrayList;
import java.util.List;

import com.server.chat.InputManager;
import com.server.chat.Message;
import com.server.chat.command.EnumCommand;
import com.server.core.SloverseServer;

public class ConnectionManager {

	private static List<ConnectionThread> connectedThreads = new ArrayList<>();
	
	public static synchronized List<ConnectionThread> getThreads() {
		return connectedThreads;
	}
	
	public static synchronized void connectThread(ConnectionThread thread) {
		connectedThreads.add(thread);
	}
	
	public static synchronized void disconnectThread(ConnectionThread thread) {
		
		for (int i = 0; i < connectedThreads.size(); i++) {
			if (connectedThreads.get(i).equals(thread)) {
				saveData(thread);
				connectedThreads.remove(i);
				break;
			}
		}
	}
	
	public static synchronized void disconnectAllThreads() {
		
		InputManager.filterInput(new Message(SloverseServer.SYSTEM, EnumCommand.BROADCAST.getCommand() + " Disconnecting all users... Sorry!"));
		for (int i = connectedThreads.size() - 1; i >= 0; i--) {
			saveData(connectedThreads.get(i));
			connectedThreads.remove(i);
		}
	}
	
	public static synchronized void saveData(ConnectionThread thread) {
		//Save everything about the thread to their file.
	}
}
