package data;

import java.time.LocalDate;

public class Location extends Reservation implements IListable {
  private int locationId;
  private Vehicule vehicule;
  private Utilisateur locateur;
  private LocalDate dateDeRetour;
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
   * @param locationId
   *          id de la location.
   * @param vehicule
   *          véhicule de la locaiton.
   * @param locateur
   *          l'utilisateur qui a fait la location.
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
  public Location(int reservationId, Client client, Classe classe, LocalDate startDate,
      LocalDate retourAnticipeDate, String note, Utilisateur utilisateur, int locationId,
      Vehicule vehicule, Utilisateur locateur, LocalDate dateDeRetour, boolean assurance,
      boolean usureJournalier, int essenceManquant, int departKm, int retourKm, String note2,
      float estimationReparation) {
    super(reservationId, client.getId(), client, classe.getId(), classe, startDate, retourAnticipeDate, note, utilisateur.getId(), utilisateur);
    this.locationId = locationId;
    this.vehicule = vehicule;
    this.locateur = locateur;
    this.dateDeRetour = dateDeRetour;
    this.assurance = assurance;
    this.usureJournalier = usureJournalier;
    this.essenceManquant = essenceManquant;
    this.departKm = departKm;
    this.retourKm = retourKm;
    note = note2;
    this.estimationReparation = estimationReparation;
  }

  public Vehicule getVehicule() {
    return vehicule;
  }

  public void setVehicule(Vehicule vehicule) {
    this.vehicule = vehicule;
  }

  public Utilisateur getLocateur() {
    return locateur;
  }

  public void setLocateur(Utilisateur locateur) {
    this.locateur = locateur;
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

  public int getLocationId() {
    return locationId;
  }

  public void setLocationId(int locationId) {
    this.locationId = locationId;
  }

@Override
public int getKey() {
	return locationId;
}

@Override
public String getDisplayedText() {
	return this.getLocataire().getPrenom() + " " + this.getLocataire().getNom() + " - " + this.getStartDate().toString();
}

}
