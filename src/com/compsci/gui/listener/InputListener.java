package com.compsci.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;

import com.compsci.chat.InputManager;
import com.compsci.chat.Message;
import com.compsci.core.SloverseServer;
import com.compsci.gui.FrameHandle;
import com.compsci.util.SloverseLogger;

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
				Message input = new Message(SloverseServer.SERVER, s);
				
				try {
					InputManager.filterInput(input);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				FrameHandle.inField.setText("");
			}
		}
	}
	
}
