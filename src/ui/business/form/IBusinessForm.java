package ui.business.form;

import ui.utils.IListable;

public interface IBusinessForm<T extends IListable> {
	public abstract T get();
	public abstract void set(T listable);
	public abstract void init();
}
