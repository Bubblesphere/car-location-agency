package ui.buttons;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class WidgetButtonAbstract extends JButton {
	
	public WidgetButtonAbstract(String displayedText) {
		super(displayedText);
		this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		onClick(e);
	    	}
	    });
	}

	protected abstract void onClick(MouseEvent e);
}