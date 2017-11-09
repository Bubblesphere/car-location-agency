

public class Personne {
	private static String Prenom;
	private static String Nom;
	private int Id;
	
	public static String getPrenom() {
		return Prenom;
	}
	public static void setPrenom(String prenom) {
		Prenom = prenom;
	}
	public static String getNom() {
		return Nom;
	}
	public static void setNom(String nom) {
		Nom = nom;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
}
