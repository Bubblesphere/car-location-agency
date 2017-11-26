package ui.business.tab;

import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;

import data.Parametre;
import ui.business.form.WFormParametre;
import ui.events.Event;
import ui.events.EventListener;
import ui.widgets.WForm;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

public class WTabParametre extends WSplitPaneTab {

	public WTabParametre(JTabbedPane tabbedPane, DefaultListModel<Parametre> parametres) {
		super(tabbedPane, "Paramètre");
		
		WListAdd addListParametre = new WListAdd(parametres);
		
		WForm form = new WForm("Information sur le paramètre", new WFormParametre());
		form.events().addListener(new ui.events.EventListener() {		
			@Override
			public void handleEvent(Event evt) {
				switch((WForm.Events)evt.getEventName()) {
					case BUTTON_SAVE_CLICK:
						parametres.set(addListParametre.getSelectedIndex(), (Parametre)form.get());
						addListParametre.setModel(parametres);
						break;
					default:
						break;
				}	
			}
		});
		
		addListParametre.events().addListener(new EventListener() {
			@Override
			public void handleEvent(Event evt) {
				switch((WListAdd.Events)evt.getEventName()) {
					case BUTTON_ADD_CLICKED:
						// TODO: Handle new empty location
						//addListParametre.addElement(new Parametre());
						break;
					case LIST_VALUE_CHANGED:
						form.set(parametres.getElementAt(addListParametre.getSelectedIndex()));
						break;
					default:
						break;
				}		
			}
		});
		
		this.setLeftComponent(addListParametre);
		this.setRightComponent(form);
	}

}
