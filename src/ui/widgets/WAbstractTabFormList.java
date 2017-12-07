package ui.widgets;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import ui.events.Event;
import ui.events.EventBubbler;
import ui.events.EventEnum;
import ui.events.EventEnum.FormEvents;
import ui.events.EventEnum.ListEvents;
import ui.events.EventListener;
import ui.utils.ArrayListHelper;
import ui.utils.IListable;

public abstract class WAbstractTabFormList<T extends IListable> extends WSplitPaneTab {
	private static final long serialVersionUID = 1L;
	protected EventBubbler events;
	protected WForm<T> form;
	protected IPanelList<T> listPanel;
	protected T currentListable;
	protected ArrayList<T> listable;
	
  protected WAbstractTabFormList(JTabbedPane tabbedPane, ArrayList<T> listableArray, WAbstractFormPanel<T> formAbstract, String name, IPanelList<T> listPanelComponent) {
    super(tabbedPane, name);
		  
  this.events = new EventBubbler(this.listenerList);

  	this.listable = listableArray;
  	this.listPanel = listPanelComponent;

    this.form = new WForm<T>("Information sur " + name, formAbstract);
    this.form.events().addListener(new ui.events.EventListener() {
    	@SuppressWarnings("rawtypes") 
      @Override
      public void handleEvent(Event evt) {    
		switch ((FormEvents) evt.getEventName()) {
      	case BUTTON_SAVE_CLICKED:        
            currentListable = (T) form.get();
            if(currentListable.getId() > 0){
                eventHandler(EventEnum.TabFormListEvents.BUTTON_SAVE_CLICKED_MODIFY);
                listable.set(listPanel.getLastSelectedIndex(),(T)currentListable);
            }else{
                eventHandler(EventEnum.TabFormListEvents.BUTTON_SAVE_CLICKED_NEW);
                listable.add((T)currentListable);
            }       
            listPanel.setModelList(ArrayListHelper.toDefaultListModel(listable));
            form.setHasUnsavedContent(false);  
          break;
        default:
          break;
        }
      }
    }); 
    
    listPanel.events().addListener(new EventListener() {
    	@SuppressWarnings("rawtypes") 
      @Override
      public void handleEvent(Event evt) {

        switch ((ListEvents) evt.getEventName()) {
        case LIST_VALUE_CHANGED:
	          if (form.getHasUnsavedContent()) {
	            int dialogResult = JOptionPane.showConfirmDialog(null,
	                "Voulez vous sauvegarder les informations non sauvegardées avant de quitter?",
	                "Attention", JOptionPane.YES_NO_CANCEL_OPTION);
	
	            if (dialogResult == JOptionPane.YES_OPTION) {
	            	
	            	currentListable = (T) form.get();
	                if(currentListable.getId() == -1){
	                	eventHandler(EventEnum.TabFormListEvents.LIST_VALUE_CHANGED_W_UNSAVED_CONTENT_YES_NEW); 
	                    listable.add(currentListable);
	                  }else{
                	  	eventHandler(EventEnum.TabFormListEvents.LIST_VALUE_CHANGED_W_UNSAVED_CONTENT_YES_MODIFY); 
	                    @SuppressWarnings("unchecked")
						T preUpdateClient = (T) Arrays.asList(listable.toArray())
	                        .stream().filter(c->((T) c).getId() == ((T) form.get()).getId())
	                        .findFirst().orElse(null); 
	                    listable.set(listable.indexOf(preUpdateClient), currentListable);
	                  }              
	                form.setHasUnsavedContent(false);
	                listPanel.setModelList(ArrayListHelper.toDefaultListModel(listable));
	            } else if (dialogResult == JOptionPane.NO_OPTION) {      
	      			form.setHasUnsavedContent(false);
	            }else{
	              break;
	            }
	          }
	          int nextIndex = listable.size()-1 < listPanel.getLastSelectedIndex() ? listable.size()-1 : listPanel.getLastSelectedIndex();
	          form.set(listable.get(nextIndex));
	          break;
        default:
          break;
        }
      }
    });

    setLeftListComponent(listPanel);
    this.setRightComponent(form);
  }
  
  	public abstract void setLeftListComponent(IPanelList<T> list);
	
	public void add (T listable) {
		this.currentListable = listable;
	}
	
	public T getCurrentListable() {
		return this.currentListable;
	}

	  public WForm<T> getForm() {
		  return this.form;
	  }
	  
	  public EventBubbler events() {
	    return this.events;
	  }
	
	  protected void eventHandler(EventEnum.TabFormListEvents event) {
	    this.events.fireEvent(new Event<EventEnum.TabFormListEvents>(this, event));
	  }
}
