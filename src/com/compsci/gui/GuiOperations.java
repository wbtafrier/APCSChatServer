package com.compsci.gui;

import java.awt.Color;

public class GuiOperations {

	public static void changeColor(Color newColor) {
		if (!FrameHandle.outPane.getBackground().equals(newColor) && newColor != null) {
			FrameHandle.outPane.setBackground(newColor);
		}
	}
	
	public static void openPropertiesDialog() {
		if (FrameHandle.propertiesDialog == null) {
			FrameHandle.propertiesDialog = new PropertiesDialog();
		}
		else {
			FrameHandle.propertiesDialog.setVisible(true);
		}
	}
	
	public static void closePropertiesDialog() {
		if (FrameHandle.propertiesDialog != null) {
			FrameHandle.propertiesDialog.setVisible(false);
			FrameHandle.propertiesDialog.dispose();
		}
	}
}
