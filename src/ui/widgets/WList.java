package ui.widgets;

import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ui.events.Event;
import ui.events.EventBubbler;
import ui.events.EventEnum;
import ui.utils.ArrayListHelper;
import ui.utils.IListable;
import ui.utils.ListableCellRenderer;

public class WList<T extends IListable> extends JList<T> implements IPanelList<T> {
	private static final long serialVersionUID = 1L;
	private EventBubbler events;
	protected int lastSelectedIndex;
 
public WList(ArrayList<T> list) {
    super(ArrayListHelper.toDefaultListModel(list));

    this.setCellRenderer(new ListableCellRenderer());
    this.setFont(new Font("Segoe UI", Font.PLAIN, 10));
    this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.setAlignmentX(Component.LEFT_ALIGNMENT);

    this.events = new EventBubbler(this.listenerList);

    this.addListSelectionListener(new ListSelectionListener() {
      @Override
	public void valueChanged(ListSelectionEvent listSelectionEvent) {
        boolean isAdjusting = listSelectionEvent.getValueIsAdjusting();
        if (!isAdjusting) {
          @SuppressWarnings("unchecked")
          JList<T> tempList = (JList<T>) listSelectionEvent.getSource();
          if (tempList.getSelectedIndices().length > 0) {
            updateLastSelected();
            eventHandler(EventEnum.ListEvents.LIST_VALUE_CHANGED);
          }
        }
      }
    });
  }


	@Override
	public void setSelectedIndexLast() {
		this.setSelectedIndex(this.lastSelectedIndex);
	}

  @Override
public void addElement(T element) {
    DefaultListModel<T> model = (DefaultListModel<T>) this.getModel();
    model.addElement(element);
  }

  private void updateLastSelected() {
    this.lastSelectedIndex = this.getSelectedIndex();
  }

  @Override
public EventBubbler events() {
    return this.events;
  }

  private void eventHandler(EventEnum.ListEvents eventName) {
    this.events.fireEvent(new Event<EventEnum.ListEvents>(this, eventName));
  }

	@Override
	public void setModelList(DefaultListModel<T> list) {
		this.setModel(list);
	}

	@Override
	public int getLastSelectedIndex() {
		return this.lastSelectedIndex;
	}

}
