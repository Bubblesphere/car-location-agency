package ui.business.tab;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import dao.ClientDao;
import data.Client;
import ui.business.form.WFormClient;
import ui.events.Event;
import ui.events.EventListener;
import ui.utils.ArrayListHelper;
import ui.widgets.WForm;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

public class WTabClient extends WSplitPaneTab {
	private static final long serialVersionUID = 1L;
	private WForm<Client> form;

  public WTabClient(JTabbedPane tabbedPane, ArrayList<Client> clients) {
    super(tabbedPane, "Client");

    WListAdd<Client> addListClient = new WListAdd<Client>(clients);

    WFormClient formClient = new WFormClient();
    this.form = new WForm<Client>("Information sur le client", formClient);
    this.form.events().addListener(new ui.events.EventListener() {
    	@SuppressWarnings("rawtypes") 
      @Override
      public void handleEvent(Event evt) {
        switch ((WForm.Events) evt.getEventName()) {
        case BUTTON_SAVE_CLICK:
          int clientId = addListClient.getSelectedIndex();          
          Client currentClient = (Client) form.get();
          if(currentClient.getId() > 0){
            ClientDao.update(currentClient);
          }else{
            currentClient = ClientDao.create(currentClient);
          }       
          clients.set(clientId, currentClient);
          addListClient.setModel(ArrayListHelper.toDefaultListModel(clients));
          form.setHasUnsavedContent(false);          
          break;
        default:
          break;
        }
      }
    }); 
    
    addListClient.events().addListener(new EventListener() {
    	@SuppressWarnings("rawtypes") 
      @Override
      public void handleEvent(Event evt) {

        switch ((WListAdd.Events) evt.getEventName()) {
        case BUTTON_ADD_CLICKED:
          if (!form.getHasUnsavedContent()) {
            Client emptyClient = new Client(-1, "Nouveau", "Nouveau");
            emptyClient.setDateDeNaissance(LocalDate.now());
            addListClient.addElement(emptyClient);
            form.set(emptyClient);            
            clients.add(emptyClient);
            addListClient.setSelectedIndex(clients.size() - 1);
            form.setHasUnsavedContent(true);
          }
          break;
        case LIST_VALUE_CHANGED:
          //Client selectedClient = clients.get(addListClient.getSelectedIndex());

          if (form.getHasUnsavedContent()) {
            int dialogResult = JOptionPane.showConfirmDialog(null,
                "Vous �tes en train de changer de client, voulez vous-l'enregistrer avant de quitter ?",
                "Attention", JOptionPane.YES_NO_CANCEL_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
              Client currentClient = (Client) form.get();
              if(currentClient.getId() == -1){
                currentClient = ClientDao.create(currentClient);
                clients.set(clients.size() - 1, currentClient);
              }else{
                ClientDao.update(currentClient);
                Client preUpdateClient = (Client) Arrays.asList(clients.toArray())
                    .stream().filter(c->((Client) c).getId() == ((Client) form.get()).getId())
                    .findFirst().orElse(null); 
                clients.set(clients.indexOf(preUpdateClient), currentClient);
              }              
              addListClient.setModel(ArrayListHelper.toDefaultListModel(clients));
              form.setHasUnsavedContent(false);
            } else if (dialogResult == JOptionPane.NO_OPTION) {              
              Client newClient = (Client) Arrays.asList(clients.toArray())
                  .stream().filter(c->((Client) c).getId() == -1)
                  .findFirst().orElse(null);              
              clients.remove(newClient);                            
              form.setHasUnsavedContent(false);
            }else{
              break;
            }
          }
          form.set(clients.get(addListClient.getSelectedIndex()));
          break;
        default:
          break;
        }
      }
    });

    this.setLeftComponent(addListClient);
    this.setRightComponent(form);
  }

  public WForm<Client> getForm() {
	  return this.form;
  }
}
