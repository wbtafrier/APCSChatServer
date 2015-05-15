package com.compsci.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.compsci.entity.Player;

public class PlayerConnectionThread extends Thread {

	private static int nextAvailableID;
	private int threadID;
	
	private Socket socket;
	private PrintWriter output;
	private BufferedReader input;
	private String userInput, serverOutput;
	private Player user;
	
	public PlayerConnectionThread(Socket incomingConnection) {
		super("MultiplayerThread");
		socket = incomingConnection;
		threadID = nextAvailableID++;
	}
	
	@Override
	public void run() {
		//"Game loop" will go in here.
		//Constantly update until user disconnects
		
		try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			
			output = out;
			input = in;
			ConnectionManager.addUser(this);
			
			welcomeMessage();
			
			while ((userInput = in.readLine()) != null) {
				serverOutput = user.getName() + ": " + userInput;
				ConnectionManager.sendMessage(serverOutput);
				
				if (userInput.equalsIgnoreCase("Bye")) {
					break;
				}
			}
			ConnectionManager.removeUser(this);
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private synchronized void welcomeMessage() {
		int otherUsers = (ConnectionManager.getConnectedThreads().size() - 1);
		String welcome;
		
		if (otherUsers == 1) {
			welcome = "Welcome to the server! There is " + otherUsers + " other user online.";
		}
		else {
			welcome = "Welcome to the server! There are " + otherUsers + " other users online.";
		}
		output.println(welcome);
	}
	
	public synchronized Player getPlayer() {
		return user;
	}
	
	public synchronized void setPlayer(Player p) {
		user = p;
	}
	
	public synchronized PrintWriter getWriter() {
		return output;
	}
	
	public synchronized BufferedReader getReader() {
		return input;
	}
	
	public synchronized void setThreadID(int id) {
		threadID = id;
	}
	
	public synchronized int getThreadID() {
		return threadID;
	}
	
	public static synchronized void setNextAvailableID(int total) {
		nextAvailableID = total;
	}
	
	public static synchronized int getNextAvailableID() {
		return nextAvailableID;
	}
}
