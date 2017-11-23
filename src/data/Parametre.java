package data;

import java.time.LocalDate;

public class Parametre extends TypeParametre {
  private int id;
  private float valeur;
  private LocalDate dateDebut;
  private LocalDate dateFin;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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
   * @param id
   *          id du paramètre.
   * @param valeur
   *          valeur du paramètre.
   * @param dateDebut
   *          date d'entré en vigueur de ce paramètre.
   * @param dateFin
   *          date de désactivation de ce paramètre.
   */
  public Parametre(int typeId, String description, int id, float valeur, LocalDate dateDebut,
      LocalDate dateFin) {
    super(typeId, description);
    this.id = id;
    this.valeur = valeur;
    this.dateDebut = dateDebut;
    this.dateFin = dateFin;
  }

}