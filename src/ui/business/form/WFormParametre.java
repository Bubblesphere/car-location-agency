package ui.business.form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
//import java.time.format.DateTimeFormatter;

import data.Parametre;
import ui.utils.FormBuilder;
import ui.widgets.WAbstractFormPanel;
import ui.widgets.WFormTextField;

public class WFormParametre extends WAbstractFormPanel<Parametre> {
	private static final long serialVersionUID = 1L;

	private int formParamID;
	private String formDescription;

  private GridBagLayout layout;

  private WFormTextField textFieldValue;

  private GridBagConstraints gbcValue;

  public WFormParametre() {
    this.layout = FormBuilder.getLayout();
    this.setLayout(this.layout);

    this.textFieldValue = new WFormTextField("Prix ($)");
    this.gbcValue = FormBuilder.getGBCPartialRow();
    this.gbcValue.gridx = 0;
    this.gbcValue.gridy = 1;
    this.add(this.textFieldValue, this.gbcValue);
  }

  @Override
  public Parametre get() {
    return new Parametre(
            this.formParamID,
            Float.parseFloat(this.textFieldValue.getText()),
            this.formDescription
    );
  }

  
  @Override
  public void init() {
	  this.formParamID = -1;
	  this.formDescription = "";
	  this.textFieldValue.setText("");
  }

@Override
public void set(Parametre listable) {
    this.formParamID = listable.getId();
    this.formDescription = listable.getDescription();
    this.textFieldValue.setText(Float.toString(listable.getValeur()));
}
}
