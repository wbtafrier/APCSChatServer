package com.compsci.gui.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JColorChooser;

import com.compsci.gui.FrameHandle;
import com.compsci.gui.GuiOperations;

public class CustomizeListener extends WindowAdapter implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (FrameHandle.getCustomizeDialog() != null)
		{
			if (e.getSource().equals(FrameHandle.getCustomizeDialog().selectColor)) {
				Color c = JColorChooser.showDialog(FrameHandle.getCustomizeDialog(), "Sloverse Server - Select Console Color", FrameHandle.getOutputPane().getBackground());
				GuiOperations.changeColor(c);
			}
			else if (e.getSource().equals(FrameHandle.getCustomizeDialog().doneButton)) {
				GuiOperations.closeCustomizeDialog();
			}
		}
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		GuiOperations.closeCustomizeDialog();
	}

}
