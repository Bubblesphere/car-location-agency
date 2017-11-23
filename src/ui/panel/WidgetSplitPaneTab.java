package ui.panel;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

public class WidgetSplitPaneTab extends JSplitPane {
	public WidgetSplitPaneTab(JTabbedPane tabbedPane, String tabDisplayedext) {
	    JPanel panel = new JPanel();
	    tabbedPane.addTab(tabDisplayedext,  panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));  
	    panel.add(this);
	}
}
