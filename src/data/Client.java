package data;

import java.time.LocalDate;

public class Client extends Personne implements IListable {
  private int id;
  private String adresse;
  private String numeroPermis;
  private String numeoTelphone;
  private String courriel;
  private String note;
  private LocalDate dateDeNaissance;

  public String getAdresse() {
    return this.adresse;
  }

  public void setAdresse(String adresse) {
    this.adresse = adresse;
  }

  public String getNumeroPermis() {
    return this.numeroPermis;
  }

  public void setNumeroPermis(String numeroPermis) {
    this.numeroPermis = numeroPermis;
  }

  public String getNumeoTelphone() {
    return this.numeoTelphone;
  }

  public void setNumeoTelphone(String numeoTelphone) {
    this.numeoTelphone = numeoTelphone;
  }

  public String getCourriel() {
    return this.courriel;
  }

  public void setCourriel(String courriel) {
    this.courriel = courriel;
  }

  public String getNote() {
    return this.note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDate getDateDeNaissance() {
    return this.dateDeNaissance;
  }

  public void setDateDeNaissance(LocalDate dateDeNaissance) {
    this.dateDeNaissance = dateDeNaissance;
  }

  /**
   * Constructeur pour Client lors de la réservation
   * 
   * @param nom
   *          nom du client.
   * @param prenom
   *          prénom du client.
   * @param numeoTelphone
   *          numéro de téléphone du client.
   * @param note
   *          commentaire.
   */
  public Client(String nom, String prenom, String numeoTelphone, String note) {
    super(nom, prenom);
    this.numeoTelphone = numeoTelphone;
    this.note = note;
    this.adresse = "";
    this.numeroPermis = "";
    this.courriel = "";
    this.dateDeNaissance = null;
  }

  /**
   * Constructeur pour Client
   * 
   * @param nom
   *          nom du client.
   * @param prenom
   *          prénom du client.
   * @param id
   *          id du client.
   * @param adresse
   *          adresse du client.
   * @param numeroPermis
   *          numéro de permis du client.
   * @param numeoTelphone
   *          numéro de téléphone du client.
   * @param courriel
   *          courriel du client.
   * @param note
   *          commentaire.
   * @param dateDeNaissance
   *          date de naissance.
   */
  public Client(String nom, String prenom, int id, String adresse, String numeroPermis,
      String numeoTelphone, String courriel, String note, LocalDate dateDeNaissance) {
    super(nom, prenom);
    this.id = id;
    this.adresse = adresse;
    this.numeroPermis = numeroPermis;
    this.numeoTelphone = numeoTelphone;
    this.courriel = courriel;
    this.note = note;
    this.dateDeNaissance = dateDeNaissance;
  }

  /**
   * Constructeur pour Client à la création
   * 
   * @param nom
   *          nom du client.
   * @param prenom
   *          prénom du client.
   * @param adresse
   *          adresse du client.
   * @param numeroPermis
   *          numéro de permis du client.
   * @param numeoTelphone
   *          numéro de téléphone du client.
   * @param courriel
   *          courriel du client.
   * @param note
   *          commentaire.
   * @param dateDeNaissance
   *          date de naissance.
   */
  public Client(String nom, String prenom, String adresse, String numeroPermis,
      String numeoTelphone, String courriel, String note, LocalDate dateDeNaissance) {
    super(nom, prenom);
    this.adresse = adresse;
    this.numeroPermis = numeroPermis;
    this.numeoTelphone = numeoTelphone;
    this.courriel = courriel;
    this.note = note;
    this.dateDeNaissance = dateDeNaissance;
  }

  /**
   * Constructeur pour la widget list
   * 
   * @param id
   *          id du client.
   * @param nom
   *          nom du client.
   * @param prenom
   *          prénom du client.
   */
  public Client(int id, String nom, String prenom) {
    super(nom, prenom);
    this.id = id;
  }

  @Override
  public String getDisplayedText() {
    String display = this.getPrenom() + " " + this.getNom();
    if(this.getId() != -1){
      display = this.getId() + "-" + display;
    }
    return display;
  }
}