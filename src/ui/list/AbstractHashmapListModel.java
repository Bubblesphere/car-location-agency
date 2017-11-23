package ui.list;

import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractListModel;

public abstract class AbstractHashmapListModel extends AbstractListModel {
	HashMap<Integer, String> hmap = new HashMap<Integer, String>();

	@Override
	public String getElementAt(int e) {
		int i = 0;
		for (Map.Entry<Integer, String> entry : hmap.entrySet()) {
			if (i++ == e) {
				return entry.getValue();
			}
		}
		return null;
	}
	
	public int getKeyAt(int e) {
		int i = 0;
		for (Map.Entry<Integer, String> entry : hmap.entrySet()) {
			if (i++ == e) {
				return entry.getKey();
			}
		}
		return -1;
	}

	@Override
	public int getSize() {
		return hmap.size();
	}

	public abstract Object getFromKey(int key);
	
	public abstract void addElement(Object object);
}