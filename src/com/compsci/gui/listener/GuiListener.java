package com.compsci.gui.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.compsci.gui.FrameHandle;

public class GuiListener extends WindowAdapter {

	@Override
	public void windowOpened(WindowEvent we) {
		FrameHandle.getInputField().requestFocusInWindow();
		FrameHandle.getFrame().removeWindowListener(this);
	}
}
