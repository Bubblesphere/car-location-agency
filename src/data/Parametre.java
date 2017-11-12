package data;

public class Parametre {
	private String Id;
	private String Valeur;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getValeur() {
		return Valeur;
	}

	public void setValeur(String valeur) {
		Valeur = valeur;
	}

	@Override
	public String toString() {
		return "Parametre [Id=" + Id + ", Valeur=" + Valeur + "]";
	}

	public Parametre(String id, String valeur) {
		super();
		Id = id;
		Valeur = valeur;
	}

}
