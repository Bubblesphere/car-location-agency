package ui.business.tab;

import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import dao.ClientDao;
import dao.VehiculeDao;
import data.Vehicule;
import ui.business.form.WFormVehicule;
import ui.events.Event;
import ui.events.EventListener;
import ui.widgets.WForm;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

public class WTabVehicule extends WSplitPaneTab {

  public WTabVehicule(JTabbedPane tabbedPane, DefaultListModel<Vehicule> vehicules) {
    super(tabbedPane, "Véhicule");

    WListAdd addListVehicule = new WListAdd(vehicules);

    WForm form = new WForm("Information sur le véhicule", new WFormVehicule());
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
            addListVehicule.setModel(vehicules);
            form.setHasUnsavedContent(false);
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
                addListVehicule.setModel(vehicules);
                form.setHasUnsavedContent(false);
              } else if (dialogResult == JOptionPane.NO_OPTION) {              
                Vehicule newVehicule = (Vehicule) Arrays.asList(vehicules.toArray())
                    .stream().filter(v->((Vehicule) v).getId() == -1)
                    .findFirst().orElse(null);              
                vehicules.removeElement(newVehicule);                            
                form.setHasUnsavedContent(false);
              }else{
                break;
              }
            }
            form.set(vehicules.getElementAt(addListVehicule.getSelectedIndex()));
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
