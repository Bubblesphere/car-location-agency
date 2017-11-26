package ui.business.form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import data.Client;
import data.IListable;
import ui.utils.FormBuilder;
import ui.widgets.WAbstractFormPanel;
import ui.widgets.WFormTextField;

public class WFormClient extends WAbstractFormPanel {
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
		// TODO: ID
		return new Client(0, this.textFieldNom.getText(), this.textFieldPrenom.getText());
	}

	@Override
	public void set(IListable listable) {
		this.textFieldNom.setText(((Client)listable).getNom());
		this.textFieldPrenom.setText(((Client)listable).getPrenom());
		this.textFieldPermis.setText(((Client)listable).getNumeroPermis());
	}
}
