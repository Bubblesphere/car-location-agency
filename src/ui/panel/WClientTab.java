package ui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;

import data.Client;
import data.IListable;
import ui.form.WFormClient;
import ui.widgets.listAdd.WListAdd;
import ui.widgets.utils.Event;
import ui.widgets.utils.EventListener;

public class WClientTab extends WSplitPaneTab {

	public WClientTab(JTabbedPane tabbedPane, DefaultListModel<Client> clients) {
		super(tabbedPane, "Client");
		
		WFormClient formClient = new WFormClient();
		WListAdd addListClient = new WListAdd(clients);
		
		
		formClient.events().addListener(new ui.widgets.utils.EventListener() {		
			@Override
			public void handleEvent(Event evt) {
				String command = evt.getCommand();
				if (command == "ButtonSaveClick") {
					clients.set(addListClient.getSelectedIndex(), formClient.getClient());
					addListClient.setModel(clients);
				}
			}
		});
		
		addListClient.events().addListener(new EventListener() {
			@Override
			public void handleEvent(Event evt) {
				String command = evt.getCommand();
				if (command == "ButtonAddClick") {
					addListClient.addElement(new Client(3, "Dallaire", "Deric"));
				} else if (command == "ListValueChanged") {
					formClient.setClient(clients.getElementAt(addListClient.getSelectedIndex()));
				}
				System.out.println(evt.getCommand());
				
			}
		});
		
		
		
		this.setLeftComponent(addListClient);
		this.setRightComponent(formClient);
	}

}
