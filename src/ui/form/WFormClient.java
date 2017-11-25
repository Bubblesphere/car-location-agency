package ui.form;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.widgets.listAdd.WListAdd;

public class WFormClient extends JPanel{
	public WFormClient() {
	    GridBagLayout gbl_panel_2 = new GridBagLayout();
	    gbl_panel_2.columnWidths = new int[]{0, 0};
	    gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	    gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	    gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    this.setLayout(gbl_panel_2);
	    
	    JLabel lblInformationSurLe = new JLabel("Information sur le client");
	    lblInformationSurLe.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    GridBagConstraints gbc_lblInformationSurLe = new GridBagConstraints();
	    gbc_lblInformationSurLe.anchor = GridBagConstraints.LINE_START;
	    gbc_lblInformationSurLe.insets = new Insets(32, 32, 5, 32);
	    gbc_lblInformationSurLe.gridx = 0;
	    gbc_lblInformationSurLe.gridy = 2;
	    this.add(lblInformationSurLe, gbc_lblInformationSurLe);
	    
	    JLabel lblNewLabel = new JLabel("Nom");
	    lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
	    GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	    gbc_lblNewLabel.anchor = GridBagConstraints.LINE_START;
	    gbc_lblNewLabel.insets = new Insets(16, 32, 5, 32);
	    gbc_lblNewLabel.gridx = 0;
	    gbc_lblNewLabel.gridy = 3;
	    this.add(lblNewLabel, gbc_lblNewLabel);
	    
	    JTextField textField = new JTextField();
	    textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    GridBagConstraints gbc_textField = new GridBagConstraints();
	    gbc_textField.anchor = GridBagConstraints.LINE_START;
	    gbc_textField.insets = new Insets(0, 32, 5, 32);
	    gbc_textField.fill = GridBagConstraints.HORIZONTAL;
	    gbc_textField.gridx = 0;
	    gbc_textField.gridy = 4;
	    this.add(textField, gbc_textField);
	    textField.setColumns(10);
	}
}
