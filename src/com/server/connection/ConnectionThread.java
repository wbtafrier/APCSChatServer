package com.server.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;

import com.server.chat.InputManager;
import com.server.chat.Message;
import com.server.core.SloverseServer;
import com.server.user.Player;
import com.server.util.SloverseLogger;

public class ConnectionThread extends Thread {
	
	private Player player;
	private Socket socket;
	private PrintWriter userOutput;
	private BufferedReader serverFeed;
	
	public ConnectionThread(Socket s) {
		super();
		init(s);
	}
	
	private void init(Socket s) {
		socket = s;
		
		try (PrintWriter out = new PrintWriter(socket.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
				userOutput = out;
				serverFeed = in;
		} catch (IOException e) {
			SloverseLogger.logErrorMessage(Level.SEVERE, "Error loading PrintWriter and BufferedReader. Stacktrace: \n" + e.getStackTrace());
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public PrintWriter getOutputStream() {
		return userOutput;
	}
	
	public BufferedReader getInputStream() {
		return serverFeed;
	}
	
	@Override
	public void run() {
		player = Player.initPlayer();
		ConnectionManager.connectThread(this);
		Object incoming;
		
		try {
			while (SloverseServer.isRunning() && (serverFeed != null) && ((incoming = serverFeed.readLine()) != null)) {
				
				if (incoming instanceof String) {
					InputManager.filterInput(new Message(player, (String)incoming));
				}
			}
		} catch (IOException e) {
			SloverseLogger.logErrorMessage(Level.SEVERE, "Error - Stacktrace: \n\n" + e.getStackTrace());
		}
		ConnectionManager.disconnectThread(this);
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ConnectionThread))
			return false;
		
		ConnectionThread t = (ConnectionThread) o;
		
		System.out.println("HELLO: " + this.getPlayer().getName());
		System.out.println("OMG: " + t.getPlayer().getName());
		
		if (this.getPlayer().getName().equalsIgnoreCase(t.getPlayer().getName())) {
			return  true;
		}
		return false;
	}
}
