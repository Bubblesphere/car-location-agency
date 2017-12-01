package ui.widgets;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import data.IListable;
import ui.events.Event;
import ui.events.EventBubbler;
import ui.events.IEventName;
import ui.utils.ArrayListHelper;
import ui.utils.ListableCellRenderer;

public class WFormComboBox extends JPanel {
	private EventBubbler events;
	private BoxLayout layout;
	private WLabel label;
	private JComboBox<? extends IListable> comboBox;

	public static enum Events implements IEventName {
		COMBO_BOX_OPENED, COMBO_BOX_CLOSED, COMBO_BOX_CANCELLED 
	}
	  
	public WFormComboBox(String labelText, ArrayList<? extends IListable> list) {
	  this.events = new EventBubbler(this.listenerList);
	  
	  this.layout = new BoxLayout(this, BoxLayout.Y_AXIS);
	  this.setBorder(new EmptyBorder(24, 0, 0, 16));
	  this.setLayout(this.layout);
	
	  this.label = new WLabel(labelText);
	  this.add(this.label);
	
	  this.comboBox = new JComboBox<>(ArrayListHelper.toDefaultComboBoxListModel(list));
	  this.comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	  this.comboBox.setAlignmentX(this.LEFT_ALIGNMENT);
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
	
	public void set(ArrayList<IListable> list) {
		this.comboBox.setModel(ArrayListHelper.toDefaultComboBoxListModel(list));
	}

	public IListable getSelected() {
		return (IListable)this.comboBox.getSelectedItem();
	}
	
	public void setSelected(IListable listable) {
		this.comboBox.setSelectedItem(listable);
	}
	
	  public EventBubbler events() {
	    return this.events;
	  }
	
	  private void eventHandler(Enum eventName) {
	    this.events.fireEvent(new Event(this, eventName));
	  }
}
