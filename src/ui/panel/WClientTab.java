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
		
		WListAdd addListClient = new WListAdd(clients);
		addListClient.events().addListener(new EventListener() {
			@Override
			public void handleEvent(Event evt) {
				String command = evt.getCommand();
				if (command == "ButtonAddClick") {
					addListClient.addElement(new Client(3, "Dallaire", "Deric"));
				} else if (command == "ListValueChanged") {
					
				}
				System.out.println(evt.getCommand());
				
			}
		});
		
		WFormClient formClient = new WFormClient();
		
		this.setLeftComponent(addListClient);
		this.setRightComponent(formClient);
	}

}
