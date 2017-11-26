package ui.widgets;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.IListable;
import ui.utils.Event;
import ui.utils.EventBubbler;

public class WListAdd extends JPanel {
	private EventBubbler events;
	
	private GridBagLayout layout;
	private WList widgetList;
	private JButton addButton;
	private GridBagConstraints gbcAddButton;
	private JScrollPane scrollPane;
	private GridBagConstraints gbcScrollPane;
	
	
	public WListAdd(DefaultListModel<? extends IListable> list) {
	    this.events = new EventBubbler(this.listenerList);
	    
		this.layout = new GridBagLayout();
		this.layout.columnWidths = new int[]{97, 0};
		this.layout.rowHeights = new int[]{16, 25, 539, 2, 0};
		this.layout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		this.layout.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		this.setLayout(this.layout);
		
		widgetList = new WList(list);
		widgetList.events().addListener(new ui.utils.EventListener() {		
			@Override
			public void handleEvent(Event evt) {
				System.out.println("ListValueChanged from ListAdd");
				eventHandler("ListValueChanged");
			}
		});
		
		this.scrollPane = new JScrollPane(widgetList);
		this.gbcScrollPane = new GridBagConstraints();
		this.gbcScrollPane.fill = GridBagConstraints.BOTH;
		this.gbcScrollPane.gridwidth = GridBagConstraints.REMAINDER;
		this.gbcScrollPane.gridheight = GridBagConstraints.REMAINDER;
		this.gbcScrollPane.gridx = 0;
		this.gbcScrollPane.gridy = 1;
		this.gbcScrollPane.anchor = GridBagConstraints.LINE_END;
		this.gbcScrollPane.weightx = this.gbcScrollPane.weighty = 1.0;
		this.add(scrollPane, this.gbcScrollPane);

		this.addButton = new JButton("Ajouter");
		this.gbcAddButton = new GridBagConstraints();
		this.gbcAddButton.fill = GridBagConstraints.BOTH;
		this.gbcAddButton.gridx = 0;
		this.gbcAddButton.gridy = 0;
		this.add(this.addButton, this.gbcAddButton);
		this.addButton.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		eventHandler("ButtonAddClick");
	    	}
	    });
	}
	
	public int getSelectedIndex() {
		return this.widgetList.getLastSelectedIndex();
	}
	
	public EventBubbler events() {
		return this.events;
	}
	
	public void setModel(DefaultListModel<? extends IListable> list) {
		this.widgetList.setModel(list);
	}
	
	public void addElement(IListable listable) {
		this.widgetList.addElement(listable);
	}
	
	private void eventHandler(String command) {
		this.events.fireEvent(new Event(this, command));
	}
}




