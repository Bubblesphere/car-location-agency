package ui.list;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class WidgetListHashMap extends JList {
	protected HashMapListModel hmap;
	protected int selectedKey = -1;
	protected String selectedDisplayedText = "";
	
	public WidgetListHashMap(HashMapListModel list) {
		super(list);
		this.hmap = list;
		this.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				boolean isAdjusting = listSelectionEvent.getValueIsAdjusting();
		        if (!isAdjusting) {
		        	JList tempList = (JList) listSelectionEvent.getSource();
					getKeyFromIndex(tempList.getSelectedIndices()[0]);
		    		getDisplayedTextFromIndex(tempList.getSelectedIndices()[0]);
		        }
			}
		});
	}
	
	public int getSelectedKey() {
		return this.selectedKey;
	}
	
	public String getSelectedDisplayedText() {
		return this.selectedDisplayedText;
	}
	
	private void getKeyFromIndex(int selectedIndex) {
		this.selectedKey = this.hmap.getKeyAt(selectedIndex);
	}
	
	private void getDisplayedTextFromIndex(int selectedIndex) {
		this.selectedDisplayedText = this.hmap.getElementAt(selectedIndex);
	}
}