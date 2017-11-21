package data;

public class Paiement {
  private int paiementId;
  private Location location;
  private float montant;
  private int methode;
  private String note;

  /**
   * Constructeur pour Paiement
   * 
   * @param paiementId
   *          id du paiement.
   * @param location
   *          location sur laquel ce paiement est attaché.
   * @param montant
   *          montant du paiement.
   * @param methode
   *          méthode de paiement.
   * @param note
   *          commentaire.
   */
  public Paiement(int paiementId, Location location, float montant, int methode, String note) {
    super();
    this.paiementId = paiementId;
    this.setLocation(location);
    this.montant = montant;
    this.methode = methode;
    this.note = note;
  }

  public int getPaiementId() {
    return paiementId;
  }

  public void setPaiementId(int paiementId) {
    this.paiementId = paiementId;
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

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

}
