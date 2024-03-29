package ui.widgets.tabs;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

public class WSplitPaneTab extends JSplitPane {

	private static final long serialVersionUID = 1L;

	public WSplitPaneTab(JTabbedPane tabbedPane, String tabDisplayedext) {
	    JPanel panel = new JPanel();
	    tabbedPane.addTab(tabDisplayedext, panel);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	    panel.add(this);
	}
}
