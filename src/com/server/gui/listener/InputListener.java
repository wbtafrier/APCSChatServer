package com.server.gui.listener;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;

import com.server.chat.InputManager;
import com.server.chat.Message;
import com.server.core.SloverseServer;
import com.server.gui.FrameHandle;
import com.server.util.SloverseLogger;

public class InputListener extends KeyAdapter implements ActionListener {

	private ArrayList<String> inputHistory = new ArrayList<String>();
	private String formerText = "";
	private int currentHistoryLocation = -1;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(FrameHandle.getInputField())) {
			String s = FrameHandle.getInputField().getText();
			if (!InputManager.isMessageAcceptable(s)) {
				SloverseLogger.logErrorMessage(Level.WARNING, "Message is not in the correct format! Will not print out message.");
				return;
			}
			else {
				Message input = new Message(SloverseServer.SYSTEM, s);
				InputManager.filterInput(input);
				inputHistory.add(0, s);
				currentHistoryLocation = -1;
				FrameHandle.getInputField().setText("");
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource().equals(FrameHandle.getInputField())) {
			if (currentHistoryLocation == -2) {
				currentHistoryLocation = -1;
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource().equals(FrameHandle.getInputField())) {
			if (e.getKeyCode() == KeyEvent.VK_UP || (!Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_NUM_LOCK) && e.getKeyCode() == KeyEvent.VK_NUMPAD8)) {
				
				if (currentHistoryLocation == -1) {
					formerText = FrameHandle.getInputField().getText();
				}
				
				if (inputHistory.size() <= 0 || (currentHistoryLocation + 1) >= inputHistory.size()) {
					return;
				}
				
				if (currentHistoryLocation == -2 && (formerText == null || formerText.isEmpty())) {
					currentHistoryLocation += 2;
				}
				else {
					currentHistoryLocation++;
				}
				
				if (currentHistoryLocation == -1) {
					FrameHandle.getInputField().setText(formerText);
				}
				else {
					FrameHandle.getInputField().setText(inputHistory.get(currentHistoryLocation));
				}
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN || (!Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_NUM_LOCK) && e.getKeyCode() == KeyEvent.VK_NUMPAD2)) {
				if (currentHistoryLocation == -1) {
					formerText = FrameHandle.getInputField().getText();
				}
				
				if (inputHistory.size() <= 0 || (currentHistoryLocation - 1) < -2) {
					return;
				}
				
				currentHistoryLocation--;
				if (currentHistoryLocation == -2) {
					FrameHandle.getInputField().setText("");
				}
				else if (currentHistoryLocation == -1) {
					FrameHandle.getInputField().setText(formerText);
				}
				else {
					FrameHandle.getInputField().setText(inputHistory.get(currentHistoryLocation));
				}
			}
		}
	}
}
