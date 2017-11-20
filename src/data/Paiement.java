package data;

public class Paiement {
  private int id;
  private int locationId;
  private float montant;
  private int methode;
  private String note;

  /**
   * Constructeur pour Paiement
   * 
   * @param id
   *          id du paiement.
   * @param locationId
   *          location sur laquel ce paiement est attaché.
   * @param montant
   *          montant du paiement.
   * @param methode
   *          méthode de paiement.
   * @param note
   *          commentaire.
   */
  public Paiement(int id, int locationId, float montant, int methode, String note) {
    super();
    this.id = id;
    this.locationId = locationId;
    this.montant = montant;
    this.methode = methode;
    this.note = note;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getLocationId() {
    return locationId;
  }

  public void setLocationId(int locationId) {
    this.locationId = locationId;
  }

  public float getMontant() {
    return montant;
  }

  public void setMontant(float montant) {
    this.montant = montant;
  }

  public int getMethode() {
    return methode;
  }

  public void setMethode(int methode) {
    this.methode = methode;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

}
