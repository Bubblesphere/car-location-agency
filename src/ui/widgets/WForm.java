package ui.widgets;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.IListable;
import ui.events.Event;
import ui.events.EventBubbler;
import ui.events.EventListener;
import ui.events.IEventName;
import ui.utils.FormBuilder;

public class WForm extends JPanel {
  private EventBubbler events;

  private GridBagLayout layout;
  private JLabel lblTitle;
  private GridBagConstraints gbcTitle;
  private WAbstractFormPanel form;
  private GridBagConstraints gbcForm;
  private WFormButton saveButton;
  private GridBagConstraints gbcSaveButton;

  public enum Events implements IEventName {
    BUTTON_SAVE_CLICK
  }

  public WForm(String title, WAbstractFormPanel form) {
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
  }

  public boolean getHasUnsavedContent() {
	  return this.form.getHasUnsavedContent();
  }
  
  private void save() {
	  this.form.setHasUnsavedContent(false);
  }
  
  public IListable get() {
    return this.form.get();
  }

  public void set(IListable listable) {
    this.form.set(listable);
  }

  public EventBubbler events() {
    return this.events;
  }

  private void eventHandler(Events event) {
    this.events.fireEvent(new Event(this, event));
  }
}
