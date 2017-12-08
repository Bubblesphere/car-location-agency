package ui.widgets;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ui.events.Event;
import ui.events.EventBubbler;
import ui.events.EventEnum;
import ui.events.EventListener;
import ui.utils.IListable;

public class WListAdd<T extends IListable> extends JPanel implements IPanelList<T> {

	private static final long serialVersionUID = 1L;

	private EventBubbler events;

  private GridBagLayout layout;
  private WList<T> widgetList;
  private JButton addButton;
  private GridBagConstraints gbcAddButton;
  private JScrollPane scrollPane;
  private GridBagConstraints gbcScrollPane;

  public WListAdd(ArrayList<T> list) {
    this.events = new EventBubbler(this.listenerList);

    this.layout = new GridBagLayout();
    this.layout.columnWidths = new int[] { 97, 0 };
    this.layout.rowHeights = new int[] { 16, 25, 539, 2, 0 };
    this.layout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
    this.layout.rowWeights = new double[] { 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
    this.setLayout(this.layout);

    widgetList = new WList<T>(list);
    widgetList.events().addListener(new EventListener() {
    	@SuppressWarnings("rawtypes")
      @Override
      public void handleEvent(Event evt) {
        eventHandler(EventEnum.ListEvents.LIST_VALUE_CHANGED);
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
        eventHandler(EventEnum.ListEvents.BUTTON_ADD_CLICKED);
      }
    });
  }
  
  @Override
public void setSelectedIndexLast() {
	  this.widgetList.setSelectedIndexLast();
  }

  public int getSelectedIndex() {
    return this.widgetList.getSelectedIndex();
  }
  
  @Override
public int getLastSelectedIndex(){
    return this.widgetList.getLastSelectedIndex();
  }

  @Override
public EventBubbler events() {
    return this.events;
  }

  @Override
public void setModelList(DefaultListModel<T> list) {
    this.widgetList.setModel(list);
  }

  @Override
public void addElement(T listable) {
    this.widgetList.addElement(listable);
  }

  private void eventHandler(EventEnum.ListEvents eventName) {
    this.events.fireEvent(new Event<EventEnum.ListEvents>(this, eventName));
  }

	@Override
	public void setSelectedIndex(int index) {
	    this.widgetList.setSelectedIndex(index);
	}
}
