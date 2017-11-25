package ui.widgets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import data.IListable;
import ui.utils.Event;
import ui.utils.EventBubbler;

public class WFormButton extends JPanel {
	private EventBubbler events;
	private BoxLayout layout;
	private JButton button;
	
	public WFormButton(String buttonText) {
		this.events = new EventBubbler(this.listenerList);
		
		this.layout = new BoxLayout(this, BoxLayout.Y_AXIS);
	    this.setBorder(new EmptyBorder(24, 0, 0, 16));
	    this.setLayout(this.layout);

	    this.button = new JButton(buttonText);
	    this.button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
	    this.button.setAlignmentX(this.RIGHT_ALIGNMENT);
	    this.button.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		eventHandler("ButtonSaveClick");
	    	}
	    });
	    this.add(this.button);
	    
	}
	
	public EventBubbler events() {
		return this.events;
	}
	
	private void eventHandler(String command) {
		this.events.fireEvent(new Event(this, command));
	}
}
