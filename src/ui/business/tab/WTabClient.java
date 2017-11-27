package ui.business.tab;

import data.Client;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import dao.ClientDao;
import ui.business.form.WFormClient;
import ui.events.Event;
import ui.events.EventListener;
import ui.widgets.WForm;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

public class WTabClient extends WSplitPaneTab {

  public WTabClient(JTabbedPane tabbedPane, DefaultListModel<Client> clients) {
    super(tabbedPane, "Client");

    WListAdd addListClient = new WListAdd(clients);

    WForm form = new WForm("Information sur le client", new WFormClient());
    form.events().addListener(new ui.events.EventListener() {
      @Override
      public void handleEvent(Event evt) {
        switch ((WForm.Events) evt.getEventName()) {
        case BUTTON_SAVE_CLICK:
          int clientId = addListClient.getSelectedIndex();
          clients.set(clientId, (Client) form.get());
          Client currentClient = clients.getElementAt(clientId);
          ClientDao.update(currentClient);         
          addListClient.setModel(clients);
          form.setHasUnsavedContent(false);
          
          break;
        default:
          break;
        }
      }
    });    
    
    addListClient.events().addListener(new EventListener() {
      @Override
      public void handleEvent(Event evt) {

        switch ((WListAdd.Events) evt.getEventName()) {
        case BUTTON_ADD_CLICKED:
          if (!form.getHasUnsavedContent()) {
            Client emptyClient = new Client(-1, "Nouveau", "Nouveau");
            addListClient.addElement(emptyClient);
            form.set(emptyClient);            
            addListClient.setSelectedIndex(clients.size() - 1);
            form.setHasUnsavedContent(true);
          }
          break;
        case LIST_VALUE_CHANGED:
          Client selectedClient = clients.getElementAt(addListClient.getSelectedIndex());

          if (form.getHasUnsavedContent()) {
            int dialogResult = JOptionPane.showConfirmDialog(null,
                "Vous êtes en train de changer de client, voulez vous-l'enregistrer avant de quitter ?",
                "Attention", JOptionPane.YES_NO_CANCEL_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
              Client currentClient = (Client) form.get();
              currentClient = ClientDao.create(currentClient);
              clients.set(clients.size() - 1, currentClient);
              addListClient.setModel(clients);
              form.setHasUnsavedContent(false);
            } else if (dialogResult == JOptionPane.NO_OPTION) {
              if(selectedClient.getId() == -1){
                clients.remove(clients.size() - 1);
              }              
              form.setHasUnsavedContent(false);
            }else{
              break;
            }
          }
          form.set(clients.getElementAt(addListClient.getSelectedIndex()));
          break;
        default:
          break;
        }
      }
    });

    this.setLeftComponent(addListClient);
    this.setRightComponent(form);
  }

}
