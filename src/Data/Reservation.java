package Data;
import java.util.Date;

public class Reservation {
	private int Id;
	private int ClientId;
	private Client Locataire;
	private int ClasseId;
	private Classe ClasseDesire;
	private Date StartDate;
	private Date FinDate;
	private String Note;
	private int UtilisateurId;
	private Utilisateur Employe;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getClientId() {
		return ClientId;
	}

	public void setClientId(int clientId) {
		ClientId = clientId;
	}

	public int getClasseId() {
		return ClasseId;
	}

	public void setClasseId(int classeId) {
		ClasseId = classeId;
	}

	public Classe getClasseDesire() {
		return ClasseDesire;
	}

	public void setClasseDesire(Classe classeDesire) {
		ClasseDesire = classeDesire;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	public Date getFinDate() {
		return FinDate;
	}

	public void setFinDate(Date finDate) {
		FinDate = finDate;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public int getUtilisateurId() {
		return UtilisateurId;
	}

	public void setUtilisateurId(int utilisateurId) {
		UtilisateurId = utilisateurId;
	}

	public Utilisateur getEmploye() {
		return Employe;
	}

	public void setEmploye(Utilisateur employe) {
		Employe = employe;
	}

	@Override
	public String toString() {
		return "Reservation [Id=" + Id + ", ClientId=" + ClientId + ", Locataire=" + Locataire + ", ClasseId="
				+ ClasseId + ", ClasseDesire=" + ClasseDesire + ", StartDate=" + StartDate + ", FinDate=" + FinDate
				+ ", Note=" + Note + ", UtilisateurId=" + UtilisateurId + ", Employe=" + Employe + "]";
	}

	public Reservation(int id, int clientId, Client locataire, int classeId, Classe classeDesire, Date startDate,
			Date finDate, String note, int utilisateurId, Utilisateur employe) {
		super();
		Id = id;
		ClientId = clientId;
		Locataire = locataire;
		ClasseId = classeId;
		ClasseDesire = classeDesire;
		StartDate = startDate;
		FinDate = finDate;
		Note = note;
		UtilisateurId = utilisateurId;
		Employe = employe;
	}

	public Client getLocataire() {
		return Locataire;
	}

	public void setLocataire(Client locataire) {
		Locataire = locataire;
	}

}
