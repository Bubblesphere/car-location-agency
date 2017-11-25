package ui.utils;

import java.awt.GridBagConstraints;

public class FormBuilder {
	public static GridBagConstraints getGBCPartialRow() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		return gbc;
	}
	
	public static GridBagConstraints getGBCFullRow() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		return gbc;
	}
}
