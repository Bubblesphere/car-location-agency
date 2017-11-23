package data;

import java.time.LocalDate;

public class Parametre extends TypeParametre {
  private int parametreId;
  private float valeur;
  private LocalDate dateDebut;
  private LocalDate dateFin;

  public float getValeur() {
    return valeur;
  }

  public void setValeur(float valeur) {
    this.valeur = valeur;
  }

  public LocalDate getDateDebut() {
    return dateDebut;
  }

  public void setDateDebut(LocalDate dateDebut) {
    this.dateDebut = dateDebut;
  }

  public LocalDate getDateFin() {
    return dateFin;
  }

  public void setDateFin(LocalDate dateFin) {
    this.dateFin = dateFin;
  }

  /**
   * Constructeur pour Parametre
   * 
   * @param typeId
   *          id du type de paramètre.
   * @param description
   *          description du paramètre.
   * @param parametreId
   *          id du paramètre.
   * @param valeur
   *          valeur du paramètre.
   * @param dateDebut
   *          date d'entré en vigueur de ce paramètre.
   * @param dateFin
   *          date de désactivation de ce paramètre.
   */
  public Parametre(int typeId, String description, int parametreId, float valeur,
      LocalDate dateDebut, LocalDate dateFin) {
    super(typeId, description);
    this.parametreId = parametreId;
    this.valeur = valeur;
    this.dateDebut = dateDebut;
    this.dateFin = dateFin;
  }

  public int getParametreId() {
    return parametreId;
  }

  public void setParametreId(int parametreId) {
    this.parametreId = parametreId;
  }

}
