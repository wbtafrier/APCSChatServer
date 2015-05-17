package com.compsci.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.compsci.entity.Player;

public class ConnectionManager {

	private static List<PlayerConnectionThread> connectionThreads = new ArrayList<PlayerConnectionThread>();
	
	public static synchronized List<String> getUserNames() {
		List<String> userNames = new ArrayList<>();
		
		for (PlayerConnectionThread thread : connectionThreads) {
			userNames.add(thread.getPlayer().getName());
		}
		return userNames;
	}
	
	public static synchronized List<PlayerConnectionThread> getConnectedThreads() {
		return connectionThreads;
	}
	
	public static synchronized void sendMessage(String message) {
		
		for (PlayerConnectionThread thread : connectionThreads) {
			thread.getWriter().println(message);
		}
	}
	
	public static synchronized void addUser(PlayerConnectionThread userThread) throws IOException {
		connectionThreads.add(userThread.getThreadID(), userThread);
		
		userThread.getWriter().println("Please enter your username: ");
		
		Player newUser = new Player(userThread.getReader().readLine());
		userThread.setPlayer(newUser);
		
		for (PlayerConnectionThread thread : connectionThreads) {
			if (!thread.equals(userThread))
				thread.getWriter().println(newUser.getName() + " joined the server!");
		}
	}
	
	//TODO: When the client frame is done call this method from the window closing event
	public static synchronized void removeUser(PlayerConnectionThread userThread) {
		
		connectionThreads.remove(userThread.getThreadID());
		compressIDs(userThread.getThreadID());
		
		for (PlayerConnectionThread thread : connectionThreads) {
			thread.getWriter().println(userThread.getPlayer().getName() + " left the server.");
		}
	}
	
	private static synchronized void compressIDs(int removedID) {
		
		PlayerConnectionThread.setNextAvailableID(PlayerConnectionThread.getNextAvailableID() - 1);
		
		for (PlayerConnectionThread thread : connectionThreads) {
			if (thread.getThreadID() > removedID) {
				thread.setThreadID(thread.getThreadID() - 1);
			}
		}
	}
}
