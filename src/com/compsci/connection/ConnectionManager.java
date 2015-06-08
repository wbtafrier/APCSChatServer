package com.compsci.connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.compsci.chat.InputManager;
import com.compsci.chat.Message;
import com.compsci.chat.ServerConsole;
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
		
		if ((connectedThreads.size() - 1) == 1) InputManager.filterInput(new Message(SloverseServer.SERVER, thread.getPlayer(), "There is 1 other user online."));
		else InputManager.filterInput(new Message(SloverseServer.SERVER, thread.getPlayer(), "There are " + (connectedThreads.size() - 1) + " other users online."));
	}
	
	public static synchronized void disconnectThread(ConnectionThread thread) {
		
		for (int i = 0; i < connectedThreads.size(); i++) {
			if (connectedThreads.get(i).equals(thread)) {
				saveData(thread);
				connectedThreads.remove(i);
				break;
			}
		}
		
		try {
			InputManager.filterInput(new Message(SloverseServer.SERVER, thread.getPlayer().getName() + " left the server."));
		} catch (IOException e) {
			e.printStackTrace();
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
		
		try {
			InputManager.filterInput(new Message(SloverseServer.SERVER, user.getName() + " left the server."));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized void disconnectAllThreads() throws IOException {
		
		InputManager.filterInput(new Message(SloverseServer.SERVER, EnumCommand.BROADCAST.getCommand() + " Disconnecting all users... Sorry!"));
		for (int i = connectedThreads.size() - 1; i >= 0; i--) {
			saveData(connectedThreads.get(i));
			connectedThreads.remove(i);
		}
	}
	
	public synchronized static boolean isUniqueUsername(User user) throws IOException {
		
		if (user.getName().equalsIgnoreCase("SERVER")) {
			return false;
		}
		
		for (int i = 0; i < connectedThreads.size(); i++) {
			if (user.getName().equalsIgnoreCase(connectedThreads.get(i).getPlayer().getName())) {
				return false;
			}
		}
		return true;
	}
	
	public synchronized static void sendData(ConnectionThread t, Object o) throws IOException {
		
		try {
			t.getOutputStream().writeObject(o);
		} catch (IOException e) {
			ServerConsole.printMessage(new Message(SloverseServer.SERVER, "Error transmitting object over the network. :("));
			e.printStackTrace();
		}
	}
	
	public static synchronized void saveData(ConnectionThread thread) {
		//Save everything about the thread to their file.
	}
}
