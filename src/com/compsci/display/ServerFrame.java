package com.compsci.display;

import java.awt.Dimension;

import javax.swing.JFrame;

public class ServerFrame extends JFrame {

	private static final long serialVersionUID = 1707966321497413110L;

	public ServerFrame() {
		super("Sloverse Server");
		DisplayHandler.setupFrame(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(400, 500));
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.pack();
		this.setVisible(true);
	}
}
