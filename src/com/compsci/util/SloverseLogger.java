package com.compsci.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Takes information and logs it to a file.
 * It can log error reports, play messages etc.
 */
public class SloverseLogger {

	private static Logger sloverseLogger = Logger.getLogger("Sloverse Logger");
	
	public static void logErrorMessage(Level l, String message) {
		sloverseLogger.log(l, message);
//		System.err.println("SLOVERSE LOGGER: " + message);
		//Send message to file system to be logged.
		
		
		//Send to Server Control Panel to be output
	}
	
	public static void logSuspiciousMessage(Level l, String message) {
		sloverseLogger.log(l, "Suspicious activity: " + message);
		System.err.println("SLOVERSE LOGGER: Suspicious activity: " + message);
		//Send message to file system to be logged.
		
		
		//Send to Server Control Panel to be output
	}
}
