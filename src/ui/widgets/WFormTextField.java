package ui.widgets;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ui.events.Event;
import ui.events.EventBubbler;
import ui.events.EventEnum.FormTextFieldEvents;

public class WFormTextField extends WAbstractFormComponent {
    private static final long serialVersionUID = 1L;
    private EventBubbler events;
    private JTextField textField;

    public WFormTextField(String labelText) {
        super(labelText);
        this.events = new EventBubbler(this.listenerList);

        this.textField = new JTextField();
        this.textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.textField.setAlignmentX(Component.LEFT_ALIGNMENT);
        DocumentListener dl = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                eventHandler(FormTextFieldEvents.TEXTFIELD_TEXT_CHANGED);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                eventHandler(FormTextFieldEvents.TEXTFIELD_TEXT_CHANGED);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                eventHandler(FormTextFieldEvents.TEXTFIELD_TEXT_CHANGED);
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

    private void eventHandler(FormTextFieldEvents eventName) {
        this.events.fireEvent(new Event<FormTextFieldEvents>(this, eventName));
    }
}
