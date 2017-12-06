package ui.widgets;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.events.Event;
import ui.events.EventBubbler;
import ui.events.EventListener;
import ui.events.IEventName;
import ui.utils.FormBuilder;
import ui.utils.IListable;

public class WForm<T extends IListable> extends JPanel {

	private static final long serialVersionUID = 1L;

private EventBubbler events;

  private GridBagLayout layout;
  private JLabel lblTitle;
  private GridBagConstraints gbcTitle;
  private WAbstractFormPanel<T> form;
  private GridBagConstraints gbcForm;
  private WFormButton saveButton;
  private GridBagConstraints gbcSaveButton;

  public enum Events implements IEventName {
    BUTTON_SAVE_CLICK
  }

  public WForm(String title, WAbstractFormPanel<T> form) {
    this.events = new EventBubbler(this.listenerList);

    this.setBorder(new EmptyBorder(32, 32, 32, 32));

    this.layout = FormBuilder.getLayout();
    this.setLayout(this.layout);

    this.lblTitle = new JLabel(title);
    this.lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
    this.gbcTitle = FormBuilder.getGBCFullRow();
    this.gbcTitle.gridx = 0;
    this.gbcTitle.gridy = 0;
    this.add(this.lblTitle, this.gbcTitle);

    this.form = form;
    this.gbcForm = FormBuilder.getGBCFullRow();
    this.gbcForm.gridx = 0;
    this.gbcForm.gridy = 1;
    this.add(this.form, this.gbcForm);

    this.saveButton = new WFormButton("Sauvegarder");
    this.saveButton.events().addListener(new EventListener() {
    	@SuppressWarnings("rawtypes") 
      @Override
      public void handleEvent(Event evt) {
    	  save();
    	  eventHandler(WForm.Events.BUTTON_SAVE_CLICK);
      }
    });
    this.gbcSaveButton = FormBuilder.getGBCPartialRow();
    this.gbcSaveButton.gridx = 1;
    this.gbcSaveButton.gridy = 3;
    this.add(this.saveButton, this.gbcSaveButton);
    
    this.form.init();
  }

  public boolean getHasUnsavedContent() {
	  return this.form.getHasUnsavedContent();
  }
  
  public void setHasUnsavedContent(Boolean hasUnsavedContent) {
    this.form.setHasUnsavedContent(hasUnsavedContent);
}
  
  private void save() {
	  this.form.setHasUnsavedContent(false);
  }
  
  public T get() {
    return this.form.get();
  }

  public void set(T listable) {
    this.form.set(listable);
  }
  
  public void init() {
	  this.form.init();
  }

  public EventBubbler events() {
    return this.events;
  }

  private void eventHandler(Events event) {
    this.events.fireEvent(new Event<Events>(this, event));
  }
}
