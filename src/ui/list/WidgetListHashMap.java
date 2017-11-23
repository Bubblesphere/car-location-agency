package ui.list;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

public abstract class WidgetListHashMap extends JList {
	protected AbstractHashmapListModel hmap;
	public WidgetListHashMap(AbstractHashmapListModel list) {
		super(list);
		this.hmap = list;
		this.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		onClick(e, getKeyForMouseEvent(e), getDisplayedTextForMouseEvent(e));
	    	}
	    });
	}
	
	private int getKeyForMouseEvent(MouseEvent e) {
		int selectedIndex = this.locationToIndex(e.getPoint());
		return this.hmap.getKeyAt(selectedIndex);
	}
	
	private String getDisplayedTextForMouseEvent(MouseEvent e) {
		int selectedIndex = this.locationToIndex(e.getPoint());
		return this.hmap.getElementAt(selectedIndex);
	}
	
	protected abstract void onClick(MouseEvent e, int key, String displayedText);
}