package ui.business.form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import dao.ReservationDao;
import dao.UtilisateurDao;
import dao.VehiculeDao;
import data.Location;
import data.Reservation;
import data.TypePaiement;
import data.TypePaiement.type;
import data.Vehicule;
import ui.events.Event;
import ui.events.EventEnum.FormButtonEvents;
import ui.events.EventEnum.FormTextFieldEvents;
import ui.events.EventListener;
import ui.utils.FormBuilder;
import ui.widgets.WAbstractFormPanel;
import ui.widgets.WFormButton;
import ui.widgets.WFormComboBox;
import ui.widgets.WFormTextField;
import ui.widgets.WLabel;

public class WFormLocation extends WAbstractFormPanel<Location> {
    private static final long serialVersionUID = 1L;

    private int formLocationID;

    private GridBagLayout layout;

    private WFormComboBox<Reservation> comboBoxReservation;
    private GridBagConstraints gbcReservation;

    //TODO get only the ones from the reservation class
    private WFormComboBox<Vehicule> comboBoxVehicule;
    private GridBagConstraints gbcVehicule;

    private WFormTextField textFieldDepartKm;
    private GridBagConstraints gbcDepartKm;

    private WFormTextField textFieldNote;
    private GridBagConstraints gbcNote;

    private WLabel paiementsListField;
    private GridBagConstraints gbcPaiements;
    
    private WFormButton buttonPay;
    private GridBagConstraints gbcPay;


    //TODO assurance, usureJournalier

    public WFormLocation() {
        this.layout = FormBuilder.getLayout();
        this.setLayout(this.layout);

        this.comboBoxReservation = new WFormComboBox<Reservation>("Réservation", (ArrayList<Reservation>) ReservationDao.retrieveAll(true));
        this.gbcReservation = FormBuilder.getGBCFullRow();
        this.gbcReservation.gridx = 0;
        this.gbcReservation.gridy = 1;
        this.add(this.comboBoxReservation, this.gbcReservation);

        this.textFieldDepartKm = new WFormTextField("Kilométrage avant le départ");
        this.gbcDepartKm = FormBuilder.getGBCPartialRow();
        this.gbcDepartKm.gridx = 0;
        this.gbcDepartKm.gridy = 2;
        this.add(this.textFieldDepartKm, this.gbcDepartKm);

        this.comboBoxVehicule = new WFormComboBox<Vehicule>("Véhicule", (ArrayList<Vehicule>) VehiculeDao.retrieveAll());
        this.gbcVehicule = FormBuilder.getGBCPartialRow();
        this.gbcVehicule.gridx = 1;
        this.gbcVehicule.gridy = 2;
        this.add(this.comboBoxVehicule, this.gbcVehicule);

        this.textFieldNote = new WFormTextField("Note");
        this.gbcNote = FormBuilder.getGBCFullRow();
        this.gbcNote.gridx = 0;
        this.gbcNote.gridy = 4;
        this.add(this.textFieldNote, this.gbcNote);

        this.paiementsListField = new WLabel("<html>Paiements<br />FILL ME WITH REAL DATA</html>"); //TODO mettre en read-only tous les paiements?
        this.gbcPaiements = FormBuilder.getGBCPartialRow();
        this.gbcPaiements.gridx = 0;
        this.gbcPaiements.gridy = 5;
        this.add(this.paiementsListField, this.gbcPaiements);

        this.buttonPay = new WFormButton("Payer");
        this.gbcPaiements = FormBuilder.getGBCFullRow();
        this.gbcPaiements.gridx = 0;
        this.gbcPaiements.gridy = 6;
        this.add(this.buttonPay, this.gbcPaiements);
        
        this.buttonPay.events().addListener(new EventListener() {
        	@SuppressWarnings("rawtypes") 
            @Override
            public void handleEvent(Event evt) {
              switch ((FormButtonEvents) evt.getEventName()) {
              case BUTTON_CLICKED:
            	  WLabel lblTotal = new WLabel("Total:");
            	  WLabel lblAmount = new WLabel("99$");
            	  ArrayList<TypePaiement> list = new ArrayList<TypePaiement>();
            	  list.add(new TypePaiement(type.COMPTANT));
            	  list.add(new TypePaiement(type.DEBIT));
            	  list.add(new TypePaiement(type.CREDIT));
            	  WFormComboBox<TypePaiement> comboPaiement = new WFormComboBox<>("M�thode de paiement", list);
            	  final JComponent[] inputs = new JComponent[] {
        			  lblTotal,
        			  lblAmount,
        			  comboPaiement
            	  };
            	  int result = JOptionPane.showConfirmDialog(null, inputs, "Gestionnaire de paiement", JOptionPane.PLAIN_MESSAGE);
            	  if (result == JOptionPane.OK_OPTION) {
            	      System.out.println("Vous avez payez " + lblAmount);
            	  } else {
            	      System.out.println("User canceled / closed the dialog, result = " + result);
            	  }
                break;
              default:
                break;
              }
            }
		});
        
        //TODO assurance, usureJournalier

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

        textFieldDepartKm.events().addListener(textBoxValueChangedListener);
        textFieldNote.events().addListener(textBoxValueChangedListener);

        //TODO vehicule, assurance, usureJournalier, comboboxReservation
    }

    @Override
    public Location get() {
        return new Location(this.comboBoxReservation.getSelected().getReservationId(),
                this.comboBoxReservation.getSelected().getClientReservation(),
                this.comboBoxReservation.getSelected().getClasseReservation(),
                this.comboBoxReservation.getSelected().getStartDate(),
                this.comboBoxReservation.getSelected().getFinDate(),
                this.textFieldNote.getText(),
                this.comboBoxReservation.getSelected().getUtilisateurReservation(),
                this.formLocationID,
                this.comboBoxVehicule.getSelected(),
                UtilisateurDao.retrieve(1), //currentUser
                null, //dateDeRetour
                true, //assurance
                true, //usureJournalire
                0, //essenceManqu
                Integer.parseInt(textFieldDepartKm.getText()),
                0, // retourKm
                this.comboBoxReservation.getSelected().getNoteReservation(),
                0);//estimationReperation
        //TODO assurance, usureJournalier
    }

    @Override
    public void init() {

    }

    public WFormComboBox<Reservation> getComboBoxReservation() {
        return this.comboBoxReservation;
    }

    public WFormComboBox<Vehicule> getComboBoxVehicule() {
        return this.comboBoxVehicule;
    }

    @Override
    public void set(Location listable) {
        Location location = listable;
        this.formLocationID = location.getId();
        this.textFieldNote.setText(location.getNoteLocation());
        this.textFieldDepartKm.setText(Integer.toString(location.getDepartKm()));
        this.comboBoxReservation.setSelected(ReservationDao.retrieve(location.getReservationId()));
        this.comboBoxVehicule.setSelected(location.getVehicule());
        this.hasUnsavedContent = false;
        //TODO assurance, usureJournalier
    }
}
