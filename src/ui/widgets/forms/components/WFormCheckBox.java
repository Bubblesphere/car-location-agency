package ui.widgets.forms.components;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JCheckBox;

public class WFormCheckBox extends WAbstractFormComponent {
    private static final long serialVersionUID = 1L;
    private JCheckBox checkBox;

    public WFormCheckBox(String labelText) {
        super(labelText);
        this.checkBox = new JCheckBox(labelText);
        this.checkBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.checkBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(this.checkBox);
    }

    public void setSelected(boolean checked) {
        this.checkBox.setSelected(checked);
    }

    public boolean isSelected() {
        return this.checkBox.isSelected();
    }
}
