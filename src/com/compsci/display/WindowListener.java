package com.compsci.display;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowListener extends WindowAdapter {

	@Override
	public void windowOpened(WindowEvent we) {
		FrameLayoutHandler.inField.requestFocusInWindow();
		FrameLayoutHandler.getFrame().removeWindowListener(this);
	}

}
