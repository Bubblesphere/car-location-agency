package ui.form;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ui.widgets.listAdd.WListAdd;

public class WFormClient extends JPanel{
	public WFormClient() {
	    GridBagLayout gbl_panel_2 = new GridBagLayout();
	    
	    gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	    gbl_panel_2.columnWeights = new double[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	    gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    
	    this.setLayout(gbl_panel_2);
	    this.setBorder(new EmptyBorder(32, 32, 32, 32));
	    
	    JLabel lblInformationSurLe = new JLabel("Information sur le client");
	    lblInformationSurLe.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    GridBagConstraints gbc_lblInformationSurLe = new GridBagConstraints();
	    gbc_lblInformationSurLe.fill = GridBagConstraints.HORIZONTAL;
	    gbc_lblInformationSurLe.anchor = GridBagConstraints.LINE_START;
	    gbc_lblInformationSurLe.gridx = 0;
	    gbc_lblInformationSurLe.gridy = 0;
	    this.add(lblInformationSurLe, gbc_lblInformationSurLe);
	    
	    WFormTextField textFieldName = new WFormTextField("Nom");
	    GridBagConstraints gbcLayout = new GridBagConstraints();
	    gbcLayout.anchor = GridBagConstraints.LINE_START;
	    gbcLayout.fill = GridBagConstraints.HORIZONTAL;
	    gbcLayout.gridx = 0;
	    gbcLayout.gridy = 1;
	    this.add(textFieldName, gbcLayout);
	   
	    
	    WFormTextField textFieldName2 = new WFormTextField("Prenom");
	    GridBagConstraints gbcLayout2 = new GridBagConstraints();
	    gbcLayout2.anchor = GridBagConstraints.LINE_START;
	    gbcLayout2.fill = GridBagConstraints.HORIZONTAL;
	    gbcLayout2.gridx = 1;
	    gbcLayout2.gridy = 1;
	    this.add(textFieldName2, gbcLayout2);
	    
	    WFormTextField textFieldName3 = new WFormTextField("Permis de conduire");
	    GridBagConstraints gbcLayout3 = new GridBagConstraints();
	    gbcLayout3.anchor = GridBagConstraints.LINE_START;
	    gbcLayout3.fill = GridBagConstraints.HORIZONTAL;
	    gbcLayout3.gridwidth = GridBagConstraints.REMAINDER;
	    gbcLayout3.gridx = 0;
	    gbcLayout3.gridy = 2;
	    this.add(textFieldName3, gbcLayout3);
	    

	}
}
