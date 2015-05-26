package com.compsci.gui.listener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import com.compsci.gui.FrameHandle;

/**
 * Focus Listener for the main TextPane in the Sloverse Frame.
 */
public class PaneFocusListener implements FocusListener {

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().equals(FrameHandle.getOutputPane())) {
			FrameHandle.getOutputPane().setEditable(false);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(FrameHandle.getOutputPane())) {
			FrameHandle.getOutputPane().setEditable(true);
		}
	}
}
