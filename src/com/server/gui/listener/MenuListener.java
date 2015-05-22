package com.server.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.server.gui.FrameHandle;

public class MenuListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(FrameHandle.exitItem)) {
//			ConnectionManager.disconnectUsers();
		}
	}
}
