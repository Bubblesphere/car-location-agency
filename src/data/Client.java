package data;

public class Client extends Personne {
	private int Id;
	private String Adresse;
	private String NumeroPermis;
	private String NumeoTelphone;
	private String Courriel;
	private String Note;

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

	public String getNumeroPermis() {
		return NumeroPermis;
	}

	public void setNumeroPermis(String numeroPermis) {
		NumeroPermis = numeroPermis;
	}

	public String getNumeoTelphone() {
		return NumeoTelphone;
	}

	public void setNumeoTelphone(String numeoTelphone) {
		NumeoTelphone = numeoTelphone;
	}

	public String getCourriel() {
		return Courriel;
	}

	public void setCourriel(String courriel) {
		Courriel = courriel;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Client(String nom, String prenom, String numeoTelphone, String note) {
		super(nom, prenom);		
		NumeoTelphone = numeoTelphone;
		Note = note;
		Adresse = "";
		NumeroPermis = "";
		Courriel = "";
	}

	public Client(String nom, String prenom, int id, String adresse, String numeroPermis, String numeoTelphone,
			String courriel, String note) {
		super(nom, prenom);
		Id = id;
		Adresse = adresse;
		NumeroPermis = numeroPermis;
		NumeoTelphone = numeoTelphone;
		Courriel = courriel;
		Note = note;
	}

	public Client(String nom, String prenom, String adresse, String numeroPermis, String numeoTelphone, String courriel,
			String note) {
		super(nom, prenom);
		Adresse = adresse;
		NumeroPermis = numeroPermis;
		NumeoTelphone = numeoTelphone;
		Courriel = courriel;
		Note = note;
	}

	@Override
	public String toString() {
		return "Client [Id=" + Id + ", Adresse=" + Adresse + ", NumeroPermis=" + NumeroPermis + ", NumeoTelphone="
				+ NumeoTelphone + ", Courriel=" + Courriel + ", Note=" + Note + "]";
	}

}
