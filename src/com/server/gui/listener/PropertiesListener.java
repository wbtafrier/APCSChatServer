package com.server.gui.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JColorChooser;

import com.server.gui.FrameHandle;
import com.server.gui.GuiOperations;

public class PropertiesListener extends WindowAdapter implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (FrameHandle.propertiesDialog != null)
		{
			if (e.getSource().equals(FrameHandle.propertiesDialog.colorDropdown)) {
				String selectedItem = (String) FrameHandle.propertiesDialog.colorDropdown.getSelectedItem();
				Color selectedColor = new Color(200, 200, 200);
				if (selectedItem.equalsIgnoreCase("red")) {
					selectedColor = Color.RED;
				}
				else if (selectedItem.equalsIgnoreCase("purple")) {
					selectedColor = Color.MAGENTA;
				}
				GuiOperations.changeColor(selectedColor);
			}
			else if (e.getSource().equals(FrameHandle.propertiesDialog.selectColor)) {
				Color c = JColorChooser.showDialog(FrameHandle.propertiesDialog, "Sloverse Server - Select Console Color", Color.red);
				GuiOperations.changeColor(c);
			}
			else if (e.getSource().equals(FrameHandle.propertiesDialog.doneButton)) {
				GuiOperations.closePropertiesDialog();
			}
		}
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		GuiOperations.closePropertiesDialog();
	}

}
