package ui.business.tab;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;

import dao.ParametreDao;
import data.Parametre;
import ui.business.form.WFormParametre;
import ui.events.Event;
import ui.events.EventListener;
import ui.utils.ArrayListHelper;
import ui.widgets.WForm;
import ui.widgets.WList;
import ui.widgets.WSplitPaneTab;

public class WTabParametre extends WSplitPaneTab {

  public WTabParametre(JTabbedPane tabbedPane, ArrayList<Parametre> parametres) {
    super(tabbedPane, "Paramètre");

    WList listParametre = new WList(parametres);

    WForm form = new WForm("Information sur le paramètre", new WFormParametre());
    form.events().addListener(new ui.events.EventListener() {
      @Override
      public void handleEvent(Event evt) {
        switch ((WForm.Events) evt.getEventName()) {
          case BUTTON_SAVE_CLICK:
            int parameterId = listParametre.getSelectedIndex();
            Parametre currentParametre = parametres.get(parameterId);
            Parametre formVals = (Parametre) form.get();
            Parametre newValues = currentParametre;

            newValues.setValeur(formVals.getValeur());

            parametres.set(parameterId, newValues);

            ParametreDao.updateValue(currentParametre.getId(), newValues);
            listParametre.setModel(ArrayListHelper.toDefaultListModel(parametres)); //Pourquoi est-ce que ça refresh avec NULL comme valeur dans la liste? Parce que le form ne contient pas de type?
            break;

          default:
            break;
        }
      }
    });

    listParametre.events().addListener(new EventListener() {
      @Override
      public void handleEvent(Event evt) {
        switch ((WList.Events) evt.getEventName()) {
          case LIST_VALUE_CHANGED:
            form.set(parametres.get(listParametre.getSelectedIndex()));
            break;
          default:
            break;
        }
      }
    });

    this.setLeftComponent(listParametre);
    this.setRightComponent(form);
  }

}
