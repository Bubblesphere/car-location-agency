package ui.business.tab;

import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;

import data.Client;
import data.Reservation;
import ui.business.form.WFormClient;
import ui.business.form.WFormReservation;
import ui.utils.Event;
import ui.utils.EventListener;
import ui.widgets.WForm;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

public class WTabReservation extends WSplitPaneTab {

	public WTabReservation(JTabbedPane tabbedPane, DefaultListModel<Reservation> reservations) {
		super(tabbedPane, "Réservation");
		
		WListAdd addListReservation = new WListAdd(reservations);
		
		WForm form = new WForm("Information sur la réservation", new WFormReservation());
		form.events().addListener(new ui.utils.EventListener() {		
			@Override
			public void handleEvent(Event evt) {
				switch(evt.getCommand()) {
					case "ButtonSaveClick":
						reservations.set(addListReservation.getSelectedIndex(), (Reservation)form.get());
						addListReservation.setModel(reservations);
						break;
					default:
						System.out.println("Unhandled event");
						break;
				}	
			}
		});
		
		addListReservation.events().addListener(new EventListener() {
			@Override
			public void handleEvent(Event evt) {
				switch(evt.getCommand()) {
					case "ButtonAddClick":
						// TODO: Handle new empty location
						//addListReservation.addElement(new Reservation());
						break;
					case "ListValueChanged":
						form.set(reservations.getElementAt(addListReservation.getSelectedIndex()));
						break;
					default:
						System.out.println("Unhandled event");
						break;
				}		
			}
		});
		
		this.setLeftComponent(addListReservation);
		this.setRightComponent(form);
	}

}
