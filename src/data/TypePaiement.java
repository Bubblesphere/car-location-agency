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
		case DEBIT:
			this.id = 1;
			this.name = "D�bit";
			break;
		case CREDIT:
			this.id = 2;
			this.name = "Cr�dit";
			break;
		}
	}
	
	public static enum type {
		COMPTANT, DEBIT, CREDIT
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
