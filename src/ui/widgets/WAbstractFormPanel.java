package ui.widgets;

import javax.swing.JPanel;
import data.IListable;

public abstract class WAbstractFormPanel extends JPanel {
	public abstract IListable get();
	public abstract void set(IListable listable);
}
