package ui.widgets;

import javax.swing.JPanel;

import data.IListable;

public abstract class WAbstractFormPanel extends JPanel {
	protected boolean hasUnsavedContent;
	protected void setHasUnsavedContent(boolean hasUnsavedContent) {
		this.hasUnsavedContent = hasUnsavedContent;
	}
	public boolean getHasUnsavedContent() {
		return this.hasUnsavedContent;
	}
	public abstract IListable get();
	public abstract void set(IListable listable);
	public abstract void init();
}
