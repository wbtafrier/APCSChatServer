package com.compsci.display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.compsci.core.ConnectionManager;

public class MenuListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(FrameLayoutHandler.exitItem)) {
			ConnectionManager.disconnectUsers();
		}
	}

}
