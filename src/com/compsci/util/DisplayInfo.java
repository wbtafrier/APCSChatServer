package com.compsci.util;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 * Provides basic information on the users computer and monitor.
 */
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
	
	public static boolean isFontInstalled(String fontName) {
		String[] fonts = ge.getAvailableFontFamilyNames();
		
		for (String s : fonts) {
			if (s.equals(fontName)) {
				return true;
			}
		}
		return false;
	}
	
	public static String getLongestFont() {
		String[] fonts = ge.getAvailableFontFamilyNames();
		int longestSize = 0;
		String longestFont = null;
		for (String f : fonts) {
			if (f.length() > longestSize) {
				longestSize = f.length();
				longestFont = f;
			}
		}
		return longestFont;
	}
}
