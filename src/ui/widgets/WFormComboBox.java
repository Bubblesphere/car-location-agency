package ui.widgets;

import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import data.IListable;
import ui.events.Event;
import ui.events.EventBubbler;
import ui.events.IEventName;
import ui.utils.ArrayListHelper;
import ui.utils.ListableCellRenderer;


public class WFormComboBox<T extends IListable> extends JPanel {
	private static final long serialVersionUID = 1L;
	private EventBubbler events;
	private JComboBox<T> comboBox;

	public static enum Events implements IEventName {
		COMBO_BOX_OPENED, COMBO_BOX_CLOSED, COMBO_BOX_CANCELLED 
	}
	  
	public WFormComboBox(String labelText, ArrayList<T> list) {
	  this.events = new EventBubbler(this.listenerList);
	  
	  this.comboBox = new JComboBox<T>(ArrayListHelper.toDefaultComboBoxListModel(list));
	  this.comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	  this.comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
	  this.comboBox.setRenderer(new ListableCellRenderer());
	  this.comboBox.addPopupMenuListener(new PopupMenuListener() {

		@Override
		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			eventHandler(Events.COMBO_BOX_OPENED);
		}

		@Override
		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			eventHandler(Events.COMBO_BOX_CLOSED);
		}

		@Override
		public void popupMenuCanceled(PopupMenuEvent e) {
			eventHandler(Events.COMBO_BOX_CANCELLED);
		}
		  
	  });
	  this.add(this.comboBox);
	}
	
	@SuppressWarnings("unchecked")
	public void set(ArrayList<T> list) {
		T previouslySelected = (T)this.comboBox.getSelectedItem();
		this.comboBox.setModel(ArrayListHelper.toDefaultComboBoxListModel(list));
		setSelected(previouslySelected);
	}

	@SuppressWarnings("unchecked")
	public T getSelected() {
		return (T)this.comboBox.getSelectedItem();
	}
	
	public void setSelected(T listable) {
		this.comboBox.getModel().setSelectedItem(listable);
	}
	
	  public EventBubbler events() {
	    return this.events;
	  }
	
	  private void eventHandler(Events eventName) {
	    this.events.fireEvent(new Event<Events>(this, eventName));
	  }
}
