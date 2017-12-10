package ui.widgets.tabs;

import java.util.ArrayList;

import javax.swing.JTabbedPane;

import ui.utils.IListable;
import ui.widgets.forms.WAbstractFormPanel;
import ui.widgets.list.IPanelList;
import ui.widgets.list.WList;

public class WTabFormList<T extends IListable> extends WAbstractTabFormList<T> {
	private static final long serialVersionUID = 1L;
	
	public WTabFormList(JTabbedPane tabbedPane, ArrayList<T> listableArray,
			WAbstractFormPanel<T> formAbstract, String name) {
		super(tabbedPane, listableArray, formAbstract, name, new WList<T>(listableArray));
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setLeftListComponent(IPanelList<T> list) {
		this.setLeftComponent((WList)list);
	}

}
