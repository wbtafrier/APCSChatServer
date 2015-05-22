package com.server.gui.listener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import com.server.gui.FrameHandle;

/**
 * Focus Listener for the main TextPane in the Sloverse Frame.
 */
public class PaneFocusListener implements FocusListener {

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().equals(FrameHandle.outPane)) {
			FrameHandle.outPane.setEditable(false);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(FrameHandle.outPane)) {
			FrameHandle.outPane.setEditable(true);
		}
	}
}
