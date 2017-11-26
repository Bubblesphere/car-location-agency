package ui.business.tab;

import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;

import data.Client;
import data.Location;
import ui.business.form.WFormClient;
import ui.business.form.WFormRetour;
import ui.utils.Event;
import ui.utils.EventListener;
import ui.widgets.WForm;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

public class WTabRetour extends WSplitPaneTab {

	public WTabRetour(JTabbedPane tabbedPane, DefaultListModel<Location> locations) {
		super(tabbedPane, "Client");
		
		WListAdd addListLocation = new WListAdd(locations);
		
		WForm form = new WForm("Information sur le retour", new WFormRetour());
		form.events().addListener(new ui.utils.EventListener() {		
			@Override
			public void handleEvent(Event evt) {
				switch(evt.getCommand()) {
					case "ButtonSaveClick":
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
				switch(evt.getCommand()) {
					case "ButtonAddClick":
						// TODO: Handle new empty location
						//addListClient.addElement(new Location()));
						break;
					case "ListValueChanged":
						form.set(locations.getElementAt(addListLocation.getSelectedIndex()));
						break;
					default:
						System.out.println("Unhandled event");
						break;
				}		
			}
		});
		
		this.setLeftComponent(addListLocation);
		this.setRightComponent(form);
	}

}
