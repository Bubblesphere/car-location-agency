package data;

import java.text.NumberFormat;
import java.time.LocalDate;

import ui.utils.IListable;

public class Parametre extends TypeParametre implements IListable {
  private int id;
  private float valeur;
  private LocalDate dateDebut;
  private LocalDate dateFin;

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
   *          id du type de param�tre.
   * @param description
   *          description du param�tre.
   * @param id
   *          id du param�tre.
   * @param valeur
   *          valeur du param�tre.
   * @param dateDebut
   *          date d'entr� en vigueur de ce param�tre.
   * @param dateFin
   *          date de d�sactivation de ce param�tre.
   */
  public Parametre(int typeId, String description, int id, float valeur, LocalDate dateDebut,
      LocalDate dateFin) {
    super(typeId, description);
    this.id = id;
    this.valeur = valeur;
    this.dateDebut = dateDebut;
    this.dateFin = dateFin;
  }
  
  /**
   * Constructeur pour Parametre
   * 
   * @param typeId
   *          id du type de param�tre.
   * @param valeur
   *          valeur du param�tre.
   */
  public Parametre(int typeId, float valeur, String description) {
    super(typeId, description);    
    this.valeur = valeur;
    this.dateDebut = LocalDate.now();
    this.dateFin = null;
  }

  @Override
  public String getDisplayedText() {
	  NumberFormat formatter = NumberFormat.getCurrencyInstance();
	  return this.getDescription() + " (" + formatter.format(this.getValeur()) + ")";
  }

	@Override
	public int getId() {
		return this.getTypeId();
	}

}