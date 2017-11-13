package data;

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
	private boolean Assurance;
	private boolean UsureJournalier;
	private int EssenceManquant;
	private int DepartKm;
	private int RetourKm;
	private String Note;
	private float EstimationReparation;

	public Location(int id, int vehiculeId, Vehicule vehiculeLoue, Reservation lReservation, Utilisateur locateur,
			int reservationId, int utilisateurId, Date dateDeRetour, boolean assurance, boolean usureJournalier,
			int essenceManquant, int departKm, int retourKm, String note, float estimationReparation) {
		super();
		Id = id;
		VehiculeId = vehiculeId;
		VehiculeLoue = vehiculeLoue;
		LReservation = lReservation;
		Locateur = locateur;
		ReservationId = reservationId;
		UtilisateurId = utilisateurId;
		DateDeRetour = dateDeRetour;
		Assurance = assurance;
		UsureJournalier = usureJournalier;
		EssenceManquant = essenceManquant;
		DepartKm = departKm;
		RetourKm = retourKm;
		Note = note;
		EstimationReparation = estimationReparation;
	}

	public boolean isAssurance() {
		return Assurance;
	}

	public void setAssurance(boolean assurance) {
		Assurance = assurance;
	}

	public boolean isUsureJournalier() {
		return UsureJournalier;
	}

	public void setUsureJournalier(boolean usureJournalier) {
		UsureJournalier = usureJournalier;
	}

	public int getDepartKm() {
		return DepartKm;
	}

	public void setDepartKm(int departKm) {
		DepartKm = departKm;
	}

	public float getEstimationReparation() {
		return EstimationReparation;
	}

	public void setEstimationReparation(float estimationReparation) {
		EstimationReparation = estimationReparation;
	}

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

}
