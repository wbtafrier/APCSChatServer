package com.compsci.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerConnectionThread extends Thread {

	private static int nextAvailableID;
	private int threadID;
	
	private Socket socket;
	private PrintWriter output;
	private BufferedReader input;
	private String userInput, serverOutput;
	
	public PlayerConnectionThread(Socket incomingConnection) {
		super("MultiplayerThread");
		socket = incomingConnection;
		threadID = nextAvailableID++;
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
	
	@Override
	public void run() {
		//"Game loop" will go in here.
		//Constantly update until user disconnects
		
		try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			
			output = out;
			input = in;
			ConnectionManager.addUser(this);
			
			out.println("Welcome to the server! There are " + (ConnectionManager.getPlayerList().size() - 1) + " other users online.");
			
			while ((userInput = in.readLine()) != null) {
				serverOutput = "Echo: " + userInput;
				out.println(serverOutput);
				
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
}
