package com.compsci.chat;

import java.io.Serializable;
import java.util.logging.Level;

import com.compsci.user.User;
import com.compsci.util.SloverseLogger;

public class Message implements Serializable {

	private static final long serialVersionUID = 3900037731396757918L;
	
//	private static final int MAXIMUM_LENGTH = 300;
	private String message;
	private EnumMessageType type;
	private User sender, receiver;
	
	public Message(User s, String input) {
		if (!checkInput(input)) return;
		initMessage(s, input);
		type = EnumMessageType.PUBLIC;
		receiver = null;
	}
	
	public Message(User s, User r, String input) {
		if (!checkInput(input)) return;
		initMessage(s, r, input);
		type = EnumMessageType.PRIVATE;
	}
	
	private void initMessage(User s, String input) {
		sender = s;
		message = input;
	}
	
	private void initMessage(User s, User r, String input) {
		receiver = r;
		initMessage(s, input);
	}
	
	private boolean checkInput(String input) {
		if (!InputManager.isMessageAcceptable(input)) {
			SloverseLogger.logErrorMessage(Level.WARNING, "Message is not in the correct format!");
			return false;
		}
		return true;
	}
	
	public String getFormattedMessage() {
		String formattedMessage;
		System.out.println(type);
		
		if (type.equals(EnumMessageType.PUBLIC)) {
			formattedMessage = "[" + sender.getName() + "] " + message;
		}
		else {
			formattedMessage = "[" + sender.getName() + " --> " + receiver.getName() + "] " + message;
		}
		return formattedMessage;
	}
	
	public User getSender() {
		return sender;
	}
	
	public User getReceiver() {
		return receiver;
	}
	
	public EnumMessageType getType() {
		return type;
	}
	
	/**
	 * Do not get the string for outputting! Only use when comparing the actual message. Use the toString method when printing out messages.
	 * @return : the unformatted message.
	 */
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return getFormattedMessage();
	}
}
