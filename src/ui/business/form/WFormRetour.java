package ui.business.form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.LocationDao;
import data.Location;
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


        // READ-ONLY LABELS
        this.locationReadOnly = new WLabel("Location");
        this.gbcLocationReadOnly = FormBuilder.getGBCPartialRow();
        this.gbcLocationReadOnly.gridx = 0;
        this.gbcLocationReadOnly.gridy = 0;
        this.add(this.locationReadOnly, this.gbcLocationReadOnly);

        this.EndDateReadOnly = new WLabel("End date");
        this.gbcEndDateReadOnly = FormBuilder.getGBCPartialRow();
        this.gbcEndDateReadOnly.gridx = 1;
        this.gbcEndDateReadOnly.gridy = 0;
        this.add(this.EndDateReadOnly, this.gbcEndDateReadOnly);

        this.retourKMReadOnly = new WLabel("Kilom\u00E9trage de retour");
        this.gbcRetourKMReadOnly = FormBuilder.getGBCPartialRow();
        this.gbcRetourKMReadOnly.gridx = 0;
        this.gbcRetourKMReadOnly.gridy = 1;
        this.add(this.retourKMReadOnly, this.gbcRetourKMReadOnly);

        this.retourEssenceReadOnly = new WLabel("Essence restante");
        this.gbcRetourEssenceReadOnly = FormBuilder.getGBCPartialRow();
        this.gbcRetourEssenceReadOnly.gridx = 1;
        this.gbcRetourEssenceReadOnly.gridy = 1;
        this.add(this.retourEssenceReadOnly, this.gbcRetourEssenceReadOnly);

        this.dommagesReadOnly = new WLabel("Dommages");
        this.gbcDommagesRO = FormBuilder.getGBCPartialRow();
        this.gbcDommagesRO.gridx = 0;
        this.gbcDommagesRO.gridy = 2;
        this.add(this.dommagesReadOnly, this.gbcDommagesRO);

        this.retourNoteReadOnly = new WLabel("Note");
        this.gbcRetourNoteReadOnly = FormBuilder.getGBCPartialRow();
        this.gbcRetourNoteReadOnly.gridx = 1;
        this.gbcRetourNoteReadOnly.gridy = 2;
        this.add(this.retourNoteReadOnly, this.gbcRetourNoteReadOnly);
    }


    @Override
    public void init() {

    }

    public WFormComboBox<Location> getComboBoxLocation() {
        return this.comboBoxLocation;
    }

    @Override
    public void set(Location listable) {
        Location location = listable;
        if(location != null){
            String dtStr = dateFormatter.format(location.getDateDeRetour());
            updateReadOnlyValues(location);
            showReadOnlyForm(false);
        }else{
            showReadOnlyForm(false);
        }
    }

    @Override
    public Location get() {
		/*return new Location(
                this.formLocationID,
				Float.parseFloat(this.textFieldValue.getText())
		);*/

        return null;
    }

    public void saveNew(){
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

    public void showReadOnlyForm(Boolean hide){

        comboBoxLocation.setVisible(hide);
        textFieldEndDate.setVisible(hide);
        textFieldKMRetour.setVisible(hide);
        textFieldNote.setVisible(hide);
        textFieldEssence.setVisible(hide);
        textFieldDommages.setVisible(hide);

        //READ-ONLY labels
        dommagesReadOnly.setVisible(!hide);
        retourKMReadOnly.setVisible(!hide);
        EndDateReadOnly.setVisible(!hide);
        retourNoteReadOnly.setVisible(!hide);
        retourEssenceReadOnly.setVisible(!hide);
        locationReadOnly.setVisible(!hide);
    }

    public void updateReadOnlyValues(Location location){
        String dtStr = dateFormatter.format(location.getDateDeRetour());

        this.locationReadOnly.setText("Location #" + location.getId());

        this.textFieldEndDate.setText("Date de retour: " + dtStr);
        this.dommagesReadOnly.setText("Estim\u00E9 des dommages: " + location.getEstimationReparation());
        this.retourKMReadOnly.setText("Km au retour: " + location.getRetourKm());
        this.retourNoteReadOnly.setText("Note: " + location.getNoteRetour());
        this.retourEssenceReadOnly.setText("Essence restante: " + location.getEssenceManquant());

    }
}
