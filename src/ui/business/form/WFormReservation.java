package ui.business.form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;

import data.Classe;
import data.Client;
import data.IListable;
import data.Reservation;
import data.Utilisateur;
import ui.events.Event;
import ui.events.EventListener;
import ui.utils.FormBuilder;
import ui.widgets.WAbstractFormPanel;
import ui.widgets.WFormTextField;

public class WFormReservation extends WAbstractFormPanel {
  private int formReservationID;

  private GridBagLayout layout;

  private WFormTextField textFieldStartDate;
  private GridBagConstraints gbcStartDate;

  private WFormTextField textFieldEndDate;
  private GridBagConstraints gbcEndDate;

  private WFormTextField textFieldNote;
  private GridBagConstraints gbcNote;
  // TODO: Client, Classe

  public WFormReservation() {
    this.layout = FormBuilder.getLayout();
    this.setLayout(this.layout);

    this.textFieldStartDate = new WFormTextField("D�but");
    this.gbcStartDate = FormBuilder.getGBCPartialRow();
    this.gbcStartDate.gridx = 0;
    this.gbcStartDate.gridy = 1;
    this.add(this.textFieldStartDate, this.gbcStartDate);

    this.textFieldEndDate = new WFormTextField("Fin");
    this.gbcEndDate = FormBuilder.getGBCPartialRow();
    this.gbcEndDate.gridx = 1;
    this.gbcEndDate.gridy = 1;
    this.add(this.textFieldEndDate, this.gbcEndDate);

    this.textFieldNote = new WFormTextField("Note");
    this.gbcNote = FormBuilder.getGBCFullRow();
    this.gbcNote.gridx = 0;
    this.gbcNote.gridy = 2;
    this.add(this.textFieldNote, this.gbcNote);
    // TODO: Client, Classe

    EventListener textBoxValueChangedListener = new EventListener() {
      @Override
      public void handleEvent(Event evt) {
        switch ((WFormTextField.Events) evt.getEventName()) {
        case TEXTFIELD_TEXT_CHANGED:
          setHasUnsavedContent(true);
          break;
        default:
          break;
        }
      }
    };

    textFieldStartDate.events().addListener(textBoxValueChangedListener);
    textFieldEndDate.events().addListener(textBoxValueChangedListener);
    textFieldNote.events().addListener(textBoxValueChangedListener);
  }

  @Override
  public IListable get() {
    // TODO: Client, Classe, currentUser
    return new Reservation(this.formReservationID, new Client(1, "nom", "prenom"),
        new Classe(1, "sedan", 10.0f), LocalDate.parse(this.textFieldStartDate.getText()),
        LocalDate.parse(this.textFieldEndDate.getText()), this.textFieldNote.getText(),
        new Utilisateur("nom", "prenom", 1, "blbalblab", "courriel", 123, 1, false));
  }
  
  @Override
  public void init() {
	  
  }

  @Override
  public void set(IListable listable) {
    Reservation reservation = (Reservation) listable;
    this.formReservationID = reservation.getReservationId();
    this.textFieldNote.setText(reservation.getNoteReservation());
    this.textFieldEndDate.setText(reservation.getFinDate().toString());
    this.textFieldStartDate.setText(reservation.getStartDate().toString());
    this.hasUnsavedContent = false;    
  }
}
