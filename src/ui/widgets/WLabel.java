package ui.widgets;

import java.awt.Component;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WLabel extends JPanel {
	private static final long serialVersionUID = 1L;
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
	
	public void setText(String text) {
		this.label.setText(text);
	}
	
	public void setText(Float number) {
		setText(String.valueOf(number));
	}
	
	public String getText() {
		return this.label.getText();
	}
}
