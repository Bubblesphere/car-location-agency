
public class ClasseVehicule {
	private int Id;
	private String Nom;
	private Float PrixJournalier;
	
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public Float getPrixJournalier() {
		return PrixJournalier;
	}
	public void setPrixJournalier(Float prixJournalier) {
		PrixJournalier = prixJournalier;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
}
