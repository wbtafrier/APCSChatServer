package com.compsci.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
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
	private static final int PORT_NUMBER = 309;
	private static final int TIME_OUT = 5000;
	private static String PUBLIC_IP;
	
	public static void main(String[] args) {
		new ServerFrame("Sloverse Server");
		PUBLIC_IP = queryPublicIP();
		
		try (ServerSocket socket = new ServerSocket(PORT_NUMBER);) {
			
			testConnection();
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
	
	private static void testConnection() {
		
		try (Socket testConnect = new Socket();) {
			testConnect.connect(new InetSocketAddress(InetAddress.getByName(PUBLIC_IP), PORT_NUMBER), TIME_OUT);
		} catch (IOException e) {
			String s = "Connection Timed out. The server is not linked to an external IP. No one from outside localhost will be able to connect.";
			SloverseLogger.logErrorMessage(Level.WARNING, s);
			ServerConsole.printMessage(new Message(SERVER, s));
			return;
		}
		ServerConsole.printMessage(new Message(SERVER, "The server is open!"));
		ServerConsole.printMessage(new Message(SERVER, "Using public ip: " + PUBLIC_IP + ":" + PORT_NUMBER));
		ServerConsole.printMessage(new Message(SERVER, "Welcome to the Sloverse Server console!"));
	}
	
	private static String queryPublicIP() {
		String ip = "localhost";
		try {
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			ServerConsole.printMessage(new Message(SERVER, "Trying to establish connection..."));
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			ip = in.readLine();
			return ip;
		} catch (IOException e) {
			SloverseLogger.logErrorMessage(Level.SEVERE, "Error trying to connect to Amazon's ip checker. Returning localhost.");
			e.printStackTrace();
		}
		return ip;
	}
	
	public static String getPublicIP() {
		return PUBLIC_IP;
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
		System.exit(0);
	}
}
