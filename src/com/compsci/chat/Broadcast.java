package com.compsci.chat;

public class Broadcast extends Message {

	private static final long serialVersionUID = -7994409670239292547L;
	private static final String TITLE = "BROADCAST";
	
	public Broadcast(String input) {
		super(null, input);
	}
	
	@Override
	public String getFormattedMessage() {
		String formattedMessage;
		
		formattedMessage = "[" + TITLE + "] " + message;
		return formattedMessage;
	}
}
