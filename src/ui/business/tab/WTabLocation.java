package ui.business.tab;

import java.util.ArrayList;
import javax.swing.JTabbedPane;

import data.Location;
import ui.business.form.WFormLocation;
import ui.events.Event;
import ui.events.EventListener;
import ui.widgets.WForm;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

public class WTabLocation extends WSplitPaneTab {
	private static final long serialVersionUID = 1L;

public WTabLocation(JTabbedPane tabbedPane, ArrayList<Location> locations) {
    super(tabbedPane, "Location");

    WListAdd<Location> addListLocation = new WListAdd<Location>(locations);

    WForm<Location> form = new WForm<Location>("Information sur la location", new WFormLocation());
    form.events().addListener(new ui.events.EventListener() {
    	@SuppressWarnings("rawtypes") 
      @Override
      public void handleEvent(Event evt) {
        switch ((WForm.Events) evt.getEventName()) {
          case BUTTON_SAVE_CLICK:
           //TODO
            break;
          default:
            break;
        }
      }
    });

    addListLocation.events().addListener(new EventListener() {
    	@SuppressWarnings("rawtypes") 
      @Override
      public void handleEvent(Event evt) {
        switch ((WListAdd.Events) evt.getEventName()) {
          case BUTTON_ADD_CLICKED:
        	//TODO
            break;
          case LIST_VALUE_CHANGED:
        	//TODO
            break;
          default:
            break;
        }
      }
    });

    this.setLeftComponent(addListLocation);
    this.setRightComponent(form);
  }

}
