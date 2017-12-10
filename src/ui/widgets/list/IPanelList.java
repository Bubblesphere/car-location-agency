package ui.widgets.list;

import javax.swing.DefaultListModel;

import ui.events.EventBubbler;
import ui.utils.IListable;

public interface IPanelList<T extends IListable> {
	  public void setSelectedIndexLast();

	  public int getLastSelectedIndex();
	  
	  public void setSelectedIndex(int index);

	  public EventBubbler events();

	  public void setModelList(DefaultListModel<T> list);

	  public void addElement(T listable);

}
