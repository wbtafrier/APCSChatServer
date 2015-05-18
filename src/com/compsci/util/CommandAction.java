package com.compsci.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.compsci.core.ConnectionManager;
import com.compsci.display.FrameLayoutHandler;
import com.compsci.format.CommandHub;

public class CommandAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(FrameLayoutHandler.inField)) {
			
			String input = FrameLayoutHandler.inField.getText();
			
			if (!CommandHub.isCommand(input)) {
				ConnectionManager.sendBroadcast("SERVER", input);
			}
			FrameLayoutHandler.inField.setText("");
		}
	}
}
