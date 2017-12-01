package ui.business.form;

import java.awt.GridBagLayout;

import data.Location;
import ui.utils.FormBuilder;
import ui.widgets.WAbstractFormPanel;

public class WFormRetour extends WAbstractFormPanel<Location> {
	private static final long serialVersionUID = 1L;
private GridBagLayout layout;

  public WFormRetour() {
    this.layout = FormBuilder.getLayout();
    this.setLayout(this.layout);

    // TODO: Implement
  }


  @Override
  public void init() {
	  
  }

	@Override
	public void set(Location listable) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public Location get() {
		// TODO Auto-generated method stub
		return null;
	}
}
