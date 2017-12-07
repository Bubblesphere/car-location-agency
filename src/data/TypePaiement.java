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
		case DÉBIT:
			this.id = 1;
			this.name = "Débit";
			break;
		case CRÉDIT:
			this.id = 2;
			this.name = "Crédit";
			break;
		}
	}
	
	public static enum type {
		COMPTANT, DÉBIT, CRÉDIT
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
