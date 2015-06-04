package com.compsci.gui.listener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JColorChooser;

import com.compsci.gui.FrameHandle;
import com.compsci.gui.GuiOperations;

public class CustomizeListener extends WindowAdapter implements ActionListener, ItemListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (FrameHandle.getCustomizeDialog() != null)
		{
			if (e.getSource().equals(FrameHandle.getCustomizeDialog().selectChatColor)) {
				Color c = JColorChooser.showDialog(FrameHandle.getCustomizeDialog(), "Sloverse Chat - Select Chat Color", FrameHandle.getOutputPane().getBackground());
				GuiOperations.changeChatColor(c);
			}
			else if (e.getSource().equals(FrameHandle.getCustomizeDialog().selectTextColor)) {
				Color c = JColorChooser.showDialog(FrameHandle.getCustomizeDialog(), "Sloverse Chat - Select Text Color", FrameHandle.getOutputPane().getForeground());
				GuiOperations.changeTextColor(c);
			}
			else if (e.getSource().equals(FrameHandle.getCustomizeDialog().doneButton)) {
				String fontName = FrameHandle.getCustomizeDialog().fontDropdown.getSelectedItem().toString();
				Font f = new Font(fontName, Font.PLAIN, 16);
				FrameHandle.getOutputPane().setFont(f);
				FrameHandle.getInputField().setFont(f);
				GuiOperations.closeCustomizeDialog();
			}
		}
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		GuiOperations.closeCustomizeDialog();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(FrameHandle.getCustomizeDialog().fontDropdown)) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				String fontName = FrameHandle.getCustomizeDialog().fontDropdown.getSelectedItem().toString();
				FrameHandle.getCustomizeDialog().fontDropdown.setFont(new Font(fontName, Font.PLAIN, 16));
				FrameHandle.getCustomizeDialog().pack();
				FrameHandle.getCustomizeDialog().setLocationRelativeTo(FrameHandle.getFrame());
			}
		}
	}

}
