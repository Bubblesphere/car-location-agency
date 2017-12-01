package ui.business.tab;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import dao.ClientDao;
import dao.VehiculeDao;
import data.Client;
import data.Vehicule;
import ui.business.form.WFormVehicule;
import ui.events.Event;
import ui.events.EventListener;
import ui.utils.ArrayListHelper;
import ui.widgets.WForm;
import ui.widgets.WFormComboBox;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

public class WTabVehicule extends WSplitPaneTab {

  public WTabVehicule(JTabbedPane tabbedPane) {
    super(tabbedPane, "Véhicule");
    
    ArrayList<Vehicule> vehicules = (ArrayList<Vehicule>)VehiculeDao.retrieveAll();

    WListAdd addListVehicule = new WListAdd(vehicules);

    WFormVehicule formVehicule = new WFormVehicule();
    WForm form = new WForm("Information sur le véhicule", formVehicule);
    form.events().addListener(new ui.events.EventListener() {
      @Override
      public void handleEvent(Event evt) {
        switch ((WForm.Events) evt.getEventName()) {
          case BUTTON_SAVE_CLICK:
            int vehiculeId = addListVehicule.getSelectedIndex();
            Vehicule currentVehicule = (Vehicule) form.get();            
            if(currentVehicule.getId() > 0){
              VehiculeDao.update(currentVehicule);
            }else{
              currentVehicule = VehiculeDao.create(currentVehicule);
            }
            vehicules.set(vehiculeId, currentVehicule);
            addListVehicule.setModel(ArrayListHelper.toDefaultListModel(vehicules));
            form.setHasUnsavedContent(false);
            break;
          default:
            break;
        }
      }
    });
    
    formVehicule.getComboBoxClasse().events().addListener(new EventListener() {      
      @Override
      public void handleEvent(Event evt) {
            switch ((WFormComboBox.Events) evt.getEventName()) {
              case COMBO_BOX_OPENED:
                  
                  break;
              default:
                  break;
            }
      }   
    });

    addListVehicule.events().addListener(new EventListener() {
      @Override
      public void handleEvent(Event evt) {
        switch ((WListAdd.Events) evt.getEventName()) {
          case BUTTON_ADD_CLICKED:
            if(!form.getHasUnsavedContent()){
              Vehicule emptyVehicule = new Vehicule(-1, "Nouveau", "Nouveau");
              addListVehicule.addElement(emptyVehicule);
              form.set(emptyVehicule);
              addListVehicule.setSelectedIndex(vehicules.size() - 1);
              form.setHasUnsavedContent(true);
            }            
            break;
          case LIST_VALUE_CHANGED:
            if (form.getHasUnsavedContent()) {
              int dialogResult = JOptionPane.showConfirmDialog(null,
                  "Vous êtes en train de changer de véhicule, voulez vous-l'enregistrer avant de quitter ?",
                  "Attention", JOptionPane.YES_NO_CANCEL_OPTION);

              if (dialogResult == JOptionPane.YES_OPTION) {
                Vehicule currentVehicule = (Vehicule) form.get();
                if(currentVehicule.getId() == -1){
                  currentVehicule = VehiculeDao.create(currentVehicule);
                  vehicules.set(vehicules.size() - 1, currentVehicule);
                }else{
                  VehiculeDao.update(currentVehicule);
                  Vehicule preUpdateVehicule = (Vehicule) Arrays.asList(vehicules.toArray())
                      .stream().filter(v->((Vehicule) v).getId() == ((Vehicule) form.get()).getId())
                      .findFirst().orElse(null); 
                  vehicules.set(vehicules.indexOf(preUpdateVehicule), currentVehicule);
                }              
                addListVehicule.setModel(ArrayListHelper.toDefaultListModel(vehicules));
                form.setHasUnsavedContent(false);
              } else if (dialogResult == JOptionPane.NO_OPTION) {              
                Vehicule newVehicule = (Vehicule) Arrays.asList(vehicules.toArray())
                    .stream().filter(v->((Vehicule) v).getId() == -1)
                    .findFirst().orElse(null);              
                vehicules.remove(newVehicule);                            
                form.setHasUnsavedContent(false);
              }else{
                break;
              }
            }
            form.set(vehicules.get(addListVehicule.getSelectedIndex()));
            break;
          default:
            break;
        }
      }
    });

    this.setLeftComponent(addListVehicule);
    this.setRightComponent(form);
  }

}
