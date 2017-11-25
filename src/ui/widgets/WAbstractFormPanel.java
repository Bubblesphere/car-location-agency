package ui.widgets;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import data.IListable;
import ui.utils.EventBubbler;

public abstract class WAbstractFormPanel extends JPanel {
	public abstract IListable get();
	public abstract void set(IListable listable);
}
