package data;

import ui.utils.IListable;

public class TypePaiement implements IListable {
	private int id;
	private String name;
	
	
	public TypePaiement(type type) {
		switch(type) {
		case COMPTANT:
			this.id = 0;
			this.name = "Comptant";
			break;
		case D�BIT:
			this.id = 1;
			this.name = "D�bit";
			break;
		case CR�DIT:
			this.id = 2;
			this.name = "Cr�dit";
			break;
		}
	}
	
	public static enum type {
		COMPTANT, D�BIT, CR�DIT
	}
	
	
	
	@Override
	public String getDisplayedText() {
		return this.name;
	}

	@Override
	public int getId() {
		return this.id;
	}

}
