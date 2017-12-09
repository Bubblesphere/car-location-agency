package ui.business.form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import dao.ReservationDao;
import dao.UtilisateurDao;
import dao.VehiculeDao;
import data.Location;
import data.Reservation;
import data.Vehicule;
import ui.events.Event;
import ui.events.EventEnum.FormTextFieldEvents;
import ui.events.EventListener;
import ui.utils.FormBuilder;
import ui.widgets.WAbstractFormPanel;
import ui.widgets.WFormComboBox;
import ui.widgets.WFormTextField;
import ui.widgets.WLabel;

public class WFormLocation extends WAbstractFormPanel<Location> implements IBusinessForm<Location> {
    private static final long serialVersionUID = 1L;

    private int formLocationID;

    private GridBagLayout layout;

    private WFormComboBox<Reservation> comboBoxReservation;
    private GridBagConstraints gbcReservation;

    private WFormComboBox<Vehicule> comboBoxVehicule;
    private GridBagConstraints gbcVehicule;

    private WFormTextField textFieldDepartKm;
    private GridBagConstraints gbcDepartKm;

    private WFormTextField textFieldNote;
    private GridBagConstraints gbcNote;

    private WLabel paiementsListField;
    private GridBagConstraints gbcPaiements;

    private WFormPayButton buttonPay;
    private GridBagConstraints gbcPay;

    public WFormLocation() {
        this.layout = FormBuilder.getLayout();
        this.setLayout(this.layout);

        this.comboBoxReservation = new WFormComboBox<Reservation>("R\u00E9servation", (ArrayList<Reservation>) ReservationDao.retrieveAll(true));
        this.gbcReservation = FormBuilder.getGBCFullRow();
        this.gbcReservation.gridx = 0;
        this.gbcReservation.gridy = 1;
        this.add(this.comboBoxReservation, this.gbcReservation);

        this.textFieldDepartKm = new WFormTextField("Kilom\u00E9trage avant le d\u00E9part");
        this.gbcDepartKm = FormBuilder.getGBCPartialRow();
        this.gbcDepartKm.gridx = 0;
        this.gbcDepartKm.gridy = 2;
        this.add(this.textFieldDepartKm, this.gbcDepartKm);

        this.comboBoxVehicule = new WFormComboBox<Vehicule>("V\u00E9hicule", (ArrayList<Vehicule>) VehiculeDao.retrieveAll());
        this.gbcVehicule = FormBuilder.getGBCPartialRow();
        this.gbcVehicule.gridx = 1;
        this.gbcVehicule.gridy = 2;
        this.add(this.comboBoxVehicule, this.gbcVehicule);

        this.textFieldNote = new WFormTextField("Note");
        this.gbcNote = FormBuilder.getGBCFullRow();
        this.gbcNote.gridx = 0;
        this.gbcNote.gridy = 4;
        this.add(this.textFieldNote, this.gbcNote);

        this.paiementsListField = new WLabel("<html>Paiements<br /></html>");
        this.gbcPaiements = FormBuilder.getGBCPartialRow();
        this.gbcPaiements.gridx = 0;
        this.gbcPaiements.gridy = 5;
        this.add(this.paiementsListField, this.gbcPaiements);

        this.buttonPay = new WFormPayButton("Payer");
        this.gbcPay = FormBuilder.getGBCFullRow();
        this.gbcPay.gridx = 0;
        this.gbcPay.gridy = 6;
        this.buttonPay.setDisabled(true);
        this.add(this.buttonPay, this.gbcPay);

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
    }

    @Override
    public Location get() {
        int km = 0;
        try {
            km = Integer.parseInt(textFieldDepartKm.getText());
        } catch (NumberFormatException e) {
        }

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
                km,
                0, // retourKm
                this.comboBoxReservation.getSelected().getNoteReservation(),
                0,
                "");//estimationReperation
    }

    @Override
    public void init() {

    }

    public WFormPayButton getButtonPay() {
        return this.buttonPay;
    }

    public WFormComboBox<Reservation> getComboBoxReservation() {
        return this.comboBoxReservation;
    }

    public WFormComboBox<Vehicule> getComboBoxVehicule() {
        return this.comboBoxVehicule;
    }

    public int getFormId() {
        return this.formLocationID;
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
        this.paiementsListField.setText(listable.getPaiementsDisplay());
        if (this.formLocationID == -1) {
            this.buttonPay.setDisabled(true);
        } else {
            this.buttonPay.setDisabled(false);
        }
    }
}
