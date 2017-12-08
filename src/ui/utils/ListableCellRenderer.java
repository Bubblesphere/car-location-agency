package ui.utils;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class ListableCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

@Override
public Component getListCellRendererComponent(JList<?> list, Object value, int index,
      boolean isSelected, boolean cellHasFocus) {
    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

    IListable object = (IListable) value;
    if (object != null)
    setText(object.getDisplayedText());

    return this;
  }
}
