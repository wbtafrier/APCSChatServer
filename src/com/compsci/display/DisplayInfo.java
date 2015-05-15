package com.compsci.display;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public abstract class DisplayInfo {

	private static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private static GraphicsDevice gd = ge.getDefaultScreenDevice();
	private static DisplayMode display = gd.getDisplayMode();
	private static final Dimension screenSize = new Dimension(display.getWidth(), display.getHeight());
	
	public static GraphicsEnvironment getGraphicsEnvironment() {
		return ge;
	}
	
	public static GraphicsDevice getGraphicsDevice() {
		return gd;
	}
	
	public static DisplayMode getDisplay() {
		return display;
	}
	
	public static Dimension getScreenSize() {
		return screenSize;
	}
}
