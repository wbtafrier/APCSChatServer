package com.compsci.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;

import com.compsci.chat.InputManager;
import com.compsci.chat.Message;
import com.compsci.chat.ServerConsole;
import com.compsci.core.SloverseServer;
import com.compsci.user.Player;
import com.compsci.util.SloverseLogger;

public class ConnectionThread extends Thread {
	
	private Player player;
	private Socket socket;
	private ObjectOutputStream outStream;
	private ObjectInputStream inStream;
	
	public ConnectionThread(Socket s) {
		super();
		init(s);
	}
	
	private void init(Socket s) {
		socket = s;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public ObjectOutputStream getOutputStream() {
		return outStream;
	}
	
	public ObjectInputStream getInputStream() {
		return inStream;
	}
	
	@Override
	public void run() {
		player = Player.initPlayer();
		ConnectionManager.connectThread(this);
		Object incoming;
		
		try {
			outStream = new ObjectOutputStream(socket.getOutputStream());
			inStream = new ObjectInputStream(socket.getInputStream());
			
			while (SloverseServer.isRunning() && !socket.isClosed()) {
				incoming = inStream.readObject();
				
				if (incoming != null) {
					if (incoming instanceof Message) {
						InputManager.filterInput((Message) incoming);
					}
					else {
						System.out.println("DERP");
					}
				}
			}
		} catch (SocketException e) {
			SloverseLogger.logErrorMessage(Level.WARNING, "Connection Lost. Internal exception: " + e.getClass());
			ServerConsole.printMessage(new Message(SloverseServer.SERVER, this.getPlayer().getName() + " : lost connection."));
			ConnectionManager.disconnectThread(this);
			//Show message to bring the user back to home screen.
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
