package ui.widgets;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import dao.ClientDao;
import data.Client;
import ui.events.Event;
import ui.events.EventBubbler;
import ui.events.EventListener;
import ui.events.IEventName;
import ui.utils.ArrayListHelper;
import ui.utils.IListable;

public class WTabFormListAdd<T extends IListable> extends WSplitPaneTab {
	private static final long serialVersionUID = 1L;
	private EventBubbler events;
	private WForm<T> form;
	private WListAdd<T> addList;
	private T currentListable;
	private ArrayList<T> listable;

	  public enum Events implements IEventName {
		    BUTTON_ADD_CLICKED, 
		    BUTTON_SAVE_CLICKED_MODIFY,
		    BUTTON_SAVE_CLICKED_NEW,
		    LIST_VALUE_CHANGED_W_UNSAVED_CONTENT_YES_MODIFY,
		    LIST_VALUE_CHANGED_W_UNSAVED_CONTENT_YES_NEW
	  }
	
  public WTabFormListAdd(JTabbedPane tabbedPane, ArrayList<T> listableArray, WAbstractFormPanel<T> formAbstract, String name) {
    super(tabbedPane, name);
		  
  this.events = new EventBubbler(this.listenerList);

  	this.listable = listableArray;
    WListAdd<T> addList = new WListAdd<T>(listable);

    this.form = new WForm<T>("Information sur " + name, formAbstract);
    this.form.events().addListener(new ui.events.EventListener() {
    	@SuppressWarnings("rawtypes") 
      @Override
      public void handleEvent(Event evt) {    
		switch ((WForm.Events) evt.getEventName()) {
      	case BUTTON_SAVE_CLICK:
            int id = addList.getSelectedIndex();          
            currentListable = (T) form.get();
            if(currentListable.getId() > 0){
                eventHandler(Events.BUTTON_SAVE_CLICKED_MODIFY);
            }else{
                eventHandler(Events.BUTTON_SAVE_CLICKED_NEW);
            }       
            listable.set(id, (T)currentListable);
            addList.setModel(ArrayListHelper.toDefaultListModel(listable));
            form.setHasUnsavedContent(false);  
          break;
        default:
          break;
        }
      }
    }); 
    
    addList.events().addListener(new EventListener() {
    	@SuppressWarnings("rawtypes") 
      @Override
      public void handleEvent(Event evt) {

        switch ((WListAdd.Events) evt.getEventName()) {
        case BUTTON_ADD_CLICKED:
          if (!form.getHasUnsavedContent()) {
        	  	eventHandler(Events.BUTTON_ADD_CLICKED);
      			addList.addElement(currentListable);
      			addList.setSelectedIndexLast();
      			form.set(currentListable);       
      			form.setHasUnsavedContent(true);
      			listable.add(currentListable); 
          }
          break;
        case LIST_VALUE_CHANGED:
	          if (form.getHasUnsavedContent()) {
	            int dialogResult = JOptionPane.showConfirmDialog(null,
	                "Voulez vous sauvegarder les informations non sauvegardées avant de quitter?",
	                "Attention", JOptionPane.YES_NO_CANCEL_OPTION);
	
	            if (dialogResult == JOptionPane.YES_OPTION) {
	            	
	            	currentListable = (T) form.get();
	                if(currentListable.getId() == -1){
	                	eventHandler(Events.LIST_VALUE_CHANGED_W_UNSAVED_CONTENT_YES_NEW); 
	                    listable.set(listable.size() - 1, currentListable);
	                  }else{
                	  	eventHandler(Events.LIST_VALUE_CHANGED_W_UNSAVED_CONTENT_YES_MODIFY); 
	                    T preUpdateClient = (T) Arrays.asList(listable.toArray())
	                        .stream().filter(c->((T) c).getId() == ((T) form.get()).getId())
	                        .findFirst().orElse(null); 
	                    listable.set(listable.indexOf(preUpdateClient), currentListable);
	                  }              
	                  addList.setModel(ArrayListHelper.toDefaultListModel(listable));
	                  form.setHasUnsavedContent(false);
	            } else if (dialogResult == JOptionPane.NO_OPTION) {      
	            	T newListable = (T) Arrays.asList(listable.toArray())
	                        .stream().filter(c->((T) c).getId() == -1)
	                        .findFirst().orElse(null);              
                    listable.remove(newListable);          
	      			form.setHasUnsavedContent(false);
	            }else{
	              break;
	            }
	          }

	          form.set(listable.get(addList.getSelectedIndex()));
	          break;
        default:
          break;
        }
      }
    });

    this.setLeftComponent(addList);
    this.setRightComponent(form);
  }

	public WListAdd<T> getListAdd() {
		return this.addList;
	}
	
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
	
	  private void eventHandler(Events event) {
	    this.events.fireEvent(new Event<Events>(this, event));
	  }
}
