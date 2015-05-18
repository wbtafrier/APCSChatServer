package com.compsci.format;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.compsci.core.ConnectionManager;

public class GuiTextHandler {

	public static String formatText(String label, String input) {
		String formattedLabel = "[" + label + "] ";
		return formattedLabel + input;
	}
	
	public static String formatTextWithTime(String label, String input) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		String timeStamp = sdf.format(cal.getTime()) + " ";
		String formattedLabel = "[" + label + "] ";
		
		return timeStamp + formattedLabel + input;
	}
	
	public static void writeToGui(String label, String input) {
		if (input == null) {
			return;
		}
		
		String oldText = FrameLayoutHandler.outPane.getText();
		String trimmed = input.trim();
		
		if (!verifyMessage(trimmed)) {
			return;
		}
		
		if (CommandHub.isCommand(trimmed)) {
			CommandHub.computeCommand(trimmed);
		}
		
		String formatted = formatTextWithTime(label, input);
		FrameLayoutHandler.outPane.setText(oldText + formatted + "\n");
	}
	
	public static boolean verifyMessage(String message) {
		if (message.contains("\n") || message.contains("\r") || message.isEmpty()) {
			return false;
		}
		return true;
	}
}
