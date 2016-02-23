package com.compsci.connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.compsci.chat.InputManager;
import com.compsci.chat.Message;
import com.compsci.chat.ServerConsole;
import com.compsci.chat.command.EnumCommand;
import com.compsci.core.SloverseServer;
import com.compsci.user.EnumAction;
import com.compsci.user.User;
import com.compsci.user.UserAction;
import com.compsci.util.SloverseLogger;

public class ConnectionManager {

	private static List<ConnectionThread> connectedThreads = new ArrayList<>();
	
	public static synchronized List<ConnectionThread> getThreads() {
		return connectedThreads;
	}
	
	public static synchronized void connectThread(ConnectionThread thread) throws IOException {
		connectedThreads.add(thread);
		for (ConnectionThread t : connectedThreads) {
			t.getOutputStream().writeObject(thread.getUser());
			thread.getOutputStream().writeObject(t.getUser());
		}		
		InputManager.filterInput(new Message(SloverseServer.SERVER, thread.getUser().getName() + " has joined the server!"));
		
		if ((connectedThreads.size() - 1) == 1) InputManager.filterInput(new Message(SloverseServer.SERVER, thread.getUser(), "There is 1 other user online."));
		else InputManager.filterInput(new Message(SloverseServer.SERVER, thread.getUser(), "There are " + (connectedThreads.size() - 1) + " other users online."));
	}
	
	public static synchronized void disconnectThread(ConnectionThread thread) {
		
		for (int i = 0; i < connectedThreads.size(); i++) {
			if (connectedThreads.get(i).equals(thread)) {
				saveData(thread);
				ConnectionThread t = connectedThreads.remove(i);
				try {
					UserAction disconnect = new UserAction(t.getUser(), EnumAction.DISCONNECT);
					sendDataToAll(disconnect);
					InputManager.filterInput(new Message(SloverseServer.SERVER, t.getUser().getName() + " left the server."));
					t.getSocket().close();
				} catch (IOException e) {
					SloverseLogger.logErrorMessage(Level.WARNING, "Disconnected thread tried typing!");
				}
				break;
			}
		}
	}
	
	public static synchronized void disconnectThread(User user) {
		
		for (int i = 0; i < connectedThreads.size(); i++) {
			if (connectedThreads.get(i).getUser().equals(user)) {
				disconnectThread(connectedThreads.get(i));
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
	
	public synchronized static boolean isUniqueUsername(User user) throws IOException {
		
		if (user.getName().equalsIgnoreCase("SERVER")) {
			return false;
		}
		
		for (int i = 0; i < connectedThreads.size(); i++) {
			if (user.getName().equalsIgnoreCase(connectedThreads.get(i).getUser().getName())) {
				return false;
			}
		}
		return true;
	}
	
	public synchronized static void sendData(ConnectionThread t, Object o) {
		
		try {
			t.getOutputStream().writeObject(o);
		} catch (IOException e) {
			ServerConsole.printMessage(new Message(SloverseServer.SERVER, "Error transmitting object over the network. :("));
			e.printStackTrace();
		}
	}
	
	public synchronized static void sendDataToAll(Object o) {
		for (ConnectionThread t : connectedThreads) {
			sendData(t, o);
		}
	}
	
	public static synchronized void saveData(ConnectionThread thread) {
		//Save everything about the thread to their file.
	}
}
