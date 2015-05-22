package com.server.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.server.gui.FrameHandle;

public class InputListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(FrameHandle.inField)) {
			
//			String input = FrameHandle.inField.getText();
//			GuiTextHandler.writeToGui("SERVER", input);
			FrameHandle.inField.setText("");
		}
	}
	
}
