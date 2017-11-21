package data;

public class Personne {
  private String nom;
  private String prenom;
  private String courriel;

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  /**
   * Constructeur pour Personne
   * 
   * @param nom
   *          nom de la personne.
   * @param prenom
   *          prenom de la personne.
   */
  public Personne(String nom, String prenom, String courriel) {
    super();
    this.nom = nom;
    this.prenom = prenom;
    this.setCourriel(courriel);
  }

  public Personne() {
    super();
  }

  public String getCourriel() {
    return courriel;
  }

  public void setCourriel(String courriel) {
    this.courriel = courriel;
  }

}
