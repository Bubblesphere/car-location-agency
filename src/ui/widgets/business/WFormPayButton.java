package ui.widgets.business;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import data.Paiement;
import data.TypePaiement;
import data.TypePaiement.type;
import ui.events.Event;
import ui.events.EventBubbler;
import ui.events.EventEnum.FormPayButtonEvents;
import ui.widgets.forms.components.WFormButton;
import ui.widgets.forms.components.WFormComboBox;
import ui.widgets.forms.components.WFormLabel;

public class WFormPayButton extends WFormButton implements IBusinessForm<Paiement> {
    private static final long serialVersionUID = 1L;
    private EventBubbler events;
    private WFormLabel lblTotal;
    private WFormLabel lblAmount;
    private WFormComboBox<TypePaiement> comboPaiement;

    private int payId;


    public WFormPayButton(String buttonText) {
        super(buttonText);

        this.events = new EventBubbler(this.listenerList);

        this.lblTotal = new WFormLabel("Total:");
        this.lblAmount = new WFormLabel("");
        ArrayList<TypePaiement> list = new ArrayList<TypePaiement>();
        list.add(new TypePaiement(type.COMPTANT));
        list.add(new TypePaiement(type.DEBIT));
        list.add(new TypePaiement(type.CREDIT));
        this.comboPaiement = new WFormComboBox<>("M\u00E9thode de paiement", list);

        for (MouseListener l : this.button.getMouseListeners()) {
            this.button.removeMouseListener(l);
        }

        this.button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eventHandler(FormPayButtonEvents.DIALOG_OPENED);
                int result = JOptionPane.showConfirmDialog(null, new JComponent[]{
                        lblTotal,
                        lblAmount,
                        comboPaiement
                }, "Gestionnaire de paiement", JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    eventHandler(FormPayButtonEvents.PAYED);
                } else {
                    eventHandler(FormPayButtonEvents.DIALOG_CLOSED);
                }
            }
        });
    }

    public EventBubbler events() {
        return this.events;
    }

    private void eventHandler(FormPayButtonEvents eventName) {
        this.events.fireEvent(new Event<FormPayButtonEvents>(this, eventName));
    }

    @Override
    public Paiement get() {
        return new Paiement(this.payId, Float.valueOf(this.lblAmount.getText()), this.comboPaiement.getSelected().getId(), "");
    }

    @Override
    public void set(Paiement listable) {
        this.payId = listable.getLocationId();
        this.lblAmount.setText(listable.getMontant());
        this.comboPaiement.setSelected(new TypePaiement(listable.getId()));
    }

    @Override
    public void init() {
        this.payId = -1;
        this.lblAmount.setText("");
        this.comboPaiement.setSelected(new TypePaiement(0));
    }
}
