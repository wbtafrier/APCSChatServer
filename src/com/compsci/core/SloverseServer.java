package com.compsci.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;

import com.compsci.chat.Message;
import com.compsci.chat.ServerConsole;
import com.compsci.connection.ConnectionManager;
import com.compsci.connection.ConnectionThread;
import com.compsci.gui.ServerFrame;
import com.compsci.user.ServerUser;
import com.compsci.user.User;
import com.compsci.util.SloverseLogger;

public final class SloverseServer {

	public static final User SERVER = new ServerUser();
	private static boolean isListening = true;
	private static final int PORT_NUMBER = 609;
	
	public static void main(String[] args) {
		new ServerFrame("Sloverse Server");
		ServerConsole.printMessage(new Message(SERVER, "Welcome to the Sloverse Server console!"));
		
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
		try {
			ConnectionManager.disconnectAllThreads();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setListening(false);
	}
}
