package ui.buttons.impl;

import java.awt.event.MouseEvent;

import ui.buttons.WidgetButtonSaveAbstract;

public class WidgetButtonSaveReservation extends WidgetButtonSaveAbstract {

	@Override
	protected void onClick(MouseEvent e) {
		System.out.println("Saved reservation successfully!");
	}

}
