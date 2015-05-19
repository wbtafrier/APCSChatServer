package com.compsci.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import com.compsci.display.ServerFrame;
import com.compsci.format.GuiTextHandler;

public final class SloverseServer {

	private static boolean listening = true;
	private static int portNumber = 609;
	
	public static void main(String[] args) throws UnknownHostException {
		
		new ServerFrame();
		
		GuiTextHandler.writeToGui("SERVER", "Welcome to the server control panel!");
		
		try (ServerSocket socket = new ServerSocket(portNumber);) {
			while (listening) {
				new PlayerConnectionThread(socket.accept()).start();
			}
		} catch (IOException e) {
			System.err.println("Could not listen to port: " + portNumber);
			System.exit(-1);
		}
	}
	
	public static boolean isRunning() {
		return listening;
	}
	
	public static void closeServer() {
		ConnectionManager.disconnectUsers();
		listening = false;
	}
}
