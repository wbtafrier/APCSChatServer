package com.compsci.core;

import java.io.IOException;
import java.net.ServerSocket;

public final class SloverseServer {

	private static boolean listening = true;
	private static int portNumber = 608;
	
	public static void main(String[] args) {
		
		try (ServerSocket socket = new ServerSocket(portNumber);) {
			
			while (listening) {
				new PlayerConnectionThread(socket.accept()).start();
			}
		} catch (IOException e) {
			System.err.println("Could not listen to port: " + portNumber);
			System.exit(-1);
		}
	}
}
