package data;

import java.time.LocalDate;

public class Reservation {
  private int reservationId;
  private Client client;
  private Classe classe;
  private LocalDate startDate;
  private LocalDate retourAnticipeDate;
  private String note;
  private Utilisateur reservateur;

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Classe getClasse() {
    return classe;
  }

  public void setClasse(Classe classe) {
    this.classe = classe;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getRetourAnticipeDate() {
    return retourAnticipeDate;
  }

  public void setRetourAnticipeDate(LocalDate retourAnticipeDate) {
    this.retourAnticipeDate = retourAnticipeDate;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  /**
   * Constructeur pour Reservation
   * 
   * @param reservationId
   *          id de la réservation.
   * @param client
   *          id du client de la la réservation.
   * @param classe
   *          la classe de véhicule désiré.
   * @param startDate
   *          la date de début de la location.
   * @param retourAnticipeDate
   *          la date de fin de la location.
   * @param note
   *          commentaire.
   * @param reservateur
   *          id de l'utilisateur qui a créé la réservation..
   */
  public Reservation(int reservationId, Client client, Classe classe, LocalDate startDate,
      LocalDate retourAnticipeDate, String note, Utilisateur reservateur) {
    super();
    this.setReservationId(reservationId);
    this.client = client;
    this.classe = classe;
    this.startDate = startDate;
    this.retourAnticipeDate = retourAnticipeDate;
    this.note = note;
    this.setReservateur(reservateur);
  }

  public int getReservationId() {
    return reservationId;
  }

  public void setReservationId(int reservationId) {
    this.reservationId = reservationId;
  }

  public Utilisateur getReservateur() {
    return reservateur;
  }

  public void setReservateur(Utilisateur reservateur) {
    this.reservateur = reservateur;
  }

}
