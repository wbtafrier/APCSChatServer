package com.compsci.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.compsci.entity.Player;
import com.compsci.format.GuiTextHandler;
import com.compsci.util.SloverseLogger;

public class ConnectionManager {

	private static List<PlayerConnectionThread> connectionThreads = new ArrayList<PlayerConnectionThread>();
	private static boolean displayUserMessage = true;
	
	public static synchronized void setDisplayUserMessage(boolean b) {
		displayUserMessage = b;
	}
	
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
	
	public static synchronized void sendMessage(PlayerConnectionThread thread, String message) {
		
		if (displayUserMessage)
			SloverseLogger.write(Level.INFO, thread.getPlayer().getName(), message);
		
		for (PlayerConnectionThread t : connectionThreads) {
			t.getWriter().println(GuiTextHandler.formatText(thread.getPlayer().getName(), message));
		}
	}
	
	public static synchronized void sendBroadcast(String label, String message) {
		SloverseLogger.write(Level.INFO, label, message);
		
		for (PlayerConnectionThread t : connectionThreads) {
			t.getWriter().println(GuiTextHandler.formatText(label, message));
		}
	}
	
	public static synchronized void addUser(PlayerConnectionThread userThread) throws IOException {
		connectionThreads.add(userThread.getThreadID(), userThread);
		
		userThread.getWriter().println("Please enter your username: ");
		
		Player newUser = new Player(userThread.getReader().readLine());
		userThread.setPlayer(newUser);
		
		String welcomeString = SloverseLogger.write(Level.INFO, "SERVER", newUser.getName() + " joined the server!");
		
		for (PlayerConnectionThread thread : connectionThreads) {
			if (!thread.equals(userThread))
				thread.getWriter().println(welcomeString);
		}
	}
	
	//TODO: When the client frame is done call this method from the window closing event
	public static synchronized void removeUser(PlayerConnectionThread userThread) {
		
		connectionThreads.remove(userThread.getThreadID());
		compressIDs(userThread.getThreadID());
		
		String exitString = SloverseLogger.write(Level.INFO, "SERVER", userThread.getPlayer().getName() + " left the server!");
		
		for (PlayerConnectionThread thread : connectionThreads) {
			thread.getWriter().println(exitString);
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
