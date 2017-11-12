package Data;

public class Utilisateur extends Personne {
	private int Id;
	private String Login;
	private String MotDePasse;
	private String Courriel;
	private int NumEmploye;
	private int Role;
	private Boolean Desactive;

	public Utilisateur(String nom, String prenom, int id, String login, String motDePasse, String courriel,
			int numEmploye, int role, Boolean desactive) {
		super(nom, prenom);
		Id = id;
		Login = login;
		MotDePasse = motDePasse;
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

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getMotDePasse() {
		return MotDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		MotDePasse = motDePasse;
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

	@Override
	public String toString() {
		return "Utilisateur [Id=" + Id + ", Login=" + Login + ", MotDePasse=" + MotDePasse + ", Courriel=" + Courriel
				+ ", NumEmploye=" + NumEmploye + ", Role=" + Role + ", Desactive=" + Desactive + "]";
	}

}
