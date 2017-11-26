package ui.business.tab;

import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;

import data.Vehicule;
import ui.business.form.WFormVehicule;
import ui.events.Event;
import ui.events.EventListener;
import ui.widgets.WForm;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

public class WTabVehicule extends WSplitPaneTab {

	public WTabVehicule(JTabbedPane tabbedPane, DefaultListModel<Vehicule> vehicules) {
		super(tabbedPane, "Véhicule");
		
		WListAdd addListVehicule = new WListAdd(vehicules);
		
		WForm form = new WForm("Information sur le véhicule", new WFormVehicule());
		form.events().addListener(new ui.events.EventListener() {		
			@Override
			public void handleEvent(Event evt) {
				switch((WForm.Events)evt.getEventName()) {
					case BUTTON_SAVE_CLICK:
						vehicules.set(addListVehicule.getSelectedIndex(), (Vehicule)form.get());
						addListVehicule.setModel(vehicules);
						break;
					default:
						System.out.println("Unhandled event");
						break;
				}	
			}
		});
		
		addListVehicule.events().addListener(new EventListener() {
			@Override
			public void handleEvent(Event evt) {
				switch((WListAdd.Events)evt.getEventName()) {
					case BUTTON_ADD_CLICKED:
						// TODO: Handle new empty location
						//addListVehicule.addElement(new Vehicule(3, "Dallaire", "Deric"));
						break;
					case LIST_VALUE_CHANGED:
						form.set(vehicules.getElementAt(addListVehicule.getSelectedIndex()));
						break;
					default:
						System.out.println("Unhandled event");
						break;
				}		
			}
		});
		
		this.setLeftComponent(addListVehicule);
		this.setRightComponent(form);
	}

}
