package ui.list;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class WidgetListHashMapAddPanel extends JPanel {
	private HashMapListModel list;
	
	public WidgetListHashMapAddPanel(HashMapListModel list) {
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{97, 0};
		gbl_panel_1.rowHeights = new int[]{16, 25, 539, 2, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		this.setLayout(gbl_panel_1);
		
		JButton btnNewButton = new JButton("Ajouter");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		this.add(btnNewButton, gbc_btnNewButton);
		
		WidgetListHashMap widgetList = new WidgetListHashMap(list);

		widgetList.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		System.out.println(widgetList.selectedKey);
	    	}
	    });
		
		JScrollPane scrollPane = new JScrollPane(widgetList);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = GridBagConstraints.REMAINDER;
		gbc_scrollPane.gridheight = GridBagConstraints.REMAINDER;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		gbc_scrollPane.anchor = GridBagConstraints.LINE_END;
		gbc_scrollPane.weightx = gbc_scrollPane.weighty = 1.0;

		this.add(scrollPane, gbc_scrollPane);
	}
}






