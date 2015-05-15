package com.compsci.format;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.compsci.display.DisplayHandler;

public class GuiTextHandler {

	public static String formatText(String username, String input) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		String timeStamp = sdf.format(cal.getTime()) + " ";
		String label = "[" + username + "] ";
		
		if (input == null) {
			input = "";
		}
		
		return timeStamp + label + input;
	}
	
	public static void writeToGui(String username, String input) {
		String oldText = DisplayHandler.outPane.getText();
		String formatted = formatText(username, input);
		DisplayHandler.outPane.setText(oldText + formatted + "\n\n");
	}
	
	public static String formatText(String input) {
		return formatText("SERVER", input);
	}
	
	public static boolean isCommand(String input) {
		return (input != null && input.length() > 1 && input.charAt(0) == '/');
	}
	
}
