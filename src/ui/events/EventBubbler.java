package ui.events;

import javax.swing.event.EventListenerList;

public class EventBubbler {
  private EventListenerList listenerList;

  public EventBubbler(EventListenerList listenerList) {
    this.listenerList = listenerList;
  }

  public void addListener(EventListener listener) {
    listenerList.add(EventListener.class, listener);
  }

  public void removeListener(EventListener listener) {
    listenerList.remove(EventListener.class, listener);
  }

  public void fireEvent(Event evt) {
    Object[] listeners = listenerList.getListeners(EventListener.class);
    for (int i = 0, n = listeners.length; i < n; i++) {
      ((EventListener) listeners[i]).handleEvent(evt);
    }
  }
}
