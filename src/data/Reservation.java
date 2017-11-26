package data;

import java.time.LocalDate;

public class Reservation implements IListable {
  private int id;
  private int clientId;
  private Client locataire;
  private int classeId;
  private Classe classeDesire;
  private LocalDate startDate;
  private LocalDate finDate;
  private String note;
  private int utilisateurId;
  private Utilisateur employe;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public Client getLocataire() {
    return locataire;
  }

  public void setLocataire(Client locataire) {
    this.locataire = locataire;
  }

  public int getClasseId() {
    return classeId;
  }

  public void setClasseId(int classeId) {
    this.classeId = classeId;
  }

  public Classe getClasseDesire() {
    return classeDesire;
  }

  public void setClasseDesire(Classe classeDesire) {
    this.classeDesire = classeDesire;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getFinDate() {
    return finDate;
  }

  public void setFinDate(LocalDate finDate) {
    this.finDate = finDate;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public int getUtilisateurId() {
    return utilisateurId;
  }

  public void setUtilisateurId(int utilisateurId) {
    this.utilisateurId = utilisateurId;
  }

  public Utilisateur getEmploye() {
    return employe;
  }

  public void setEmploye(Utilisateur employe) {
    this.employe = employe;
  }

  /**
   * Constructeur pour Reservation
   * 
   * @param id
   *          id de la réservation.
   * @param clientId
   *          id du client de la réservation.
   * @param locataire
   *          id du client de la la réservation.
   * @param classeId
   *          id de la classe de véhicule désiré.
   * @param classeDesire
   *          la classe de véhicule désiré.
   * @param startDate
   *          la date de début de la location.
   * @param finDate
   *          la date de fin de la location.
   * @param note
   *          commentaire.
   * @param utilisateurId
   *          date d'entré en vigueur de ce paramètre.
   * @param employe
   *          id de l'utilisateur qui a créé la réservation..
   */
  public Reservation(int id, int clientId, Client locataire, int classeId, Classe classeDesire,
      LocalDate startDate, LocalDate finDate, String note, int utilisateurId, Utilisateur employe) {
    super();
    this.id = id;
    this.clientId = clientId;
    this.locataire = locataire;
    this.classeId = classeId;
    this.classeDesire = classeDesire;
    this.startDate = startDate;
    this.finDate = finDate;
    this.note = note;
    this.utilisateurId = utilisateurId;
    this.employe = employe;
  }

  @Override
  public String getDisplayedText() {
    return this.getLocataire().getPrenom() + " " + this.getLocataire().getNom() + " - "
        + this.getStartDate().toString();
  }

}