package com.server.core;

import java.io.IOException;
import java.net.ServerSocket;

import com.server.connection.ConnectionThread;
import com.server.gui.ServerFrame;

public final class SloverseServer {

	private static boolean isListening = true;
	private static final int PORT_NUMBER = 609;
	
	public static void main(String[] args) {
		new ServerFrame("Sloverse Server");
		
		try (ServerSocket socket = new ServerSocket(PORT_NUMBER);) {
			while (isListening) {
				new ConnectionThread(socket.accept()).start();
			}
		} catch (IOException e) {
//			SloverseLogger.log("Could not listen to port: " + PORT_NUMBER);
			System.exit(-1);
		}
	}
	
	public static boolean isRunning() {
		return isListening;
	}

	private static void setListening(boolean b) {
		isListening = b;
	}
	
	public static void shutdownServer() {
		
		setListening(false);
	}
}
