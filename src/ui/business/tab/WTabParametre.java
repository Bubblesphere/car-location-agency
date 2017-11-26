package ui.business.tab;

import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;

import data.Client;
import data.Parametre;
import ui.business.form.WFormClient;
import ui.business.form.WFormParametre;
import ui.utils.Event;
import ui.utils.EventListener;
import ui.widgets.WForm;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

public class WTabParametre extends WSplitPaneTab {

	public WTabParametre(JTabbedPane tabbedPane, DefaultListModel<Parametre> parametres) {
		super(tabbedPane, "Paramètre");
		
		WListAdd addListParametre = new WListAdd(parametres);
		
		WForm form = new WForm("Information sur le paramètre", new WFormParametre());
		form.events().addListener(new ui.utils.EventListener() {		
			@Override
			public void handleEvent(Event evt) {
				switch(evt.getCommand()) {
					case "ButtonSaveClick":
						parametres.set(addListParametre.getSelectedIndex(), (Parametre)form.get());
						addListParametre.setModel(parametres);
						break;
					default:
						System.out.println("Unhandled event");
						break;
				}	
			}
		});
		
		addListParametre.events().addListener(new EventListener() {
			@Override
			public void handleEvent(Event evt) {
				switch(evt.getCommand()) {
					case "ButtonAddClick":
						// TODO: Handle new empty location
						//addListParametre.addElement(new Parametre());
						break;
					case "ListValueChanged":
						form.set(parametres.getElementAt(addListParametre.getSelectedIndex()));
						break;
					default:
						System.out.println("Unhandled event");
						break;
				}		
			}
		});
		
		this.setLeftComponent(addListParametre);
		this.setRightComponent(form);
	}

}
