package com.compsci.display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.compsci.format.GuiTextHandler;

public class EnterAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(DisplayHandler.inField)) {
			String input = DisplayHandler.inField.getText();
			GuiTextHandler.writeToGui("SERVER", input);
			DisplayHandler.inField.setText("");
			
		}
	}
	
}
