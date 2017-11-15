package data;

public class Personne {
	private String Nom;
	private String Prenom;

	public Personne(String nom, String prenom) {
		super();
		Nom = nom;
		Prenom = prenom;
	}
	
	public Personne() {
		super();		
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

}
