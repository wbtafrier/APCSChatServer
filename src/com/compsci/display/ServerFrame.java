package com.compsci.display;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.compsci.core.SloverseServer;


public class ServerFrame extends JFrame {

	private static final long serialVersionUID = 1707966321497413110L;
	private static final Dimension MINIMUM_SIZE = new Dimension((int)(DisplayInfo.getScreenSize().getWidth() / 4), (int)(DisplayInfo.getScreenSize().getHeight() / 2));
	private static final Dimension MAXIMUM_SIZE = new Dimension(DisplayInfo.getScreenSize().width, DisplayInfo.getScreenSize().height);

	public ServerFrame() {
		super("Sloverse Server");
		FrameLayoutHandler.setupFrame(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListeners();
		
		this.setMinimumSize(MINIMUM_SIZE);
		this.setMaximumSize(MAXIMUM_SIZE);
		this.setLocationRelativeTo(null);
		
		this.pack();
		this.setVisible(true);
	}
	
	private void addWindowListeners() {
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent we) {
				SloverseServer.closeServer();
			}
		});
	}
}
