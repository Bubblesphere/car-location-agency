package ui.widgets.list;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.Client;
import data.IListable;
import ui.widgets.utils.Event;
import ui.widgets.utils.EventBubbler;

public class WList extends JList {
	private EventBubbler events;

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
		        		System.out.println("ListValueChanged from List");
		        		eventHandler("ListValueChanged");
		        	}
		        }
			}
		});
	}

	public int getKeyFromIndex(int selectedIndex) {
		if (this.getSelectedIndex() != -1) {
			return ((IListable)this.getModel().getElementAt(this.getSelectedIndex())).getKey();
		}
		return -1;
	}
	
	public String getDisplayedTextFromIndex(int selectedIndex) {
		return ((IListable)this.getModel().getElementAt(this.getSelectedIndex())).getDisplayedText();
	}
	
	public EventBubbler events() {
		return this.events;
	}
	
	public void addElement(IListable element) {
		DefaultListModel model = (DefaultListModel) this.getModel();
		model.addElement(element);
	}
	
	private void eventHandler(String command) {
		this.events.fireEvent(new Event(this, command));
	}
}

