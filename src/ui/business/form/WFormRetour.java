package ui.business.form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dao.LocationDao;
import data.Location;
import data.Paiement;
import ui.utils.FormBuilder;
import ui.widgets.WAbstractFormPanel;
import ui.widgets.WFormComboBox;
import ui.widgets.WFormTextField;
import ui.widgets.WLabel;

public class WFormRetour extends WAbstractFormPanel<Location> implements IBusinessForm<Location> {
    private static final long serialVersionUID = 1L;

    private GridBagLayout layout;

    private WFormComboBox<Location> comboBoxLocation;
    private GridBagConstraints gbcLocation;

    private WFormTextField textFieldEndDate;
    private GridBagConstraints gbcEndDate;

    private WFormTextField textFieldKMRetour;
    private GridBagConstraints gbcKMRetour;

    private WFormTextField textFieldNote;
    private GridBagConstraints gbcNote;

    private WFormTextField textFieldEssence;
    private GridBagConstraints gbcEssence;

    private WFormTextField textFieldDommages;
    private GridBagConstraints gbcDommages;

    private WFormPayButton buttonPay;
    private GridBagConstraints gbcPay;

    //READ-ONLY
    private WLabel dommagesReadOnly;
    private GridBagConstraints gbcDommagesRO;

    private WLabel retourKMReadOnly;
    private GridBagConstraints gbcRetourKMReadOnly;

    private WLabel EndDateReadOnly;
    private GridBagConstraints gbcEndDateReadOnly;

    private WLabel retourNoteReadOnly;
    private GridBagConstraints gbcRetourNoteReadOnly;

    private WLabel retourEssenceReadOnly;
    private GridBagConstraints gbcRetourEssenceReadOnly;

    private WLabel locationReadOnly;
    private GridBagConstraints gbcLocationReadOnly;

    private WLabel departKMReadOnly;
    private GridBagConstraints gbcDepartKMReadOnly;

    private WLabel locationClientReadOnly;
    private GridBagConstraints gbcLocationClientReadOnly;

    private WLabel reservationDebutReadOnly;
    private GridBagConstraints gbcReservationDebutReadOnly;

    private WLabel vehiculeReadOnly;
    private GridBagConstraints gbcVehiculeReadOnly;

