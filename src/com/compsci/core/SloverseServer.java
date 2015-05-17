package com.compsci.core;

import java.io.IOException;
import java.net.ServerSocket;

import com.compsci.display.ServerFrame;

public final class SloverseServer {

	private static boolean listening = true;
	private static int portNumber = 609;
	
	public static void main(String[] args) {
		
		new ServerFrame();
		
//		System.setOut(GuiStream.printStream);
//		System.setErr(GuiStream.printStream);
		
		System.out.println("Welcome to the server control panel!");
		
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
