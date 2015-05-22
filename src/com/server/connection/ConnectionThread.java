package com.server.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;

import com.server.chat.InputManager;
import com.server.core.SloverseServer;
import com.server.user.Player;
import com.server.util.SloverseLogger;

public class ConnectionThread extends Thread {
	
	private Player player;
	private Socket socket;
	private ObjectOutputStream userOutput;
	private ObjectInputStream serverFeed;
	
	public ConnectionThread(Socket s) {
		super();
		init(s);
	}
	
	private void init(Socket s) {
		socket = s;
		
		try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());) {
				userOutput = out;
				serverFeed = in;
		} catch (IOException e) {
			SloverseLogger.logErrorMessage(Level.SEVERE, "Error loading when PrintWriter and BufferedReader. Stacktrace: \n" + e.getStackTrace());
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public ObjectOutputStream getOutputStream() {
		return userOutput;
	}
	
	public ObjectInputStream getInputStream() {
		return serverFeed;
	}
	
	@Override
	public void run() {
		player = new Player("Derp", "Spongeboob");
//		player = Player.initPlayer();
		ConnectionManager.connectThread(this);
		Object incoming;
		
		try {
			while (SloverseServer.isRunning() && ((incoming = serverFeed.readObject()) != null)) {
				
				if (incoming instanceof String) {
					InputManager.filterInput(player, (String)incoming);
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			SloverseLogger.logErrorMessage(Level.SEVERE, "Error - Stacktrace: \n" + e.getStackTrace());
		}
		ConnectionManager.disconnectThread(this);
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ConnectionThread))
			return false;
		
		ConnectionThread t = (ConnectionThread) o;
		
		if (this.getPlayer().getName().equalsIgnoreCase(t.getPlayer().getName())) {
			return  true;
		}
		return false;
	}
}
