package data;

public class Client extends Personne {
  private int id;
  private String adresse;
  private String numeroPermis;
  private String numeoTelphone;
  private String courriel;
  private String note;

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
  }

  /**
   * Constructeur pour Client
   * 
   * @param nom
   *          nom du client.
   * @param prenom
   *          prénom du client.
   * @param id
   *           id du client.
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
   */
  public Client(String nom, String prenom, int id, String adresse, String numeroPermis,
      String numeoTelphone, String courriel, String note) {
    super(nom, prenom);
    this.id = id;
    this.adresse = adresse;
    this.numeroPermis = numeroPermis;
    this.numeoTelphone = numeoTelphone;
    this.courriel = courriel;
    this.note = note;
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
   */
  public Client(String nom, String prenom, String adresse, String numeroPermis,
      String numeoTelphone, String courriel, String note) {
    super(nom, prenom);
    this.adresse = adresse;
    this.numeroPermis = numeroPermis;
    this.numeoTelphone = numeoTelphone;
    this.courriel = courriel;
    this.note = note;
  }
}
