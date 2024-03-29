package ui.events;

import java.util.EventObject;

public class Event<T extends Enum<T> & IEventName> extends EventObject {
	private static final long serialVersionUID = 1L;
	private final Enum<T> eventName;

  public Event(Object source, Enum<T> eventName) {
    super(source);
    this.eventName = eventName;
  }

  public Enum<T> getEventName() {
    return this.eventName;
  }
}
