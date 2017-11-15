package data;

import security.PasswordSecurity;

public class Utilisateur extends Personne {
	private int Id;
	private String MotDePasse;
	private String Courriel;
	private int NumEmploye;
	private int Role;
	private Boolean Desactive;
	
	
	public Utilisateur(String motDePasse, int numEmploye) {
		super();
		setMotDePasse(motDePasse);	
		NumEmploye = numEmploye;
	}

	public Utilisateur(String nom, String prenom, int id, String courriel, int numEmploye, int role,
			Boolean desactive) {
		super(nom, prenom);
		Id = id;
		Courriel = courriel;
		NumEmploye = numEmploye;
		Role = role;
		Desactive = desactive;
	}

	public Utilisateur(String nom, String prenom, int id, String motDePasse, String courriel, int numEmploye, int role,
			Boolean desactive) {
		super(nom, prenom);
		Id = id;
		setMotDePasse(motDePasse);	
		Courriel = courriel;
		NumEmploye = numEmploye;
		Role = role;
		Desactive = desactive;
	}

	public Utilisateur(String nom, String prenom, String motDePasse, String courriel, int numEmploye, int role,
			Boolean desactive) {
		super(nom, prenom);
		setMotDePasse(motDePasse);		
		Courriel = courriel;
		NumEmploye = numEmploye;
		Role = role;
		Desactive = desactive;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getMotDePasse() {
		return MotDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		MotDePasse = PasswordSecurity.hashPassword(motDePasse);
	}

	public String getCourriel() {
		return Courriel;
	}

	public void setCourriel(String courriel) {
		Courriel = courriel;
	}

	public int getNumEmploye() {
		return NumEmploye;
	}

	public void setNumEmploye(int numEmploye) {
		NumEmploye = numEmploye;
	}

	public int getRole() {
		return Role;
	}

	public void setRole(int role) {
		Role = role;
	}

	public Boolean getDesactive() {
		return Desactive;
	}

	public void setDesactive(Boolean desactive) {
		Desactive = desactive;
	}
}
