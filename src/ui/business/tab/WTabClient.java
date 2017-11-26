package ui.business.tab;

import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;

import data.Client;
import ui.business.form.WFormClient;
import ui.utils.Event;
import ui.utils.EventListener;
import ui.widgets.WForm;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

public class WTabClient extends WSplitPaneTab {

	public WTabClient(JTabbedPane tabbedPane, DefaultListModel<Client> clients) {
		super(tabbedPane, "Client");
		
		WListAdd addListClient = new WListAdd(clients);
		
		WForm form = new WForm("Information sur le client", new WFormClient());
		form.events().addListener(new ui.utils.EventListener() {		
			@Override
			public void handleEvent(Event evt) {
				switch(evt.getCommand()) {
					case "ButtonSaveClick":
						clients.set(addListClient.getSelectedIndex(), (Client)form.get());
						addListClient.setModel(clients);
						break;
					default:
						System.out.println("Unhandled event");
						break;
				}	
			}
		});
		
		addListClient.events().addListener(new EventListener() {
			@Override
			public void handleEvent(Event evt) {
				switch(evt.getCommand()) {
					case "ButtonAddClick":
						// TODO: Handle new empty location
						addListClient.addElement(new Client(3, "Dallaire", "Deric"));
						break;
					case "ListValueChanged":
						form.set(clients.getElementAt(addListClient.getSelectedIndex()));
						break;
					default:
						System.out.println("Unhandled event");
						break;
				}		
			}
		});
		
		this.setLeftComponent(addListClient);
		this.setRightComponent(form);
	}

}
