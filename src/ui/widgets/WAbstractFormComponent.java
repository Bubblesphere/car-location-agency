package ui.widgets;

public abstract class WAbstractFormComponent extends WAbstractFormComponentLayout {
	private static final long serialVersionUID = 1L;
	private WLabel label;
	
	public WAbstractFormComponent(String labelText) {		
		  this.label = new WLabel(labelText);
		  this.add(this.label);
	}
}
