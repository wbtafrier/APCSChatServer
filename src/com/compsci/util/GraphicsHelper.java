package com.compsci.util;

import java.awt.GraphicsEnvironment;

public class GraphicsHelper {
	
	private static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	
	
	public static boolean isFontInstalled(String fontName) {
		String[] fonts = ge.getAvailableFontFamilyNames();
		
		for (String s : fonts) {
			if (s.equals(fontName)) {
				return true;
			}
		}
		return false;
	}
}
