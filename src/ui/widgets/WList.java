package ui.widgets;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.IListable;
import ui.events.Event;
import ui.events.EventBubbler;
import ui.events.IEventName;
import ui.utils.ListableCellRenderer;

public class WList extends JList {
  private EventBubbler events;
  protected int lastSelectedIndex;

  public static enum Events implements IEventName {
    LIST_VALUE_CHANGED
  }

  public WList(DefaultListModel<? extends IListable> list) {
    super(list);

    this.setCellRenderer(new ListableCellRenderer());
    this.setFont(new Font("Segoe UI", Font.PLAIN, 10));
    this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.setAlignmentX(Component.LEFT_ALIGNMENT);

    this.events = new EventBubbler(this.listenerList);

    this.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent listSelectionEvent) {
        boolean isAdjusting = listSelectionEvent.getValueIsAdjusting();
        if (!isAdjusting) {
          JList tempList = (JList) listSelectionEvent.getSource();
          if (tempList.getSelectedIndices().length > 0) {
            updateLastSelected();
            eventHandler(Events.LIST_VALUE_CHANGED);
          }
        }
      }
    });
  }

  public void addElement(IListable element) {
    DefaultListModel model = (DefaultListModel) this.getModel();
    model.addElement(element);
  }

  public int getLastSelectedIndex() {
    return this.lastSelectedIndex;
  }

  private void updateLastSelected() {
    this.lastSelectedIndex = this.getSelectedIndex();
  }

  public EventBubbler events() {
    return this.events;
  }

  private void eventHandler(Events eventName) {
    this.events.fireEvent(new Event(this, eventName));
  }
}
