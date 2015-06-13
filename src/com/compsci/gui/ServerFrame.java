package com.compsci.gui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.compsci.core.SloverseServer;
import com.compsci.util.DisplayInfo;

/**
 * The JFrame for the Server control panel.
 */
public class ServerFrame extends JFrame {

	private static final long serialVersionUID = 1707966321497413110L;
	private static final Dimension PREFERRED_SIZE = new Dimension((int)(DisplayInfo.getScreenSize().getWidth() / 3), (int)(DisplayInfo.getScreenSize().getHeight() / 1.5));
	private static final Dimension MAXIMUM_SIZE = new Dimension(DisplayInfo.getScreenSize().width, DisplayInfo.getScreenSize().height);

	public ServerFrame(String name) {
		super(name);
		FrameHandle.setupFrame(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListeners();
		
		this.setSize(PREFERRED_SIZE);
		this.setPreferredSize(PREFERRED_SIZE);
		this.setMaximumSize(MAXIMUM_SIZE);
		this.setLocationRelativeTo(null);
		
		this.pack();
		this.setVisible(true);
	}
	
	private void addWindowListeners() {
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent we) {
				SloverseServer.shutdownServer();
			}
		});
	}
}
