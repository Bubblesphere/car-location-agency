package data;

import java.time.LocalDate;

import ui.utils.IListable;

public class Location extends Reservation implements IListable {
	private int locationId;
	private Vehicule vehicule;
	private Utilisateur utilisateurLocation;
	private LocalDate dateDeRetour;
	private boolean assurance;
	private boolean usureJournalier;
	private int essenceManquant;
	private int departKm;
	private int retourKm;
	private String noteLocation;
	private String noteRetour;
	private float estimationReparation;

	/**
	 * Constructeur pour une Location
	 * 
	 * @param locationId
	 *            id de la location.
	 * @param vehicule
	 *            v�hicule de la locaiton.
	 * @param locateur
	 *            l'utilisateur qui a fait la location.
	 * @param dateDeRetour
	 *            date de retour du locataire.
	 * @param assurance
	 *            si le locataire a prit l'assurance.
	 * @param usureJournalier
	 *            si le locataire a prit le forfait d'usure journalier.
	 * @param essenceManquant
	 *            essence manquant en litre.
	 * @param departKm
	 *            kilom�trage du v�hicule au d�part.
	 * @param retourKm
	 *            kilom�trage du v�hicule au retour.
	 * @param noteLocation
	 *            commentaire.
	 * @param estimationReparation
	 *            estimation de r�paration � faire au v�hicule au retour.
	 */
	public Location(int reservationId,
					Client clientReservation,
					Classe classeReservation,
					LocalDate startDate,
					LocalDate finDate,
					String noteLocation,
					Utilisateur utilisateurReservation,
					int locationId,
					Vehicule vehicule,
					Utilisateur utilisateurLocation,
					LocalDate dateDeRetour,
					boolean assurance,
					boolean usureJournalier,
					int essenceManquant,
					int departKm,
					int retourKm,
					String noteReservation,
			float estimationReparation) {
		super(reservationId, clientReservation, classeReservation, startDate, finDate, noteReservation,
				utilisateurReservation);
		this.locationId = locationId;
		this.vehicule = vehicule;
		this.utilisateurLocation = utilisateurLocation;
		this.dateDeRetour = dateDeRetour;
		this.assurance = assurance;
		this.usureJournalier = usureJournalier;
		this.essenceManquant = essenceManquant;
		this.departKm = departKm;
		this.retourKm = retourKm;
		this.noteLocation = noteLocation;
		this.estimationReparation = estimationReparation;
	}

	public Location(int locationId) {
		super();
		this.locationId = locationId;
	}
	
	public void setReservation(Reservation reservation) {
		this.reservationId = reservation.getReservationId();    
	    this.clientReservation = reservation.getClientReservation();    
	    this.classeReservation = reservation.getClasseReservation();
	    this.startDate = reservation.getStartDate();
	    this.finDate = reservation.getFinDate();
	    this.noteReservation = reservation.getNoteReservation();
		this.utilisateurReservation = reservation.getUtilisateurReservation();
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public Utilisateur getUtilisateurLocation() {
		return utilisateurLocation;
	}

	public void setUtilisateurLocation(Utilisateur utilisateurLocation) {
		this.utilisateurLocation = utilisateurLocation;
	}

	public LocalDate getDateDeRetour() {
		return dateDeRetour;
	}

	public void setDateDeRetour(LocalDate dateDeRetour) {
		this.dateDeRetour = dateDeRetour;
	}

	public boolean isAssurance() {
		return assurance;
	}

	public void setAssurance(boolean assurance) {
		this.assurance = assurance;
	}

	public boolean isUsureJournalier() {
		return usureJournalier;
	}

	public void setUsureJournalier(boolean usureJournalier) {
		this.usureJournalier = usureJournalier;
	}

	public int getEssenceManquant() {
		return essenceManquant;
	}

	public void setEssenceManquant(int essenceManquant) {
		this.essenceManquant = essenceManquant;
	}

	public int getDepartKm() {
		return departKm;
	}

	public void setDepartKm(int departKm) {
		this.departKm = departKm;
	}

	public int getRetourKm() {
		return retourKm;
	}

	public void setRetourKm(int retourKm) {
		this.retourKm = retourKm;
	}

	public String getNoteLocation() {
		return noteLocation;
	}

	public void setNoteLocation(String noteLocation) {
		this.noteLocation = noteLocation;
	}

	public String getNoteRetour() {
		return noteRetour;
	}

	public void setNoteRetour(String noteRetour) {
		this.noteRetour = noteRetour;
	}

	public float getEstimationReparation() {
		return estimationReparation;
	}

	public void setEstimationReparation(float estimationReparation) {
		this.estimationReparation = estimationReparation;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	@Override
	public String getDisplayedText() {
		return this.getClientReservation().getPrenom() + " " + this.getClientReservation().getNom() + " - "
				+ this.getStartDate().toString();
	}

}
