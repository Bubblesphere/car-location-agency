package ui.widgets;

import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import ui.events.Event;
import ui.events.EventBubbler;
import ui.events.EventEnum.FormComboBoxEvents;
import ui.utils.ArrayListHelper;
import ui.utils.IListable;
import ui.utils.ListableCellRenderer;


public class WFormComboBox<T extends IListable> extends WAbstractFormComponent {
    private static final long serialVersionUID = 1L;
    private EventBubbler events;
    private JComboBox<T> comboBox;
    @SuppressWarnings("unused")
    private IListable currentListable;

    public WFormComboBox(String labelText, ArrayList<T> list) {
        super(labelText);
        this.events = new EventBubbler(this.listenerList);


        this.comboBox = new JComboBox<T>(ArrayListHelper.toDefaultComboBoxListModel(list));
        this.comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.comboBox.setRenderer(new ListableCellRenderer());
        this.comboBox.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                eventHandler(FormComboBoxEvents.COMBO_BOX_OPENED);
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                eventHandler(FormComboBoxEvents.COMBO_BOX_CLOSED);
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                eventHandler(FormComboBoxEvents.COMBO_BOX_CANCELLED);
            }

        });
        this.add(this.comboBox);
    }

    @SuppressWarnings("unchecked")
    public void set(ArrayList<T> list) {
        T previouslySelected = (T) this.comboBox.getSelectedItem();
        this.comboBox.setModel(ArrayListHelper.toDefaultComboBoxListModel(list));
        setSelected(previouslySelected);
    }

    @SuppressWarnings("unchecked")
    public T getSelected() {
        return (T) this.comboBox.getSelectedItem();
    }

    public void setSelected(T listable) {
        this.comboBox.getModel().setSelectedItem(listable);
    }

    public EventBubbler events() {
        return this.events;
    }

    private void eventHandler(FormComboBoxEvents eventName) {
        this.events.fireEvent(new Event<FormComboBoxEvents>(this, eventName));
    }
}
