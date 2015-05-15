package com.compsci.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import com.compsci.entity.Player;

public class SloverseServer {

	private static boolean listening;
	private static int portNumber = 608;
	
	private static List<Player> connectedUsers = new ArrayList<>();
	
	public static void main(String[] args) {
		
		try (ServerSocket socket = new ServerSocket(portNumber);) {
			
			while (listening) {
				new PlayerConnectionHandler(socket.accept()).start();
			}
		} catch (IOException e) {
			System.err.println("Could not listen to port: " + portNumber);
			System.exit(-1);
		}
	}
}
