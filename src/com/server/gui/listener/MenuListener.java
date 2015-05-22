package com.server.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.server.core.SloverseServer;
import com.server.gui.FrameHandle;
import com.server.gui.GuiOperations;

public class MenuListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(FrameHandle.exitItem)) {
			SloverseServer.shutdownServer();
		}
		else if (e.getSource().equals(FrameHandle.propertiesItem)) {
			GuiOperations.openPropertiesDialog();
		}
	}
}
