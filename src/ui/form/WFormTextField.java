package ui.form;

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
	private GridBagConstraints gbcLayout;
	private JLabel label;
	private GridBagConstraints gbcLabel;
	private JTextField textField;
	private GridBagConstraints gbctextField;
	
	
	public WFormTextField(String labelText) {
		this.layout = new BoxLayout(this, BoxLayout.Y_AXIS);
	    this.setBorder(new EmptyBorder(16, 16, 16, 16));
	    this.setLayout(this.layout);

	    this.label = new JLabel(labelText);
	    this.label.setBorder(new EmptyBorder(0, 0, 16, 0));
	    this.label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
	    this.label.setAlignmentX(this.LEFT_ALIGNMENT);
	    this.add(this.label);
	    
	    
	    this.textField = new JTextField(20);
	    this.textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    this.textField.setAlignmentX(this.LEFT_ALIGNMENT);
	    this.add(this.textField);
	    
	}
	
	private void setTopPadding(int topPadding) {
		this.setBorder(new EmptyBorder(topPadding, 0, 0, 0));
	}
}
