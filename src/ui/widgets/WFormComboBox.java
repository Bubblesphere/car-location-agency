package ui.widgets;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.IListable;
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
		TEXTFIELD_TEXT_CHANGED
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
	  this.add(this.comboBox);
	}

	public IListable getSelected() {
		return (IListable)this.comboBox.getSelectedItem();
	}
}
