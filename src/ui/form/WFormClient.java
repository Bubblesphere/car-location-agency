package ui.form;

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
import ui.widgets.utils.Event;
import ui.widgets.utils.EventBubbler;
import ui.widgets.utils.EventListener;

public class WFormClient extends JPanel{
	private EventBubbler events;
	
	private GridBagLayout layout;
	private JLabel lblTitle;
	private GridBagConstraints gbcTitle;
	private WFormTextField textFieldNom;
	private GridBagConstraints gbcNom;
	private WFormTextField textFieldPrenom;
	private GridBagConstraints gbcPrenom;
	private WFormTextField textFieldPermis;
	private GridBagConstraints gbcPermis;
	private WFormButton saveButton;
	private GridBagConstraints gbcSaveButton;
	
	
	public WFormClient() {
		this.events = new EventBubbler(this.listenerList);
		
	    this.setBorder(new EmptyBorder(32, 32, 32, 32));
	    
	    this.layout = new GridBagLayout();
	    this.layout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	    this.layout.columnWeights = new double[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	    this.layout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    this.setLayout(this.layout);

	    this.lblTitle = new JLabel("Information sur le client");
	    this.lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    this.gbcTitle = FormBuilder.getGBCFullRow();
	    this.gbcTitle.gridx = 0;
	    this.gbcTitle.gridy = 0;
	    this.add(this.lblTitle, this.gbcTitle);
	    
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
	    
    	this.saveButton = new WFormButton("Sauvegarder");
    	this.saveButton.events().addListener(new EventListener() {		
			@Override
			public void handleEvent(Event evt) {
				eventHandler("ButtonSaveClick");
			}
		});
    	this.gbcSaveButton = FormBuilder.getGBCPartialRow();
	    this.gbcSaveButton.gridx = 1;
	    this.gbcSaveButton.gridy = 3;
	    this.add(this.saveButton, this.gbcSaveButton);
	}
	
	public Client getClient() {
		// TODO: ID
		return new Client(0, this.textFieldNom.getText(), this.textFieldPrenom.getText());
	}
	
	public void setClient(Client client) {
		this.textFieldNom.setText(client.getNom());
		this.textFieldPrenom.setText(client.getPrenom());
		this.textFieldPermis.setText(client.getNumeroPermis());
	}
	
	public EventBubbler events() {
		return this.events;
	}
	
	private void eventHandler(String command) {
		this.events.fireEvent(new Event(this, command));
	}
}
