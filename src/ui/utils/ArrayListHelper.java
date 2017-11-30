package ui.utils;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import data.IListable;

public class ArrayListHelper {
	
	public static <T> DefaultListModel<T> toDefaultListModel(ArrayList<T> list) {
		DefaultListModel<T> returnList = new DefaultListModel<>();
		for(int i = 0; i < list.size(); i++)
			returnList.addElement((T)list.get(i));
		return returnList;
	}
	
	public static <T> DefaultComboBoxModel<T> toDefaultComboBoxListModel(ArrayList<T> list) {
		DefaultComboBoxModel<T> returnList = new DefaultComboBoxModel<>();
		for(int i = 0; i < list.size(); i++)
			returnList.addElement((T)list.get(i));
		return returnList;
	}
}
