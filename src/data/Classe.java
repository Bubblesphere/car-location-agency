package data;

public class Classe {
  private int id;
  private String nom;
  private float prixJournalier;

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
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
   * @param id
   *          id de la classe.
   * @param nom
   *          nom de la classe.
   * @param prixJournalier
   *           prix journalier de la classe.  
   */
  public Classe(int id, String nom, float prixJournalier) {
    super();
    this.id = id;
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
