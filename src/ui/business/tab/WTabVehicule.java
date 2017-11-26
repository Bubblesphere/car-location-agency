package ui.business.tab;

import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;

import data.Vehicule;
import ui.business.form.WFormVehicule;
import ui.utils.Event;
import ui.utils.EventListener;
import ui.widgets.WForm;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

public class WTabVehicule extends WSplitPaneTab {

	public WTabVehicule(JTabbedPane tabbedPane, DefaultListModel<Vehicule> vehicules) {
		super(tabbedPane, "Véhicule");
		
		WListAdd addListVehicule = new WListAdd(vehicules);
		
		WForm form = new WForm("Information sur le véhicule", new WFormVehicule());
		form.events().addListener(new ui.utils.EventListener() {		
			@Override
			public void handleEvent(Event evt) {
				switch(evt.getCommand()) {
					case "ButtonSaveClick":
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
				switch(evt.getCommand()) {
					case "ButtonAddClick":
						// TODO: Handle new empty location
						//addListVehicule.addElement(new Vehicule(3, "Dallaire", "Deric"));
						break;
					case "ListValueChanged":
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
