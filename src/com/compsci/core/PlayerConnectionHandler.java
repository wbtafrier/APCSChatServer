package com.compsci.core;

import java.net.Socket;

public class PlayerConnectionHandler implements Runnable {

	private Socket playerConnection;
	
	public PlayerConnectionHandler(Socket incomingConnection) {
		System.out.println("New user connected to the server!");
		playerConnection = incomingConnection;
	}
	
	@Override
	public void run() {
		//"Game loop" will go in here.
		//Constantly update until user disconnects
	}
}
