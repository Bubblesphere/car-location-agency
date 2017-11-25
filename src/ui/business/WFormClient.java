package ui.business;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Client;
import data.IListable;
import ui.utils.Event;
import ui.utils.EventBubbler;
import ui.utils.EventListener;
import ui.utils.FormBuilder;
import ui.widgets.WAbstractFormPanel;
import ui.widgets.WFormButton;
import ui.widgets.WFormTextField;

public class WFormClient extends WAbstractFormPanel {
	private EventBubbler events;
	
	private GridBagLayout layout;
	private WFormTextField textFieldNom;
	private GridBagConstraints gbcNom;
	private WFormTextField textFieldPrenom;
	private GridBagConstraints gbcPrenom;
	private WFormTextField textFieldPermis;
	private GridBagConstraints gbcPermis;
	
	public WFormClient() {
		this.events = new EventBubbler(this.listenerList);
	    
	    this.layout = new GridBagLayout();
	    this.layout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	    this.layout.columnWeights = new double[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	    this.layout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
	    
	    this.textFieldPermis = new WFormTextField("Permis de conduire");
	    this.gbcPermis = FormBuilder.getGBCFullRow();
	    this.gbcPermis.gridx = 0;
	    this.gbcPermis.gridy = 2;
	    this.add(this.textFieldPermis, this.gbcPermis);
	}
	
	@Override
	public IListable get() {
		// TODO: ID
		return new Client(0, this.textFieldNom.getText(), this.textFieldPrenom.getText());
	}

	@Override
	public void set(IListable listable) {
		this.textFieldNom.setText(((Client)listable).getNom());
		this.textFieldPrenom.setText(((Client)listable).getPrenom());
		this.textFieldPermis.setText(((Client)listable).getNumeroPermis());
	}
	
	public EventBubbler events() {
		return this.events;
	}
	
	private void eventHandler(String command) {
		this.events.fireEvent(new Event(this, command));
	}


}
