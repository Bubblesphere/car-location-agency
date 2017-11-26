package ui.widgets;

import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class WFormTextField extends JPanel {
	private BoxLayout layout;
	private WLabel label;
	private JTextField textField;
	
	public WFormTextField(String labelText) {
		this.layout = new BoxLayout(this, BoxLayout.Y_AXIS);
	    this.setBorder(new EmptyBorder(24, 0, 0, 16));
	    this.setLayout(this.layout);

	    this.label = new WLabel(labelText);
	    this.add(this.label);
	    
	    this.textField = new JTextField();
	    this.textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    this.textField.setAlignmentX(this.LEFT_ALIGNMENT);
	    this.add(this.textField);
	}
	
	public String getText() {
		return this.textField.getText();
	}
	
	public void setText(String text) {
		this.textField.setText(text);
	}
}
