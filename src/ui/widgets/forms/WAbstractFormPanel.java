package ui.widgets.forms;

import javax.swing.JPanel;

import ui.utils.IListable;
import ui.widgets.business.IBusinessForm;

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
