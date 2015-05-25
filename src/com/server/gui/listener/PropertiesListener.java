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
		if (FrameHandle.getPropertiesDialog() != null)
		{
			if (e.getSource().equals(FrameHandle.getPropertiesDialog().selectColor)) {
				Color c = JColorChooser.showDialog(FrameHandle.getPropertiesDialog(), "Sloverse Server - Select Console Color", FrameHandle.getOutputPane().getBackground());
				GuiOperations.changeColor(c);
			}
			else if (e.getSource().equals(FrameHandle.getPropertiesDialog().doneButton)) {
				GuiOperations.closePropertiesDialog();
			}
		}
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		GuiOperations.closePropertiesDialog();
	}

}