    private WLabel listePaiements;
    private GridBagConstraints gbcListePaiements;

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public WFormRetour() {
        this.layout = FormBuilder.getLayout();
        this.setLayout(this.layout);

        //TODO Ajouter les champs des valeurs initiales au moment de la location

        this.comboBoxLocation = new WFormComboBox<Location>("Choisir location", (ArrayList<Location>) LocationDao.retrieveAll(true));
        this.gbcLocation = FormBuilder.getGBCPartialRow();
        this.gbcLocation.gridx = 0;
        this.gbcLocation.gridy = 0;
        this.add(this.comboBoxLocation, this.gbcLocation);

        this.textFieldEndDate = new WFormTextField("End date");

        LocalDateTime now = LocalDateTime.now();

        this.textFieldEndDate.setText(now.format(dateFormatter).toString());
        this.gbcEndDate = FormBuilder.getGBCPartialRow();
        this.gbcEndDate.gridx = 1;
        this.gbcEndDate.gridy = 0;
        this.add(this.textFieldEndDate, this.gbcEndDate);

        this.textFieldKMRetour = new WFormTextField("Kilom\u00E9trage de retour");
        this.gbcKMRetour = FormBuilder.getGBCPartialRow();
        this.gbcKMRetour.gridx = 0;
        this.gbcKMRetour.gridy = 1;
        this.add(this.textFieldKMRetour, this.gbcKMRetour);

        this.textFieldEssence = new WFormTextField("Essence restante");
        this.gbcEssence = FormBuilder.getGBCPartialRow();
        this.gbcEssence.gridx = 1;
        this.gbcEssence.gridy = 1;
        this.add(this.textFieldEssence, this.gbcEssence);

        this.textFieldDommages = new WFormTextField("Dommages");
        this.gbcDommages = FormBuilder.getGBCPartialRow();
        this.gbcDommages.gridx = 0;
        this.gbcDommages.gridy = 2;
        this.add(this.textFieldDommages, this.gbcDommages);

        this.textFieldNote = new WFormTextField("Note");
        this.gbcNote = FormBuilder.getGBCPartialRow();
        this.gbcNote.gridx = 1;
        this.gbcNote.gridy = 2;
        this.add(this.textFieldNote, this.gbcNote);

        this.buttonPay = new WFormPayButton("Payer");
        this.gbcPay = FormBuilder.getGBCFullRow();
        this.gbcPay.gridx = 0;
        this.gbcPay.gridy = 3;
        this.buttonPay.setDisabled(true);
        this.add(this.buttonPay, this.gbcPay);

        // READ-ONLY LABELS
        this.locationReadOnly = new WLabel("Location");
        this.gbcLocationReadOnly = FormBuilder.getGBCPartialRow();
        this.gbcLocationReadOnly.gridx = 0;
        this.gbcLocationReadOnly.gridy = 0;
        this.add(this.locationReadOnly, this.gbcLocationReadOnly);

        this.EndDateReadOnly = new WLabel("Date de retour");
        this.gbcEndDateReadOnly = FormBuilder.getGBCPartialRow();
        this.gbcEndDateReadOnly.gridx = 1;
        this.gbcEndDateReadOnly.gridy = 0;
        this.add(this.EndDateReadOnly, this.gbcEndDateReadOnly);

        this.reservationDebutReadOnly = new WLabel("Date de Debut");
        this.gbcReservationDebutReadOnly = FormBuilder.getGBCPartialRow();
        this.gbcReservationDebutReadOnly.gridx = 0;
        this.gbcReservationDebutReadOnly.gridy = 1;
        this.add(this.reservationDebutReadOnly, this.gbcReservationDebutReadOnly);

        this.retourKMReadOnly = new WLabel("Kilom\u00E9trage de retour");
        this.gbcRetourKMReadOnly = FormBuilder.getGBCPartialRow();
        this.gbcRetourKMReadOnly.gridx = 1;
        this.gbcRetourKMReadOnly.gridy = 1;
        this.add(this.retourKMReadOnly, this.gbcRetourKMReadOnly);

        this.retourEssenceReadOnly = new WLabel("Essence restante");
        this.gbcRetourEssenceReadOnly = FormBuilder.getGBCPartialRow();
        this.gbcRetourEssenceReadOnly.gridx = 0;
        this.gbcRetourEssenceReadOnly.gridy = 2;
        this.add(this.retourEssenceReadOnly, this.gbcRetourEssenceReadOnly);

        this.dommagesReadOnly = new WLabel("Dommages");
        this.gbcDommagesRO = FormBuilder.getGBCPartialRow();
        this.gbcDommagesRO.gridx = 1;
        this.gbcDommagesRO.gridy = 2;
        this.add(this.dommagesReadOnly, this.gbcDommagesRO);

        this.retourNoteReadOnly = new WLabel("Note");
        this.gbcRetourNoteReadOnly = FormBuilder.getGBCPartialRow();
        this.gbcRetourNoteReadOnly.gridx = 0;
        this.gbcRetourNoteReadOnly.gridy = 3;
        this.add(this.retourNoteReadOnly, this.gbcRetourNoteReadOnly);

        this.departKMReadOnly = new WLabel("D\u00E9but");
        this.gbcDepartKMReadOnly = FormBuilder.getGBCPartialRow();
        this.gbcDepartKMReadOnly.gridx = 1;
        this.gbcDepartKMReadOnly.gridy = 3;
        this.add(this.departKMReadOnly, this.gbcDepartKMReadOnly);

        this.locationClientReadOnly = new WLabel("Client");
        this.gbcLocationClientReadOnly = FormBuilder.getGBCPartialRow();
        this.gbcLocationClientReadOnly.gridx = 0;
        this.gbcLocationClientReadOnly.gridy = 4;
        this.add(this.locationClientReadOnly, this.gbcLocationClientReadOnly);


        this.vehiculeReadOnly = new WLabel("V\u00E9hicule");
        this.gbcVehiculeReadOnly = FormBuilder.getGBCPartialRow();
        this.gbcVehiculeReadOnly.gridx = 1;
        this.gbcVehiculeReadOnly.gridy = 4;
        this.add(this.vehiculeReadOnly, this.gbcVehiculeReadOnly);


        this.listePaiements = new WLabel("Paiements effectués");
        this.gbcListePaiements = FormBuilder.getGBCPartialRow();
        this.gbcListePaiements.gridx = 0;
        this.gbcListePaiements.gridy = 5;
        this.add(this.listePaiements, this.gbcListePaiements);

    }


