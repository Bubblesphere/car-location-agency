package ui.business.tab;

import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;

import data.Client;
import data.Parametre;
import ui.business.form.WFormParametre;
import ui.events.Event;
import ui.events.EventListener;
import ui.widgets.WForm;
import ui.widgets.WList;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

public class WTabParametre extends WSplitPaneTab {

	public WTabParametre(JTabbedPane tabbedPane, DefaultListModel<Parametre> parametres) {
		super(tabbedPane, "Paramètre");
		
		WList listParametre = new WList(parametres);
		
		WForm form = new WForm("Information sur le paramètre", new WFormParametre());
		form.events().addListener(new ui.events.EventListener() {		
			@Override
			public void handleEvent(Event evt) {
				switch((WForm.Events)evt.getEventName()) {
					case BUTTON_SAVE_CLICK:
						int parameterId = listParametre.getSelectedIndex();
						parametres.set(parameterId, (Parametre)form.get());
						Parametre currentParametre = parametres.getElementAt(parameterId);
						// TODO: Update db
						listParametre.setModel(parametres);
						break;
					default:
						break;
				}	
			}
		});
		
		listParametre.events().addListener(new EventListener() {
			@Override
			public void handleEvent(Event evt) {
				switch((WList.Events)evt.getEventName()) {
					case LIST_VALUE_CHANGED:
						form.set(parametres.getElementAt(listParametre.getSelectedIndex()));
						break;
					default:
						break;
				}		
			}
		});
		
		this.setLeftComponent(listParametre);
		this.setRightComponent(form);
	}

}
