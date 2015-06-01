package com.compsci.connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.compsci.chat.InputManager;
import com.compsci.chat.Message;
import com.compsci.chat.command.EnumCommand;
import com.compsci.core.SloverseServer;
import com.compsci.user.User;

public class ConnectionManager {

	private static List<ConnectionThread> connectedThreads = new ArrayList<>();
	
	public static synchronized List<ConnectionThread> getThreads() {
		return connectedThreads;
	}
	
	public static synchronized void connectThread(ConnectionThread thread) throws IOException {
		connectedThreads.add(thread);
		InputManager.filterInput(new Message(SloverseServer.SERVER, thread.getPlayer().getName() + " has joined the server!"));
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
	
	public static synchronized void disconnectThread(User user) {
		
		for (int i = 0; i < connectedThreads.size(); i++) {
			if (connectedThreads.get(i).getPlayer().equals(user)) {
				saveData(connectedThreads.get(i));
				connectedThreads.remove(i);
				break;
			}
		}
	}
	
	public static synchronized void disconnectAllThreads() throws IOException {
		
		InputManager.filterInput(new Message(SloverseServer.SERVER, EnumCommand.BROADCAST.getCommand() + " Disconnecting all users... Sorry!"));
		for (int i = connectedThreads.size() - 1; i >= 0; i--) {
			saveData(connectedThreads.get(i));
			connectedThreads.remove(i);
		}
	}
	
	public static synchronized void saveData(ConnectionThread thread) {
		//Save everything about the thread to their file.
	}
}
