package Data;
import java.util.Date;

public class Location {
	private int Id;
	private int VehiculeId;
	private Vehicule VehiculeLoue;
	private Reservation LReservation;
	private Utilisateur Locateur;
	private int ReservationId;
	private int UtilisateurId;
	private Date DateDeRetour;
	private float CoutAssuranceJour;
	private float CoutUsureKm;
	private float CoutEssenceLitre;
	private int EssenceManquant;
	private int RetourKm;
	private String Note;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getVehiculeId() {
		return VehiculeId;
	}

	public void setVehiculeId(int vehiculeId) {
		VehiculeId = vehiculeId;
	}

	public Vehicule getVehiculeLoue() {
		return VehiculeLoue;
	}

	public void setVehiculeLoue(Vehicule vehiculeLoue) {
		VehiculeLoue = vehiculeLoue;
	}

	public Reservation getLReservation() {
		return LReservation;
	}

	public void setLReservation(Reservation lReservation) {
		LReservation = lReservation;
	}

	public Utilisateur getLocateur() {
		return Locateur;
	}

	public void setLocateur(Utilisateur locateur) {
		Locateur = locateur;
	}

	public int getReservationId() {
		return ReservationId;
	}

	public void setReservationId(int reservationId) {
		ReservationId = reservationId;
	}

	public int getUtilisateurId() {
		return UtilisateurId;
	}

	public void setUtilisateurId(int utilisateurId) {
		UtilisateurId = utilisateurId;
	}

	public Date getDateDeRetour() {
		return DateDeRetour;
	}

	public void setDateDeRetour(Date dateDeRetour) {
		DateDeRetour = dateDeRetour;
	}

	public float getCoutAssuranceJour() {
		return CoutAssuranceJour;
	}

	public void setCoutAssuranceJour(float coutAssuranceJour) {
		CoutAssuranceJour = coutAssuranceJour;
	}

	public float getCoutUsureKm() {
		return CoutUsureKm;
	}

	public void setCoutUsureKm(float coutUsureKm) {
		CoutUsureKm = coutUsureKm;
	}

	public float getCoutEssenceLitre() {
		return CoutEssenceLitre;
	}

	public void setCoutEssenceLitre(float coutEssenceLitre) {
		CoutEssenceLitre = coutEssenceLitre;
	}

	public int getEssenceManquant() {
		return EssenceManquant;
	}

	public void setEssenceManquant(int essenceManquant) {
		EssenceManquant = essenceManquant;
	}

	public int getRetourKm() {
		return RetourKm;
	}

	public void setRetourKm(int retourKm) {
		RetourKm = retourKm;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	@Override
	public String toString() {
		return "Location [Id=" + Id + ", VehiculeId=" + VehiculeId + ", VehiculeLoue=" + VehiculeLoue
				+ ", LReservation=" + LReservation + ", Locateur=" + Locateur + ", ReservationId=" + ReservationId
				+ ", UtilisateurId=" + UtilisateurId + ", DateDeRetour=" + DateDeRetour + ", CoutAssuranceJour="
				+ CoutAssuranceJour + ", CoutUsureKm=" + CoutUsureKm + ", CoutEssenceLitre=" + CoutEssenceLitre
				+ ", EssenceManquant=" + EssenceManquant + ", RetourKm=" + RetourKm + ", Note=" + Note + "]";
	}

	public Location(int id, int vehiculeId, Vehicule vehiculeLoue, Reservation lReservation, Utilisateur locateur,
			int reservationId, int utilisateurId, Date dateDeRetour, float coutAssuranceJour, float coutUsureKm,
			float coutEssenceLitre, int essenceManquant, int retourKm, String note) {
		super();
		Id = id;
		VehiculeId = vehiculeId;
		VehiculeLoue = vehiculeLoue;
		LReservation = lReservation;
		Locateur = locateur;
		ReservationId = reservationId;
		UtilisateurId = utilisateurId;
		DateDeRetour = dateDeRetour;
		CoutAssuranceJour = coutAssuranceJour;
		CoutUsureKm = coutUsureKm;
		CoutEssenceLitre = coutEssenceLitre;
		EssenceManquant = essenceManquant;
		RetourKm = retourKm;
		Note = note;
	}

}
