package ui.business.form;

import java.awt.GridBagLayout;

import data.IListable;
import ui.utils.FormBuilder;
import ui.widgets.WAbstractFormPanel;

public class WFormVehicule extends WAbstractFormPanel {
  private GridBagLayout layout;

  public WFormVehicule() {
    this.layout = FormBuilder.getLayout();
    this.setLayout(this.layout);

    // TODO: Implement
  }

  @Override
  public IListable get() {
    // TODO: Implement
    return null;
  }

  @Override
  public void set(IListable listable) {
    // TODO: Implement
  }
}
