package com.compsci.display;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class PaneFocusListener implements FocusListener {

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().equals(FrameLayoutHandler.outPane)) {
			FrameLayoutHandler.outPane.setEditable(false);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(FrameLayoutHandler.outPane)) {
			FrameLayoutHandler.outPane.setEditable(true);
		}
	}

}
