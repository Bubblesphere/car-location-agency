package data;

public class Classe {
  private int classeId;
  private String nom;
  private float prixJournalier;

  public int getClasseId() {
    return this.classeId;
  }

  public void setClasseId(int classeId) {
    this.classeId = classeId;
  }

  public String getNom() {
    return this.nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public float getPrixJournalier() {
    return this.prixJournalier;
  }

  public void setPrixJournalier(float prixJournalier) {
    this.prixJournalier = prixJournalier;
  }

  /**
   * Constructeur pour Classe à la selection
   * 
   * @param classeId
   *          id de la classe.
   * @param nom
   *          nom de la classe.
   * @param prixJournalier
   *          prix journalier de la classe.
   */
  public Classe(int classeId, String nom, float prixJournalier) {
    super();
    this.classeId = classeId;
    this.nom = nom;
    this.prixJournalier = prixJournalier;
  }

  /**
   * Constructeur pour Classe avant l'insertion
   * 
   * @param nom
   *          nom de la classe.
   * @param prixJournalier
   *          prix journalier de la classe.
   */
  public Classe(String nom, float prixJournalier) {
    super();
    this.nom = nom;
    this.prixJournalier = prixJournalier;
  }

}
