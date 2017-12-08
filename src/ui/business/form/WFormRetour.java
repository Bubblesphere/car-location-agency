package ui.business.form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.LocationDao;
import data.Location;
import data.Reservation;
import ui.utils.FormBuilder;
import ui.widgets.WAbstractFormPanel;
import ui.widgets.WFormComboBox;
import ui.widgets.WFormTextField;

public class WFormRetour extends WAbstractFormPanel<Location> implements IBusinessForm<Location> {
    private static final long serialVersionUID = 1L;
    private int formLocationID;

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

    public WFormRetour() {
        this.layout = FormBuilder.getLayout();
        this.setLayout(this.layout);

        //TODO Ajouter les champs des valeurs initiales � la location

        this.comboBoxLocation = new WFormComboBox<Location>("Choisir location", (ArrayList<Location>) LocationDao.retrieveAll(true));
        this.gbcLocation = FormBuilder.getGBCPartialRow();
        this.gbcLocation.gridx = 0;
        this.gbcLocation.gridy = 0;
        this.add(this.comboBoxLocation, this.gbcLocation);

        this.textFieldEndDate = new WFormTextField("End date");
        this.textFieldEndDate.setText(LocalDate.now().toString());
        this.gbcEndDate = FormBuilder.getGBCPartialRow();
        this.gbcEndDate.gridx = 1;
        this.gbcEndDate.gridy = 0;
        this.add(this.textFieldEndDate, this.gbcEndDate);

        this.textFieldKMRetour = new WFormTextField("Kilom�trage de retour");
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


    }


    @Override
    public void init() {

    }

    public WFormComboBox<Location> getComboBoxReservation() {
        return this.comboBoxLocation;
    }

    @Override
    public void set(Location listable) {
        // TODO Auto-generated method stub
        Location location = listable;
        if(location != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dtStr = formatter.format(location.getDateDeRetour());

            this.textFieldEndDate.setText(dtStr);
            this.textFieldKMRetour.setText(Integer.toString(location.getRetourKm()));
            this.textFieldNote.setText(location.getNoteRetour());
            this.textFieldEssence.setText(Float.toString(location.getEssenceManquant()));
            this.textFieldDommages.setText(Float.toString(location.getEstimationReparation()));
        }

    }

    @Override
    public Location get() {
        // TODO Auto-generated method stub
		/*return new Location(
                this.formLocationID,
				Float.parseFloat(this.textFieldValue.getText())
		);*/

        return null;
    }
}
