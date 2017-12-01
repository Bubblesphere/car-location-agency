package ui.business.tab;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import dao.ReservationDao;
import data.Reservation;
import ui.business.form.WFormReservation;
import ui.events.Event;
import ui.events.EventListener;
import ui.utils.ArrayListHelper;
import ui.widgets.WForm;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

public class WTabReservation extends WSplitPaneTab {

  public WTabReservation(JTabbedPane tabbedPane, ArrayList<Reservation> reservations) {
    super(tabbedPane, "R�servation");

    WListAdd addListReservation = new WListAdd(reservations);

    WForm form = new WForm("Information sur la r�servation", new WFormReservation());
    form.events().addListener(new ui.events.EventListener() {
      @Override
      public void handleEvent(Event evt) {
        switch ((WForm.Events) evt.getEventName()) {
          case BUTTON_SAVE_CLICK:
            int reservationId = addListReservation.getSelectedIndex();
            reservations.set(reservationId, (Reservation) form.get());
            Reservation currentReservation = reservations.get(reservationId);
            if(currentReservation.getReservationId() > 0){
              ReservationDao.update(currentReservation);
            }else{
              currentReservation = ReservationDao.create(currentReservation);
            }       
            reservations.set(reservationId, currentReservation);
            addListReservation.setModel(ArrayListHelper.toDefaultListModel(reservations));
            form.setHasUnsavedContent(false);
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
            if(!form.getHasUnsavedContent()){
             Reservation emptyReservation = new Reservation(-1);
             addListReservation.addElement(emptyReservation);
             form.set(emptyReservation);
             addListReservation.setSelectedIndex(reservations.size() - 1);
             form.setHasUnsavedContent(true);
            } 
            break;
          case LIST_VALUE_CHANGED:
            Reservation selectedReservations = reservations.get(addListReservation.getSelectedIndex());

            if (form.getHasUnsavedContent()) {
              int dialogResult = JOptionPane.showConfirmDialog(null,
                  "Vous �tes en train de changer de r�servation, voulez vous-l'enregistrer avant de quitter ?",
                  "Attention", JOptionPane.YES_NO_CANCEL_OPTION);

              if (dialogResult == JOptionPane.YES_OPTION) {
                Reservation currentReservation = (Reservation) form.get();
                if(currentReservation.getReservationId() == -1){
                  currentReservation = ReservationDao.create(currentReservation);
                  reservations.set(reservations.size() - 1, currentReservation);
                }else{
                  ReservationDao.update(currentReservation);
                  Reservation preUpdateReservation = (Reservation) Arrays.asList(reservations.toArray())
                      .stream().filter(r->((Reservation) r).getReservationId() == ((Reservation) form.get()).getReservationId())
                      .findFirst().orElse(null); 
                  reservations.set(reservations.indexOf(preUpdateReservation), currentReservation);
                }              
                addListReservation.setModel(ArrayListHelper.toDefaultListModel(reservations));
                form.setHasUnsavedContent(false);
              } else if (dialogResult == JOptionPane.NO_OPTION) {              
                Reservation newReservation = (Reservation) Arrays.asList(reservations.toArray())
                    .stream().filter(r->((Reservation) r).getReservationId() == -1)
                    .findFirst().orElse(null);              
                reservations.remove(newReservation);                            
                form.setHasUnsavedContent(false);
              }else{
                break;
              }
            }
            form.set(reservations.get(addListReservation.getSelectedIndex()));
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
