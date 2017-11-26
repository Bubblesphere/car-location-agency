package ui.business.form;

import java.awt.GridBagLayout;
import data.IListable;

import java.awt.GridBagConstraints;
import java.time.format.DateTimeFormatter;

import data.Parametre;
import ui.utils.FormBuilder;
import ui.widgets.WAbstractFormPanel;
import ui.widgets.WFormTextField;
import ui.widgets.WLabel;

public class WFormParametre extends WAbstractFormPanel {
  private int formParamID;

  private GridBagLayout layout;

  private WLabel labelId;

  private GridBagConstraints gbcId;
  private WFormTextField textFieldType;
  private WFormTextField textFieldDescription;

  private WFormTextField textFieldValue;
  private WFormTextField textFieldStartDate;
  private WFormTextField textFieldEndDate;

  private GridBagConstraints gbcType;
  private GridBagConstraints gbcDescription;
  private GridBagConstraints gbcValue;
  private GridBagConstraints gbcStartDate;
  private GridBagConstraints gbcEndDate;

  public WFormParametre() {
    this.layout = FormBuilder.getLayout();
    this.setLayout(this.layout);

    this.labelId = new WLabel("#" + this.formParamID);
    this.gbcId = FormBuilder.getGBCPartialRow();
    this.gbcId.gridx = 0;
    this.gbcId.gridy = 0;
    this.add(this.labelId, this.gbcId);

    this.textFieldValue = new WFormTextField("Prix ($)");
    this.gbcValue = FormBuilder.getGBCPartialRow();
    this.gbcValue.gridx = 0;
    this.gbcValue.gridy = 1;
    this.add(this.textFieldValue, this.gbcValue);

    this.textFieldType = new WFormTextField("Type");
    this.gbcType = FormBuilder.getGBCPartialRow();
    this.gbcType.gridx = 0;
    this.gbcType.gridy = 2;
    this.add(this.textFieldType, this.gbcType);

    this.textFieldDescription = new WFormTextField("Description");
    this.gbcDescription = FormBuilder.getGBCPartialRow();
    this.gbcDescription.gridx = 0;
    this.gbcDescription.gridy = 3;
    this.add(this.textFieldDescription, this.gbcDescription);

    this.textFieldStartDate = new WFormTextField("Date de mise en vigueur");
    this.gbcStartDate = FormBuilder.getGBCPartialRow();
    this.gbcStartDate.gridx = 0;
    this.gbcStartDate.gridy = 4;
    this.add(this.textFieldStartDate, this.gbcStartDate);

    this.textFieldEndDate = new WFormTextField("Date de fin");
    this.gbcEndDate = FormBuilder.getGBCPartialRow();
    this.gbcEndDate.gridx = 1;
    this.gbcEndDate.gridy = 4;
    this.add(this.textFieldEndDate, this.gbcEndDate);
  }

  @Override
  public IListable get() {
    // TODO: Implement
    //return new Parametre(this.formParamID, this.textFieldValue.getText(), this.formParamID);
    return null;
  }

  @Override
  public void set(IListable listable) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");

    Parametre param = (Parametre) listable;
    this.formParamID = param.getId();
    this.textFieldType.setText(Integer.toString(param.getTypeId()));
    this.textFieldDescription.setText(param.getDescription());
    this.textFieldValue.setText(Float.toString(param.getValeur()));
    if(param.getDateDebut() != null) this.textFieldStartDate.setText(param.getDateDebut().format(formatter));
    if(param.getDateFin() != null) this.textFieldEndDate.setText(param.getDateFin().format(formatter));
  }
}
