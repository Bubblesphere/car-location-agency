package ui.widgets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class WFormTextField extends JPanel {
	private BoxLayout layout;
	private JLabel label;
	private JTextField textField;
	
	public WFormTextField(String labelText) {
		this.layout = new BoxLayout(this, BoxLayout.Y_AXIS);
	    this.setBorder(new EmptyBorder(24, 0, 0, 16));
	    this.setLayout(this.layout);

	    this.label = new JLabel(labelText);
	    this.label.setBorder(new EmptyBorder(0, 0, 16, 0));
	    this.label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
	    this.label.setAlignmentX(this.LEFT_ALIGNMENT);
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
