package ui.widgets;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

import ui.utils.FormBuilder;

public class WPay extends JFrame {
	  private GridBagLayout layout;
	  private WLabel lblTotal;
	  private GridBagConstraints gbcTotal;
	  
	public WPay() {
		
	    this.layout = FormBuilder.getLayout();
	    this.setLayout(this.layout);
	    
	    this.lblTotal = new WLabel("Total");
	    this.gbcTotal = FormBuilder.getGBCFullRow();
	    this.gbcTotal.gridx = 0;
	    this.gbcTotal.gridy = 1;
	    this.add(this.lblTotal, this.gbcTotal);
	    
	    this.pack();
	    this.setVisible(true);
	}
}
