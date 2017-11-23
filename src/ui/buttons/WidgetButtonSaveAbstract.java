package ui.buttons;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class WidgetButtonSaveAbstract extends WidgetButtonAbstract {
	
	public WidgetButtonSaveAbstract() {
		super("Sauvegarder");
		this.setIcon(new ImageIcon(WidgetButtonSaveAbstract.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	}
	
}