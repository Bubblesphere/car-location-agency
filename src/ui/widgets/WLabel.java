package ui.widgets;

import java.awt.Component;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WLabel extends JPanel {
	private BoxLayout layout;
	private JLabel label;

	public WLabel(String labelText) {
		this.layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setBorder(new EmptyBorder(24, 0, 0, 16));
		this.setLayout(this.layout);

		this.label = new JLabel(labelText);
		this.label.setBorder(new EmptyBorder(0, 0, 16, 0));
		this.label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		this.label.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.add(this.label);
	}
}
