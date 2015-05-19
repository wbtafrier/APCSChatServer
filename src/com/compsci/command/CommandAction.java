package com.compsci.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.compsci.display.FrameLayoutHandler;
import com.compsci.format.GuiTextHandler;

public class CommandAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(FrameLayoutHandler.inField)) {
			
			String input = FrameLayoutHandler.inField.getText();
			GuiTextHandler.writeToGui("SERVER", input);
			FrameLayoutHandler.inField.setText("");
		}
	}
}
