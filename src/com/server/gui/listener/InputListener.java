package com.server.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.server.chat.InputManager;
import com.server.chat.Message;
import com.server.core.SloverseServer;
import com.server.gui.FrameHandle;

public class InputListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(FrameHandle.getInputField())) {
			
			Message input = new Message(SloverseServer.SYSTEM, FrameHandle.getInputField().getText());
			InputManager.filterInput(input);
			FrameHandle.getInputField().setText("");
		}
	}
	
}
