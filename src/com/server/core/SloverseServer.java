package com.server.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;

import com.server.connection.ConnectionManager;
import com.server.connection.ConnectionThread;
import com.server.gui.ServerFrame;
import com.server.user.ServerUser;
import com.server.user.User;
import com.server.util.SloverseLogger;

public final class SloverseServer {

	public static final User SYSTEM = new ServerUser();
	private static boolean isListening = true;
	private static final int PORT_NUMBER = 609;
	
	public static void main(String[] args) {
		new ServerFrame("Sloverse Server");
		
		try (ServerSocket socket = new ServerSocket(PORT_NUMBER);) {
			while (isListening) {
				new ConnectionThread(socket.accept()).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			SloverseLogger.logErrorMessage(Level.SEVERE, "Could not listen to port: " + PORT_NUMBER);
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
		ConnectionManager.disconnectAllThreads();
		setListening(false);
	}
}
