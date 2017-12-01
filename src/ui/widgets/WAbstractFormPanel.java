package ui.widgets;

import javax.swing.JPanel;

import data.IListable;

public abstract class WAbstractFormPanel<T extends IListable> extends JPanel {
	private static final long serialVersionUID = 1L;
	protected boolean hasUnsavedContent;
	protected void setHasUnsavedContent(boolean hasUnsavedContent) {
		this.hasUnsavedContent = hasUnsavedContent;
	}
	public boolean getHasUnsavedContent() {
		return this.hasUnsavedContent;
	}
	public abstract T get();
	public abstract void set(T listable);
	public abstract void init();
}
