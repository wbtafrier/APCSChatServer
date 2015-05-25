package com.server.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import com.server.chat.InputManager;
import com.server.chat.Message;
import com.server.core.SloverseServer;
import com.server.gui.FrameHandle;
import com.server.util.SloverseLogger;

public class InputListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(FrameHandle.inField)) {
			
			String s = FrameHandle.inField.getText();
			if (!InputManager.isMessageAcceptable(s)) {
				SloverseLogger.logErrorMessage(Level.WARNING, "Message is not in the correct format! Will not print out message.");
				return;
			}
			else {
				Message input = new Message(SloverseServer.SYSTEM, s);
				InputManager.filterInput(input);
				FrameHandle.inField.setText("");
			}
		}
	}
	
}
