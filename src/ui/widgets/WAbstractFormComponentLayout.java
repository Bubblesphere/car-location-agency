package ui.widgets;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WAbstractFormComponentLayout extends JPanel {
	private static final long serialVersionUID = 1L;
	private BoxLayout layout;
	
	public WAbstractFormComponentLayout() {
		  this.layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		  this.setBorder(new EmptyBorder(24, 0, 0, 16));
		  this.setLayout(this.layout);
	}
}
