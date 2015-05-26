package com.compsci.chat;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.compsci.gui.FrameHandle;

public class ServerConsole {

	public static void printMessage(Message m) {
		String oldText = FrameHandle.outPane.getText();
		FrameHandle.outPane.setText(oldText + addTimeStamp(m.getFormattedMessage()) + "\n");
	}
	
	private static String addTimeStamp(String message) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		String timeStamp = sdf.format(cal.getTime()) + " ";
		
		return timeStamp + message;
	}
}
