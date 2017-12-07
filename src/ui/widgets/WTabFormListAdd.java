package ui.widgets;

import java.util.ArrayList;

import javax.swing.JTabbedPane;

import ui.events.Event;
import ui.events.EventEnum.ListEvents;
import ui.events.EventEnum.TabFormListEvents;
import ui.events.EventListener;
import ui.utils.IListable;

public class WTabFormListAdd<T extends IListable> extends WAbstractTabFormList<T>  {
	private static final long serialVersionUID = 1L;

	public WTabFormListAdd(JTabbedPane tabbedPane, ArrayList<T> listableArray,
			WAbstractFormPanel<T> formAbstract, String name) {
		super(tabbedPane, listableArray, formAbstract, name, new WListAdd<T>(listableArray));
		this.listPanel.events().addListener(new EventListener() {
	    	@SuppressWarnings("rawtypes") 
	      @Override
	      public void handleEvent(Event evt) {

	        switch ((ListEvents) evt.getEventName()) {
		        case BUTTON_ADD_CLICKED:
		          if (!form.getHasUnsavedContent()) {
		        	  	eventHandler(TabFormListEvents.BUTTON_ADD_CLICKED);
		      			form.set(currentListable);      
		      			form.setHasUnsavedContent(true);
		          }
		          break;
				default:
					break;
		        }
	    	}
		});
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setLeftListComponent(IPanelList<T> list) {
		this.setLeftComponent((WListAdd)list);
	}
}
