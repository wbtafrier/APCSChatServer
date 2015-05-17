package com.compsci.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.compsci.format.FrameLayoutHandler;
import com.compsci.format.GuiTextHandler;

public class CommandAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(FrameLayoutHandler.inField)) {
			String input = FrameLayoutHandler.inField.getText();
			GuiTextHandler.writeToGui("COMMAND", input);
			FrameLayoutHandler.inField.setText("");
			
		}
	}
	
}
