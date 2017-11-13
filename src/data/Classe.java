package data;

public class Classe {
	private int Id;
	private String Nom;
	private float PrixJournalier;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public float getPrixJournalier() {
		return PrixJournalier;
	}

	public void setPrixJournalier(float prixJournalier) {
		PrixJournalier = prixJournalier;
	}

	@Override
	public String toString() {
		return "Classe [Id=" + Id + ", Nom=" + Nom + ", PrixJournalier=" + PrixJournalier + "]";
	}

	public Classe(int id, String nom, float prixJournalier) {
		super();
		Id = id;
		Nom = nom;
		PrixJournalier = prixJournalier;
	}

	public Classe(String nom, float prixJournalier) {
		super();
		Nom = nom;
		PrixJournalier = prixJournalier;
	}

}
