package ui.widgets.forms.components;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import ui.events.Event;
import ui.events.EventBubbler;
import ui.events.EventEnum.FormButtonEvents;

public class WFormButton extends WAbstractFormComponentLayout {
    private static final long serialVersionUID = 1L;
    private EventBubbler events;
    private BoxLayout layout;
    protected JButton button;

    public WFormButton(String buttonText) {
        this.events = new EventBubbler(this.listenerList);

        this.layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setBorder(new EmptyBorder(24, 0, 0, 16));
        this.setLayout(this.layout);

        this.button = new JButton(buttonText);
        this.button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        this.button.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eventHandler(FormButtonEvents.BUTTON_CLICKED);
            }
        });
        this.add(this.button);

    }

    public EventBubbler events() {
        return this.events;
    }

    private void eventHandler(FormButtonEvents eventName) {
        this.events.fireEvent(new Event<FormButtonEvents>(this, eventName));
    }

    public void setDisabled(boolean b) {
        this.button.setEnabled(!b);
    }
}
