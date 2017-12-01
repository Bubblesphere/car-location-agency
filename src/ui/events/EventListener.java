package ui.events;

public interface EventListener extends java.util.EventListener {
	@SuppressWarnings("rawtypes")
	public void handleEvent(Event evt);
}
