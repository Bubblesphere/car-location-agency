package ui.business.form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import dao.ReservationDao;
import dao.VehiculeDao;
import data.Location;
import data.Reservation;
import data.Vehicule;
import ui.events.Event;
import ui.events.EventListener;
import ui.utils.FormBuilder;
import ui.widgets.WAbstractFormPanel;
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

        //TODO assurance, usureJournalier

        EventListener textBoxValueChangedListener = new EventListener() {
            @SuppressWarnings("rawtypes")
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
                (Vehicule) this.comboBoxVehicule.getSelected(),
                null, //currentUser
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
        Location location = (Location) listable;
        this.formLocationID = location.getLocationId();
        this.textFieldNote.setText(location.getNoteLocation());
        this.textFieldDepartKm.setText(Integer.toString(location.getDepartKm()));
        this.comboBoxReservation.setSelected(ReservationDao.retrieve(location.getReservationId()));
        this.comboBoxVehicule.setSelected(location.getVehicule());
        //TODO assurance, usureJournalier
    }
}