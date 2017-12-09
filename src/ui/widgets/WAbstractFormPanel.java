package ui.widgets;

import javax.swing.JPanel;

import ui.business.form.IBusinessForm;
import ui.utils.IListable;

public abstract class WAbstractFormPanel<T extends IListable> extends JPanel implements IBusinessForm<T> {
	private static final long serialVersionUID = 1L;
	protected boolean hasUnsavedContent;
	protected void setHasUnsavedContent(boolean hasUnsavedContent) {
		this.hasUnsavedContent = hasUnsavedContent;
	}
	public boolean getHasUnsavedContent() {
		return this.hasUnsavedContent;
	}
}
