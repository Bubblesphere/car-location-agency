package ui.list.impl;

import java.awt.event.MouseEvent;

import ui.list.AbstractHashmapListModel;
import ui.list.WidgetListHashMap;


public class WidgetListHashMapClient extends WidgetListHashMap {

	public WidgetListHashMapClient(AbstractHashmapListModel list) {
		super(list);
	}

	@Override
	protected void onClick(MouseEvent e, int key, String displayedText) {
		System.out.println("Key: " + key + " ; displayedText: " + displayedText);
	}
}