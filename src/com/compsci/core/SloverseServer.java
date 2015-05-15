package com.compsci.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.compsci.entity.Player;

public class SloverseServer {

	private List<Player> connectedUsers;
	
	public SloverseServer() {
		
		connectedUsers = new ArrayList<>();
	}
	
	public static void main(String[] args) {
		
		try (ServerSocket s = new ServerSocket(608);
			Socket clientSocket = s.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
			
			while (true) {
				Runnable r = new PlayerConnectionHandler(clientSocket);
				Thread t = new Thread(r);
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
