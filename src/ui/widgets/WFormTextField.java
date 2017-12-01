package ui.widgets;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ui.events.Event;
import ui.events.EventBubbler;
import ui.events.IEventName;

public class WFormTextField extends JPanel {
  private EventBubbler events;
  private BoxLayout layout;
  private WLabel label;
  private JTextField textField;

  public static enum Events implements IEventName {
    TEXTFIELD_TEXT_CHANGED
  }
  
  public WFormTextField(String labelText) {
  this.events = new EventBubbler(this.listenerList);
	  
    this.layout = new BoxLayout(this, BoxLayout.Y_AXIS);
    this.setBorder(new EmptyBorder(24, 0, 0, 16));
    this.setLayout(this.layout);

    this.label = new WLabel(labelText);
    this.add(this.label);

    this.textField = new JTextField();
    this.textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    this.textField.setAlignmentX(this.LEFT_ALIGNMENT);
    DocumentListener dl = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
        	eventHandler(Events.TEXTFIELD_TEXT_CHANGED);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
        	eventHandler(Events.TEXTFIELD_TEXT_CHANGED);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            eventHandler(Events.TEXTFIELD_TEXT_CHANGED);
        }
    };
    this.textField.getDocument().addDocumentListener(dl);
    this.add(this.textField);
  }

  public String getText() {
    return this.textField.getText();
  }

  public void setText(String text) {
    this.textField.setText(text);
  }
  
  public EventBubbler events() {
    return this.events;
  }

  private void eventHandler(Enum eventName) {
    this.events.fireEvent(new Event(this, eventName));
  }
}
