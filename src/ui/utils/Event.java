package ui.utils;

import java.util.EventObject;

public class Event extends EventObject {
	private final String command;
	
	public Event (Object source, String command) {
        super(source);
        this.command = command;
    }
	
	public String getCommand() {
		return this.command;
	}
}
