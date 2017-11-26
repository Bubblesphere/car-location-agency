package ui.business.form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;

import dao.ClientDao;
import data.Client;
import data.IListable;
import ui.events.Event;
import ui.events.EventListener;
import ui.utils.FormBuilder;
import ui.widgets.WAbstractFormPanel;
import ui.widgets.WForm;
import ui.widgets.WFormTextField;

public class WFormClient extends WAbstractFormPanel {
  private int formClientID;

  private GridBagLayout layout;
  
  private WFormTextField textFieldNom;
  private GridBagConstraints gbcNom;
  
  private WFormTextField textFieldPrenom;
  private GridBagConstraints gbcPrenom;
  
  private WFormTextField textFieldPermis;
  private GridBagConstraints gbcPermis;
  
  private WFormTextField textFieldAdresse;
  private GridBagConstraints gbcAdresse;
  
  private WFormTextField textFieldNumeroTelephone;
  private GridBagConstraints gbcNumeroTelephone;
  
  private WFormTextField textFieldCourriel;
  private GridBagConstraints gbcCourriel;
  
  private WFormTextField textFieldNote;
  private GridBagConstraints gbcNote;
  
  // TODO: dateDeNaissance

  public WFormClient() {
    this.layout = FormBuilder.getLayout();
    this.setLayout(this.layout);

    this.textFieldNom = new WFormTextField("Nom");
    this.gbcNom = FormBuilder.getGBCPartialRow();
    this.gbcNom.gridx = 0;
    this.gbcNom.gridy = 1;
    this.add(this.textFieldNom, this.gbcNom);

    this.textFieldPrenom = new WFormTextField("Prenom");
    this.gbcPrenom = FormBuilder.getGBCPartialRow();
    this.gbcPrenom.gridx = 1;
    this.gbcPrenom.gridy = 1;
    this.add(this.textFieldPrenom, this.gbcPrenom);
    
    this.textFieldCourriel = new WFormTextField("Courriel");
    this.gbcCourriel = FormBuilder.getGBCPartialRow();
    this.gbcCourriel.gridx = 0;
    this.gbcCourriel.gridy = 2;
    this.add(this.textFieldCourriel, this.gbcCourriel);
    
    this.textFieldNumeroTelephone = new WFormTextField("Num\u00E9ro de t\u00E9l\u00E9phone");
    this.gbcNumeroTelephone = FormBuilder.getGBCPartialRow();
    this.gbcNumeroTelephone.gridx = 1;
    this.gbcNumeroTelephone.gridy = 2;
    this.add(this.textFieldNumeroTelephone, this.gbcNumeroTelephone);

    this.textFieldPermis = new WFormTextField("Permis de conduire");
    this.gbcPermis = FormBuilder.getGBCFullRow();
    this.gbcPermis.gridx = 0;
    this.gbcPermis.gridy = 3;
    this.add(this.textFieldPermis, this.gbcPermis);
    
    this.textFieldAdresse = new WFormTextField("Adresse");
    this.gbcAdresse = FormBuilder.getGBCFullRow();
    this.gbcAdresse.gridx = 0;
    this.gbcAdresse.gridy = 4;
    this.add(this.textFieldAdresse, this.gbcAdresse);    
    
    this.textFieldNote = new WFormTextField("Note");
    this.gbcNote = FormBuilder.getGBCFullRow();
    this.gbcNote.gridx = 0;
    this.gbcNote.gridy = 5;
    this.add(this.textFieldNote, this.gbcNote); 

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
    
    textFieldNom.events().addListener(textBoxValueChangedListener);
    textFieldPrenom.events().addListener(textBoxValueChangedListener);
    textFieldCourriel.events().addListener(textBoxValueChangedListener);
    textFieldNumeroTelephone.events().addListener(textBoxValueChangedListener);
    textFieldPermis.events().addListener(textBoxValueChangedListener);
    textFieldAdresse.events().addListener(textBoxValueChangedListener);
    textFieldNote.events().addListener(textBoxValueChangedListener);
  }
  
  
  
  @Override
  public IListable get() {
    return new Client(this.textFieldNom.getText(),
        this.textFieldPrenom.getText(),
        this.formClientID, 
        this.textFieldAdresse.getText(),
        this.textFieldPermis.getText(), 
        this.textFieldNumeroTelephone.getText(), 
        this.textFieldCourriel.getText(), 
        this.textFieldNote.getText(), 
        LocalDate.parse("2017-11-13"));
 // TODO: dateDeNaissance
  }

  @Override
  public void set(IListable listable) {
    Client client = (Client) listable;
    this.formClientID = client.getId();
    this.textFieldNom.setText(client.getNom());
    this.textFieldPrenom.setText(client.getPrenom());
    this.textFieldPermis.setText(client.getNumeroPermis());
    this.textFieldAdresse.setText(client.getAdresse());
    this.textFieldNumeroTelephone.setText(client.getNumeoTelphone());
    this.textFieldCourriel.setText(client.getCourriel());
    this.textFieldNote.setText(client.getNote());
    this.hasUnsavedContent = false;
 // TODO: dateDeNaissance
  }
}
