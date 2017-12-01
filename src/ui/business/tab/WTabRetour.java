package ui.business.tab;

import java.util.ArrayList;

import javax.swing.JTabbedPane;

import data.Location;
import ui.business.form.WFormRetour;
import ui.events.Event;
import ui.events.EventListener;
import ui.utils.ArrayListHelper;
import ui.widgets.WForm;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

public class WTabRetour extends WSplitPaneTab {

  public WTabRetour(JTabbedPane tabbedPane, ArrayList<Location> locations) {
    super(tabbedPane, "Retour");

    WListAdd addListLocation = new WListAdd(locations);

    WForm form = new WForm("Information sur le retour", new WFormRetour());
    form.events().addListener(new ui.events.EventListener() {
      @Override
      public void handleEvent(Event evt) {
        switch ((WForm.Events) evt.getEventName()) {
          case BUTTON_SAVE_CLICK:
            int locationId = addListLocation.getSelectedIndex();
            locations.set(locationId, (Location) form.get());
            Location currentLocation = locations.get(locationId);
            // TODO: Update db
            addListLocation.setModel(ArrayListHelper.toDefaultListModel(locations));
            break;
          default:
            break;
        }
      }
    });

    addListLocation.events().addListener(new EventListener() {
      @Override
      public void handleEvent(Event evt) {
        switch ((WListAdd.Events) evt.getEventName()) {
          case BUTTON_ADD_CLICKED:
            // TODO: Add Empty to db and return the new location
            // TODO: Implement
            // addListClient.addElement(LOCATION_FROM_PREVIOUS_STEP));
            break;
          case LIST_VALUE_CHANGED:
            form.set(locations.get(addListLocation.getSelectedIndex()));
            break;
          default:
            break;
        }
      }
    });

    this.setLeftComponent(addListLocation);
    this.setRightComponent(form);
  }

}
