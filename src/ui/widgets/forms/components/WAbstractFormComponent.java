package ui.widgets.forms.components;

public abstract class WAbstractFormComponent extends WAbstractFormComponentLayout {
	private static final long serialVersionUID = 1L;
	private WFormLabel label;
	
	public WAbstractFormComponent(String labelText) {		
		  this.label = new WFormLabel(labelText);
		  this.add(this.label);
	}
}
