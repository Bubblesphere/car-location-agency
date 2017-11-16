package data;

import java.util.Date;

public class Location {
  private int id;
  private int vehiculeId;
  private Vehicule vehiculeLoue;
  private Reservation reservation;
  private Utilisateur locateur;
  private int reservationId;
  private int utilisateurId;
  private Date dateDeRetour;
  private boolean assurance;
  private boolean usureJournalier;
  private int essenceManquant;
  private int departKm;
  private int retourKm;
  private String note;
  private float estimationReparation;

  /**
   * Constructeur pour une Location
   * 
   * @param id
   *          id de la location.
   * @param vehiculeId
   *          id du véhicule de la location.
   * @param vehiculeLoue
   *          véhicule de la locaiton.
   * @param reservation
   *          réservation ayant mené à la location.
   * @param locateur
   *          l'utilisateur qui a fait la location.
   * @param reservationId
   *          id de la réservation ayant mené à la location.
   * @param utilisateurId
   *          id de l'utilisateur qui a fait la location.
   * @param dateDeRetour
   *          date de retour du locataire.
   * @param assurance
   *          si le locataire a prit l'assurance.
   * @param usureJournalier
   *          si le locataire a prit le forfait d'usure journalier.
   * @param essenceManquant
   *          essence manquant en litre.
   * @param departKm
   *          kilométrage du véhicule au départ.
   * @param retourKm
   *          kilométrage du véhicule au retour.
   * @param note
   *          commentaire.
   * @param estimationReparation
   *          estimation de réparation à faire au véhicule au retour.
   */
  public Location(int id, int vehiculeId, Vehicule vehiculeLoue, Reservation reservation,
      Utilisateur locateur, int reservationId, int utilisateurId, Date dateDeRetour,
      boolean assurance, boolean usureJournalier, int essenceManquant, int departKm, int retourKm,
      String note, float estimationReparation) {
    super();
    this.id = id;
    this.vehiculeId = vehiculeId;
    this.vehiculeLoue = vehiculeLoue;
    this.reservation = reservation;
    this.locateur = locateur;
    this.reservationId = reservationId;
    this.utilisateurId = utilisateurId;
    this.dateDeRetour = dateDeRetour;
    this.assurance = assurance;
    this.usureJournalier = usureJournalier;
    this.essenceManquant = essenceManquant;
    this.departKm = departKm;
    this.retourKm = retourKm;
    this.note = note;
    this.estimationReparation = estimationReparation;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getVehiculeId() {
    return vehiculeId;
  }

  public void setVehiculeId(int vehiculeId) {
    this.vehiculeId = vehiculeId;
  }

  public Vehicule getVehiculeLoue() {
    return vehiculeLoue;
  }

  public void setVehiculeLoue(Vehicule vehiculeLoue) {
    this.vehiculeLoue = vehiculeLoue;
  }

  public Reservation getReservation() {
    return reservation;
  }

  public void setReservation(Reservation reservation) {
    this.reservation = reservation;
  }

  public Utilisateur getLocateur() {
    return locateur;
  }

  public void setLocateur(Utilisateur locateur) {
    this.locateur = locateur;
  }

  public int getReservationId() {
    return reservationId;
  }

  public void setReservationId(int reservationId) {
    this.reservationId = reservationId;
  }

  public int getUtilisateurId() {
    return utilisateurId;
  }

  public void setUtilisateurId(int utilisateurId) {
    this.utilisateurId = utilisateurId;
  }

  public Date getDateDeRetour() {
    return dateDeRetour;
  }

  public void setDateDeRetour(Date dateDeRetour) {
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

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public float getEstimationReparation() {
    return estimationReparation;
  }

  public void setEstimationReparation(float estimationReparation) {
    this.estimationReparation = estimationReparation;
  }

}
