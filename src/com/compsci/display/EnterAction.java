package com.compsci.display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EnterAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(DisplayHandler.inField)) {
			
			if (DisplayHandler.inField.getText().equalsIgnoreCase("stop")) {
				DisplayHandler.getFrame().dispose();
				System.exit(0);
			}
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(cal.getTime()) + " > ";
			String newLine = "\n";
			if (DisplayHandler.outPane.getText().length() <= 0) {
				newLine = "";
			}
			
			DisplayHandler.outPane.setText(DisplayHandler.outPane.getText() + 
					newLine + time + DisplayHandler.inField.getText() + "\n");
			DisplayHandler.inField.setText("");
			
		}
	}
	
}
