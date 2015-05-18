package com.compsci.format;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.compsci.display.FrameLayoutHandler;

public class GuiTextHandler {

	public static String formatText(String username, String input) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		String timeStamp = sdf.format(cal.getTime()) + " ";
		String label = "[" + username + "] ";
		
		return timeStamp + label + input;
	}
	
	public static void writeToGui(String username, String input) {
		if (input == null) {
			return;
		}
		String oldText = FrameLayoutHandler.outPane.getText();
		String trimmed = input.trim();
		if (!verifyMessage(trimmed)) {
			return;
		}
		if (isCommand(trimmed)) {
			
		}
		
		String formatted = formatText(username, trimmed);
		FrameLayoutHandler.outPane.setText(oldText + formatted + "\n\n");
	}
	
	public static String formatText(String input) {
		return formatText("SERVER", input);
	}
	
	public static boolean isCommand(String input) {
		return (input != null && input.length() > 1 && input.charAt(0) == '/');
	}
	
	public static boolean verifyMessage(String input) {
		if (input.contains("\n") || input.contains("\r") || input.isEmpty()) {
			return false;
		}
		return true;
	}
}