    @Override
    public void init() {
        LocalDateTime now = LocalDateTime.now();

        this.textFieldEndDate.setText(now.format(dateFormatter).toString());
        this.textFieldKMRetour.setText("");
        this.textFieldNote.setText("");
        this.textFieldEssence.setText("");
        this.textFieldDommages.setText("");

    }

    public WFormComboBox<Location> getComboBoxLocation() {
        return this.comboBoxLocation;
    }

    @Override
    public void set(Location listable) {
        Location location = listable;
        if (location != null) {
            String dtStr = dateFormatter.format(location.getDateDeRetour());
            updateReadOnlyValues(location);
            showReadOnlyForm(false);
        } else {
            showReadOnlyForm(true);
        }
    }

    @Override
    public Location get() {
        return LocationDao.retrieve(getComboBoxLocation().getSelected().getId());
    }

    public WFormPayButton getButtonPay() {
        return this.buttonPay;
    }

    public void saveNew() {
        LocalDateTime dateRetour = LocalDateTime.parse(textFieldEndDate.getText(), dateFormatter);

        int locationId = getComboBoxLocation().getSelected().getId();
        Location location = LocationDao.retrieve(locationId);
        location.setDateDeRetour(dateRetour);
        location.setEssenceManquant(Integer.parseInt(textFieldEssence.getText()));
        location.setEstimationReparation(Integer.parseInt(textFieldDommages.getText()));
        location.setRetourKm(Integer.parseInt(textFieldKMRetour.getText()));
        location.setNoteRetour(textFieldNote.getText());

        LocationDao.update(location);
    }

    public void showReadOnlyForm(Boolean hide) {

        comboBoxLocation.setVisible(hide);
        textFieldEndDate.setVisible(hide);
        textFieldKMRetour.setVisible(hide);
        textFieldNote.setVisible(hide);
        textFieldEssence.setVisible(hide);
        textFieldDommages.setVisible(hide);
        buttonPay.setVisible(hide);

        //READ-ONLY labels
        dommagesReadOnly.setVisible(!hide);
        retourKMReadOnly.setVisible(!hide);
        EndDateReadOnly.setVisible(!hide);
        retourNoteReadOnly.setVisible(!hide);
        retourEssenceReadOnly.setVisible(!hide);
        locationReadOnly.setVisible(!hide);
        departKMReadOnly.setVisible(!hide);
        locationClientReadOnly.setVisible(!hide);
        reservationDebutReadOnly.setVisible(!hide);
        vehiculeReadOnly.setVisible(!hide);
        listePaiements.setVisible(!hide);
    }

    public void updateReadOnlyValues(Location location) {
        String dateEndtStr = dateFormatter.format(location.getDateDeRetour());

        this.locationReadOnly.setText("Location #" + location.getId());

        this.textFieldEndDate.setText("Date de retour: " + dateEndtStr);
        this.dommagesReadOnly.setText("Estim\u00E9 des dommages: " + location.getEstimationReparation());
        this.retourKMReadOnly.setText("Km au retour: " + location.getRetourKm());
        this.retourNoteReadOnly.setText("Note: " + location.getNoteRetour());
        this.retourEssenceReadOnly.setText("Essence restante: " + location.getEssenceManquant());

        String dateStartStr = dateFormatter.format(location.getStartDate());

        this.departKMReadOnly.setText(String.valueOf("KM d\u00E9part: " + location.getDepartKm()));
        this.locationClientReadOnly.setText("Client: " + location.getClientReservation().getPrenom() + location.getClientReservation().getNom());
        this.reservationDebutReadOnly.setText("Date de d\u00E9but " + dateStartStr);
        this.vehiculeReadOnly.setText("V\u00E9hicule: " + location.getVehicule().getDisplayedText());
        this.listePaiements.setText(location.getPaiementsDisplay());

    }
}
