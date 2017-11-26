package ui.business.tab;

import data.Client;
import javax.swing.DefaultListModel;
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
          // TODO: Add Empty to db and return the new client
          Client clientReturnedFromCreationWithinDb = new Client(3, "Dallaire", "Deric", "DSAD12");
          addListClient.addElement(clientReturnedFromCreationWithinDb);
          break;
        case LIST_VALUE_CHANGED:
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
