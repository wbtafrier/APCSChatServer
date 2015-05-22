package com.server.gui.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.server.gui.FrameHandle;

public class GUIListener extends WindowAdapter {

	@Override
	public void windowOpened(WindowEvent we) {
		FrameHandle.inField.requestFocusInWindow();
		FrameHandle.getFrame().removeWindowListener(this);
	}

}
