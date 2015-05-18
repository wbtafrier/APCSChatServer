package com.compsci.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.compsci.format.GuiTextHandler;

public class SloverseLogger {
	
	private static Logger logger = Logger.getLogger("Sloverse Server Logger");
	
	public static String write(Level l, String label, String message) {
		GuiTextHandler.writeToGui(label, message);
		
		String formattedMessage = GuiTextHandler.formatText(label, message);
		logger.log(l, formattedMessage);
		return formattedMessage;
	}
}
