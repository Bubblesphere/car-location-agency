package data;

import java.time.LocalDate;

import ui.utils.IListable;

public class Reservation implements IListable {
  protected int reservationId;
  protected Client clientReservation;
  protected Classe classeReservation;
  protected LocalDate startDate;
  protected LocalDate finDate;
  protected String noteReservation;
  protected Utilisateur utilisateurReservation;



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
   *          id de la r�servation.
   */
  public Reservation(int reservationId) {
    super();
    this.reservationId = reservationId;    
    this.clientReservation = null;    
    this.classeReservation = null;
    this.startDate = LocalDate.now();
    this.finDate = LocalDate.now();;
    this.noteReservation = "";    
    this.utilisateurReservation = null;
  }
  
  public Reservation() {
	    super();
	    this.reservationId = -1;    
	    this.clientReservation = null;    
	    this.classeReservation = null;
	    this.startDate = LocalDate.now();
	    this.finDate = LocalDate.now();;
	    this.noteReservation = "";    
	    this.utilisateurReservation = null;
	  }

  /**
   * Constructeur pour Reservation
   * 
   * @param reservationId
   *          id de la r�servation.
   * @param clientReservation
   *          id du client de la la r�servation.
   * @param classeReservation
   *          la classe de v�hicule d�sir�.
   * @param startDate
   *          la date de d�but de la location.
   * @param finDate
   *          la date de fin de la location.
   * @param noteReservation
   *          commentaire.   
   * @param utilisateurReservation
   *          id de l'utilisateur qui a cr�� la r�servation..
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
    if(this.getReservationId() == -1){
      return this.getStartDate().toString();
    }else{
      return this.getClientReservation().getPrenom() + " " + this.getClientReservation().getNom() + " - "
          + this.getStartDate().toString();
    }
    
  }
  
  @Override
  public int getId() {
	  return this.reservationId;
  }

}