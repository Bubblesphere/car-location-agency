package ui.business.tab;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import dao.ReservationDao;
import data.Location;
import data.Reservation;
import ui.business.form.WFormLocation;
import ui.business.form.WFormReservation;
import ui.events.Event;
import ui.events.EventListener;
import ui.utils.ArrayListHelper;
import ui.widgets.WForm;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

public class WTabLocation extends WSplitPaneTab {

  public WTabLocation(JTabbedPane tabbedPane, ArrayList<Location> locations) {
    super(tabbedPane, "Location");

    WListAdd addListLocation = new WListAdd(locations);

    WForm form = new WForm("Information sur la location", new WFormLocation());
    form.events().addListener(new ui.events.EventListener() {
      @Override
      public void handleEvent(Event evt) {
        switch ((WForm.Events) evt.getEventName()) {
          case BUTTON_SAVE_CLICK:
           //TODO
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
        	//TODO
            break;
          case LIST_VALUE_CHANGED:
        	//TODO
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
