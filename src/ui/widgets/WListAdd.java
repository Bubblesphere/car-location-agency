package ui.widgets;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.IListable;
import ui.events.Event;
import ui.events.EventBubbler;
import ui.events.IEventName;

public class WListAdd extends JPanel {
  private EventBubbler events;

  private GridBagLayout layout;
  private WList widgetList;
  private JButton addButton;
  private GridBagConstraints gbcAddButton;
  private JScrollPane scrollPane;
  private GridBagConstraints gbcScrollPane;

  public static enum Events implements IEventName {
    LIST_VALUE_CHANGED, BUTTON_ADD_CLICKED
  }

  public WListAdd(ArrayList<? extends IListable> list) {
    this.events = new EventBubbler(this.listenerList);

    this.layout = new GridBagLayout();
    this.layout.columnWidths = new int[] { 97, 0 };
    this.layout.rowHeights = new int[] { 16, 25, 539, 2, 0 };
    this.layout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
    this.layout.rowWeights = new double[] { 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
    this.setLayout(this.layout);

    widgetList = new WList(list);
    widgetList.events().addListener(new ui.events.EventListener() {
      @Override
      public void handleEvent(Event evt) {
        eventHandler(Events.LIST_VALUE_CHANGED);
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
        eventHandler(Events.BUTTON_ADD_CLICKED);
      }
    });
  }
  
  public void setModel(JList<? extends IListable> list) {
	  this.widgetList.setModel((DefaultComboBoxModel<? extends IListable>) list.getModel());
  }

  public int getSelectedIndex() {
    return this.widgetList.getLastSelectedIndex();
  }
  
  public void setSelectedIndex(int index){
    this.widgetList.setSelectedIndex(index);
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

  private void eventHandler(Events eventName) {
    this.events.fireEvent(new Event(this, eventName));
  }
}
