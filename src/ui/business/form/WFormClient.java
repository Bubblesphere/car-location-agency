package ui.business.form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import data.Client;
import data.IListable;
import ui.utils.FormBuilder;
import ui.widgets.WAbstractFormPanel;
import ui.widgets.WFormTextField;


// TODO: Are we fetching more fields to populate a client? If so, Client potentially needs new constructor and adjustments are to be made here.

public class WFormClient extends WAbstractFormPanel {
	private int formClientID;
	
	private GridBagLayout layout;
	private WFormTextField textFieldNom;
	private GridBagConstraints gbcNom;
	private WFormTextField textFieldPrenom;
	private GridBagConstraints gbcPrenom;
	private WFormTextField textFieldPermis;
	private GridBagConstraints gbcPermis;
	
	public WFormClient() {
	    this.layout = FormBuilder.getLayout();
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
		return new Client(this.formClientID, this.textFieldNom.getText(), this.textFieldPrenom.getText(), this.textFieldPermis.getText());
	}

	@Override
	public void set(IListable listable) {
		Client client = (Client)listable;
		this.formClientID = client.getId();
		this.textFieldNom.setText(client.getNom());
		this.textFieldPrenom.setText(client.getPrenom());
		this.textFieldPermis.setText(client.getNumeroPermis());
	}
}
