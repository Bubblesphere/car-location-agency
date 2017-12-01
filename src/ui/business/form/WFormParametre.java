package ui.business.form;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.format.DateTimeFormatter;

import data.IListable;
import data.Parametre;
import ui.utils.FormBuilder;
import ui.widgets.WAbstractFormPanel;
import ui.widgets.WFormTextField;

public class WFormParametre extends WAbstractFormPanel {
  private int formParamID;

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
  public IListable get() {
    // TODO: Implement

    return new Parametre(
            this.formParamID,
            Float.parseFloat(this.textFieldValue.getText())
    );
  }

  @Override
  public void set(IListable listable) {
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    Parametre param = (Parametre) listable;

    this.textFieldValue.setText(Float.toString(param.getValeur()));
  }
  
  @Override
  public void init() {
	  
  }
}
