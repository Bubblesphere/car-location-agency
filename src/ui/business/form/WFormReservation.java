package ui.business.form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.ClasseDao;
import dao.ClientDao;
import data.Classe;
import data.Client;
import data.Reservation;
import data.Utilisateur;
import ui.events.Event;
import ui.events.EventEnum.FormTextFieldEvents;
import ui.events.EventListener;
import ui.utils.FormBuilder;
import ui.widgets.WAbstractFormPanel;
import ui.widgets.WFormComboBox;
import ui.widgets.WFormTextField;

public class WFormReservation extends WAbstractFormPanel<Reservation> {
	private static final long serialVersionUID = 1L;

private int formReservationID;

  private GridBagLayout layout;

  private WFormTextField textFieldStartDate;
  private GridBagConstraints gbcStartDate;

  private WFormTextField textFieldEndDate;
  private GridBagConstraints gbcEndDate;

  private WFormTextField textFieldNote;
  private GridBagConstraints gbcNote;
  
  private WFormComboBox<Client> comboBoxClient;
  private GridBagConstraints gbcClient;
  
  private WFormComboBox<Classe> comboBoxClasse;
  private GridBagConstraints gbcClasse;

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
    
    this.comboBoxClient = new WFormComboBox<Client>("Client",  (ArrayList<Client>) ClientDao.retrieveAll());
    this.gbcClient = FormBuilder.getGBCPartialRow();
    this.gbcClient.gridx = 0;
    this.gbcClient.gridy = 3;
    this.add(this.comboBoxClient, this.gbcClient);
    
    this.comboBoxClasse = new WFormComboBox<Classe>("Classe",  (ArrayList<Classe>) ClasseDao.retrieveAll());
    this.gbcClasse = FormBuilder.getGBCPartialRow();
    this.gbcClasse.gridx = 1;
    this.gbcClasse.gridy = 3;
    this.add(this.comboBoxClasse, this.gbcClasse);

    EventListener textBoxValueChangedListener = new EventListener() {
    	@SuppressWarnings("rawtypes") 
      @Override
      public void handleEvent(Event evt) {
        switch ((FormTextFieldEvents) evt.getEventName()) {
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
  public Reservation get() {
    // TODO:currentUser
    return new Reservation(this.formReservationID, (Client)this.comboBoxClient.getSelected(),
       (Classe) this.comboBoxClasse.getSelected(), LocalDate.parse(this.textFieldStartDate.getText()),
        LocalDate.parse(this.textFieldEndDate.getText()), this.textFieldNote.getText(),
        new Utilisateur("nom", "prenom", 1, "blbalblab", "courriel", 123, 1, false));
  }
  
  @Override
  public void init() {
	  
  }
  
  public WFormComboBox<Classe> getComboBoxClasse() {
	  return this.comboBoxClasse;
  }
  
  public WFormComboBox<Client> getComboBoxClient() {
	  return this.comboBoxClient;
  }

@Override
public void set(Reservation listable) {
	 Reservation reservation = (Reservation) listable;
	    this.formReservationID = reservation.getReservationId();
	    this.textFieldNote.setText(reservation.getNoteReservation());
	    this.textFieldEndDate.setText(reservation.getFinDate().toString());
	    this.textFieldStartDate.setText(reservation.getStartDate().toString());
	    this.hasUnsavedContent = false;   
	
}
  
  
}
