package com.compsci.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerConnectionHandler extends Thread {

	private Socket socket;
	
	public PlayerConnectionHandler(Socket incomingConnection) {
		super("MultiplayerThread");
		socket = incomingConnection;
	}
	
	@Override
	public void run() {
		//"Game loop" will go in here.
		//Constantly update until user disconnects
		
		try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			
			out.println("Welcome to the server!");
			
			while () {
				
			}
			
		} catch (IOException e) {
			
		}
	}
}
