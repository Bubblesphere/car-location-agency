package ui.widgets;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.events.Event;
import ui.events.EventBubbler;
import ui.events.IEventName;

public class WFormButton extends JPanel {
  private EventBubbler events;
  private BoxLayout layout;
  private JButton button;

  public static enum Events implements IEventName {
    BUTTON_SAVE_CLICK
  }

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
        eventHandler(Events.BUTTON_SAVE_CLICK);
      }
    });
    this.add(this.button);

  }

  public EventBubbler events() {
    return this.events;
  }

  private void eventHandler(Enum eventName) {
    this.events.fireEvent(new Event(this, eventName));
  }
}
