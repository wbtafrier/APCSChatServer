package com.compsci.gui;

import java.awt.Color;
import java.util.Random;

public class GuiOperations {

	private static Random rand = new Random();
	
	public static void changeChatColor(Color newColor) {
		if (!FrameHandle.getOutputPane().getBackground().equals(newColor) && newColor != null) {
			FrameHandle.getOutputPane().setBackground(newColor);
		}
	}
	
	public static void changeTextColor(Color newColor) {
		if (!FrameHandle.getOutputPane().getForeground().equals(newColor) && newColor != null) {
			FrameHandle.getOutputPane().setForeground(newColor);
		}
	}
	
	public static void randomizeColor() {
		FrameHandle.getOutputPane().setBackground(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
		FrameHandle.getOutputPane().setForeground(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
	}
	
	public static void openCustomizeDialog() {
		if (FrameHandle.getCustomizeDialog() == null) {
			FrameHandle.setCustomizeDialog(new CustomizeDialog());
		}
		else {
			FrameHandle.getCustomizeDialog().setVisible(true);
		}
	}
	
	public static void closeCustomizeDialog() {
		if (FrameHandle.getCustomizeDialog() != null) {
			FrameHandle.getCustomizeDialog().setVisible(false);
			FrameHandle.getCustomizeDialog().dispose();
		}
	}
}
