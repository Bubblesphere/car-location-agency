package ui.business.tab;

import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;

import data.Location;
import ui.business.form.WFormRetour;
import ui.events.Event;
import ui.events.EventListener;
import ui.widgets.WForm;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

public class WTabRetour extends WSplitPaneTab {

	public WTabRetour(JTabbedPane tabbedPane, DefaultListModel<Location> locations) {
		super(tabbedPane, "Client");
		
		WListAdd addListLocation = new WListAdd(locations);
		
		WForm form = new WForm("Information sur le retour", new WFormRetour());
		form.events().addListener(new ui.events.EventListener() {		
			@Override
			public void handleEvent(Event evt) {
				switch((WForm.Events)evt.getEventName()) {
					case BUTTON_SAVE_CLICK:
						locations.set(addListLocation.getSelectedIndex(), (Location)form.get());
						addListLocation.setModel(locations);
						break;
					default:
						System.out.println("Unhandled event");
						break;
				}	
			}
		});
		
		addListLocation.events().addListener(new EventListener() {
			@Override
			public void handleEvent(Event evt) {
				switch((WListAdd.Events)evt.getEventName()) {
					case BUTTON_ADD_CLICKED:
						// TODO: Handle new empty location
						//addListClient.addElement(new Location()));
						break;
					case LIST_VALUE_CHANGED:
						form.set(locations.getElementAt(addListLocation.getSelectedIndex()));
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
