package data;

import java.time.LocalDate;

public class Reservation implements IListable {
  private int reservationId;
  private Client clientReservation;
  private Classe classeReservation;
  private LocalDate startDate;
  private LocalDate finDate;
  private String noteReservation;
  private Utilisateur utilisateurReservation;



  public Client getClientReservation() {
    return clientReservation;
  }

  public void setClientReservation(Client clientReservation) {
    this.clientReservation = clientReservation;
  }

  public Classe getClasseReservation() {
    return classeReservation;
  }

  public void setClasseReservation(Classe classeReservation) {
    this.classeReservation = classeReservation;
  }

  public Utilisateur getUtilisateurReservation() {
    return utilisateurReservation;
  }

  public void setUtilisateurReservation(Utilisateur utilisateurReservation) {
    this.utilisateurReservation = utilisateurReservation;
  }

  public int getReservationId() {
    return reservationId;
  }

  public void setReservationId(int reservationId) {
    this.reservationId = reservationId;
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

  public String getNoteReservation() {
    return noteReservation;
  }

  public void setNoteReservation(String noteReservation) {
    this.noteReservation = noteReservation;
  }

  

  /**
   * Constructeur pour Reservation
   * 
   * @param reservationId
   *          id de la réservation.
   * @param clientReservation
   *          id du client de la la réservation.
   * @param classeReservation
   *          la classe de véhicule désiré.
   * @param startDate
   *          la date de début de la location.
   * @param finDate
   *          la date de fin de la location.
   * @param noteReservation
   *          commentaire.   
   * @param utilisateurReservation
   *          id de l'utilisateur qui a créé la réservation..
   */
  public Reservation(int reservationId, Client clientReservation, Classe classeReservation,
      LocalDate startDate, LocalDate finDate, String noteReservation, Utilisateur utilisateurReservation) {
    super();
    this.reservationId = reservationId;    
    this.clientReservation = clientReservation;    
    this.classeReservation = classeReservation;
    this.startDate = startDate;
    this.finDate = finDate;
    this.noteReservation = noteReservation;    
    this.utilisateurReservation = utilisateurReservation;
  }

  @Override
  public String getDisplayedText() {
    return this.getClientReservation().getPrenom() + " " + this.getClientReservation().getNom() + " - "
        + this.getStartDate().toString();
  }

}