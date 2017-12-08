package ui.widgets;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import data.TypePaiement;
import data.TypePaiement.type;
import ui.events.Event;
import ui.events.EventBubbler;
import ui.events.EventListener;
import ui.events.EventEnum.FormButtonEvents;
import ui.events.EventEnum.FormPayButtonEvents;

public class WFormPayButton extends WFormButton {
	private static final long serialVersionUID = 1L;
	private EventBubbler events;
	private JComponent[] inputs;
	
	private float total = 0;
	
	
	public WFormPayButton(String buttonText) {
		super(buttonText);

		this.events = new EventBubbler(this.listenerList);

		  WLabel lblTotal = new WLabel("Total:");
		  WLabel lblAmount = new WLabel("");
		  ArrayList<TypePaiement> list = new ArrayList<TypePaiement>();
		  list.add(new TypePaiement(type.COMPTANT));
		  list.add(new TypePaiement(type.DEBIT));
		  list.add(new TypePaiement(type.CREDIT));
		  WFormComboBox<TypePaiement> comboPaiement = new WFormComboBox<>("M�thode de paiement", list);
		  this.inputs = new  JComponent[] {
				  lblTotal,
				  lblAmount,
				  comboPaiement
		  };
		
	    this.button.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	    	  eventHandler(FormPayButtonEvents.DIALOG_OPENED);
	    	  lblAmount.setText(String.valueOf(total));
	    	  int result = JOptionPane.showConfirmDialog(null, inputs, "Gestionnaire de paiement", JOptionPane.PLAIN_MESSAGE);
	    	  if (result == JOptionPane.OK_OPTION) {
	    		  eventHandler(FormPayButtonEvents.PAYED);
	    	  } else {
	    		  eventHandler(FormPayButtonEvents.DIALOG_CLOSED);
	    	  }
	        }
	      });
	}
	
	public void setTotal(float total) {
		this.total = total;
	}
	
	  public EventBubbler events() {
		    return this.events;
	  }

	  private void eventHandler(FormPayButtonEvents eventName) {
		  this.events.fireEvent(new Event<FormPayButtonEvents>(this, eventName));
	  }
}
