package ui.business.tab;

import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;

import dao.ReservationDao;
import data.Reservation;
import ui.business.form.WFormReservation;
import ui.events.Event;
import ui.events.EventListener;
import ui.widgets.WForm;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

public class WTabReservation extends WSplitPaneTab {

  public WTabReservation(JTabbedPane tabbedPane, DefaultListModel<Reservation> reservations) {
    super(tabbedPane, "Réservation");

    WListAdd addListReservation = new WListAdd(reservations);

    WForm form = new WForm("Information sur la réservation", new WFormReservation());
    form.events().addListener(new ui.events.EventListener() {
      @Override
      public void handleEvent(Event evt) {
        switch ((WForm.Events) evt.getEventName()) {
          case BUTTON_SAVE_CLICK:
            int reservationId = addListReservation.getSelectedIndex();
            reservations.set(reservationId, (Reservation) form.get());
            Reservation currentReservation = reservations.getElementAt(reservationId);
            
            // TODO: Update db
            addListReservation.setModel(reservations);
            break;
          default:
            break;
        }
      }
    });

    addListReservation.events().addListener(new EventListener() {
      @Override
      public void handleEvent(Event evt) {
        switch ((WListAdd.Events) evt.getEventName()) {
          case BUTTON_ADD_CLICKED:
            // TODO: Add Empty to db and return the new reservation
            // TODO: Implement
            // addListReservation.addElement(RESERVATION_FROM_PREVIOUS_STEP);
            break;
          case LIST_VALUE_CHANGED:
            form.set(reservations.getElementAt(addListReservation.getSelectedIndex()));
            break;
          default:
            break;
        }
      }
    });

    this.setLeftComponent(addListReservation);
    this.setRightComponent(form);
  }

}
