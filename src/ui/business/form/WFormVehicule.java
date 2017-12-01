package ui.business.form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import dao.ClasseDao;
import dao.ClientDao;
import data.Classe;
import data.IListable;
import data.Vehicule;
import ui.events.Event;
import ui.events.EventListener;
import ui.utils.FormBuilder;
import ui.widgets.WAbstractFormPanel;
import ui.widgets.WFormComboBox;
import ui.widgets.WFormTextField;

public class WFormVehicule extends WAbstractFormPanel {
  private int formvehiculeId;
  
  private GridBagLayout layout;
  
  private WFormTextField textFieldFabricant;
  private GridBagConstraints gbcFabricant;
  
  private WFormTextField textFieldMarque;
  private GridBagConstraints gbcMarque;
  
  private WFormTextField textFieldAnnee;
  private GridBagConstraints gbcAnnee;
  
  private WFormTextField textFieldKilometrage;
  private GridBagConstraints gbcKilometrage;
  
  private WFormTextField textFieldPlaque;
  private GridBagConstraints gbcPlaque;
  
  private WFormTextField textFieldCapaciteEssence;
  private GridBagConstraints gbcCapaciteEssence;
  
  private WFormTextField textFieldNote;
  private GridBagConstraints gbcNote; 
  
  private WFormComboBox comboBoxClasse;
  private GridBagConstraints gbcClasse;

  
  // TODO: int etat, Boolean desactive
  
  public WFormVehicule() {
   
    this.layout = FormBuilder.getLayout();
    this.setLayout(this.layout);
    
    this.textFieldFabricant = new WFormTextField("Fabricant");
    this.gbcFabricant = FormBuilder.getGBCPartialRow();
    this.gbcFabricant.gridx = 0;
    this.gbcFabricant.gridy = 1;    
    this.add(this.textFieldFabricant, this.gbcFabricant);
    
    this.textFieldMarque = new WFormTextField("Marque");
    this.gbcMarque = FormBuilder.getGBCPartialRow();
    this.gbcMarque.gridx = 1;
    this.gbcMarque.gridy = 1;    
    this.add(this.textFieldMarque, this.gbcMarque);
    
    this.textFieldAnnee = new WFormTextField("Ann\u00E9e");
    this.gbcAnnee = FormBuilder.getGBCPartialRow();
    this.gbcAnnee.gridx = 0;
    this.gbcAnnee.gridy = 2;
    this.add(this.textFieldAnnee, this.gbcAnnee);
    
    this.textFieldKilometrage = new WFormTextField("Kilom\u00E9trage");
    this.gbcKilometrage = FormBuilder.getGBCPartialRow();
    this.gbcKilometrage.gridx = 1;
    this.gbcKilometrage.gridy = 2;
    this.add(this.textFieldKilometrage, this.gbcKilometrage);
    
    this.textFieldPlaque = new WFormTextField("Plaque");
    this.gbcPlaque = FormBuilder.getGBCPartialRow();
    this.gbcPlaque.gridx = 0;
    this.gbcPlaque.gridy = 3;
    this.add(this.textFieldPlaque, this.gbcPlaque);
    
    this.textFieldCapaciteEssence = new WFormTextField("Capacit\u00E9 d'essence");
    this.gbcCapaciteEssence = FormBuilder.getGBCPartialRow();
    this.gbcCapaciteEssence.gridx = 1;
    this.gbcCapaciteEssence.gridy = 3;
    this.add(this.textFieldCapaciteEssence, this.gbcCapaciteEssence);
    
    this.textFieldNote = new WFormTextField("Note");
    this.gbcNote = FormBuilder.getGBCPartialRow();
    this.gbcNote.gridx = 0;
    this.gbcNote.gridy = 4;
    this.add(this.textFieldNote, this.gbcNote);
    
    this.comboBoxClasse = new WFormComboBox("Classe", (ArrayList<? extends IListable>) ClasseDao.retrieveAll());
    this.gbcClasse = FormBuilder.getGBCPartialRow();
    this.gbcClasse.gridx = 1;
    this.gbcClasse.gridy = 4;
    this.add(this.comboBoxClasse, this.gbcClasse); 
    // TODO: int etat, Boolean desactive
  }
  
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

  @Override
  public IListable get() {   
 // TODO: int etat, Boolean desactive
    return new Vehicule(this.formvehiculeId, (Classe)this.comboBoxClasse.getSelected(), this.textFieldFabricant.getText(), 
        this.textFieldMarque.getText(), Integer.parseInt(this.textFieldAnnee.getText()), 
        Integer.parseInt(this.textFieldKilometrage.getText()), 1, 
        this.textFieldPlaque.getText(), false, 
        Integer.parseInt(this.textFieldCapaciteEssence.getText()), this.textFieldNote.getText());
  }
  
  @Override
  public void init() {
	  
  }

  @Override
  public void set(IListable listable) {    
    Vehicule vehicule = (Vehicule) listable;
    this.formvehiculeId = vehicule.getId();
    textFieldFabricant.setText(vehicule.getFabricant());
    textFieldMarque.setText(vehicule.getMarque());
    textFieldAnnee.setText(Integer.toString(vehicule.getAnnee()));
    textFieldKilometrage.setText(Integer.toString(vehicule.getKilometrage()));
    textFieldPlaque.setText(vehicule.getPlaque());
    textFieldCapaciteEssence.setText(Integer.toString(vehicule.getCapaciteEssence()));
    textFieldNote.setText(vehicule.getNote());
    comboBoxClasse.setSelected(vehicule.getVClasse());
 // TODO: int etat, Boolean desactive
  }
  
  public WFormComboBox getComboBoxClasse(){
    return this.comboBoxClasse;
  }
}
