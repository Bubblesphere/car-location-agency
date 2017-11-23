package ui.list;

import data.Client;

public class ClientHashmapListModel extends AbstractHashmapListModel {
	@Override
	public Object getFromKey(int key) {
		// TODO: Implement
		return new Client("Dallaire", "Deric", "8195559999", "");
	}

	@Override
	public void addElement(Object object) {
		Client client = (Client)object;
		this.hmap.put(client.getId(), client.getPrenom());
	}
}
