package data;

import java.time.LocalDate;

public class Client extends Personne {
  private int id;
  private String adresse;
  private String numeroPermis;
  private String numeoTelphone;
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
   * Constructeur pour Client lors de la r�servation
   * 
   * @param nom
   *          nom du client.
   * @param prenom
   *          pr�nom du client.
   * @param numeoTelphone
   *          num�ro de t�l�phone du client.
   * @param note
   *          commentaire.
   */
  public Client(String nom, String prenom, String numeoTelphone, String note) {
    super(nom, prenom, "");
    this.numeoTelphone = numeoTelphone;
    this.note = note;
    this.adresse = "";
    this.numeroPermis = "";
    this.dateDeNaissance = null;
  }

  /**
   * Constructeur pour Client
   * 
   * @param nom
   *          nom du client.
   * @param prenom
   *          pr�nom du client.
   * @param id
   *          id du client.
   * @param adresse
   *          adresse du client.
   * @param numeroPermis
   *          num�ro de permis du client.
   * @param numeoTelphone
   *          num�ro de t�l�phone du client.
   * @param courriel
   *          courriel du client.
   * @param note
   *          commentaire.
   */
  public Client(String nom, String prenom, int id, String adresse, String numeroPermis,
      String numeoTelphone, String courriel, String note, LocalDate dateDeNaissance) {
    super(nom, prenom, courriel);
    this.id = id;
    this.adresse = adresse;
    this.numeroPermis = numeroPermis;
    this.numeoTelphone = numeoTelphone;
    this.note = note;
    this.dateDeNaissance = dateDeNaissance;
  }

  /**
   * Constructeur pour Client � la cr�ation
   * 
   * @param nom
   *          nom du client.
   * @param prenom
   *          pr�nom du client.
   * @param adresse
   *          adresse du client.
   * @param numeroPermis
   *          num�ro de permis du client.
   * @param numeoTelphone
   *          num�ro de t�l�phone du client.
   * @param courriel
   *          courriel du client.
   * @param note
   *          commentaire.
   */
  public Client(String nom, String prenom, String adresse, String numeroPermis,
      String numeoTelphone, String courriel, String note, LocalDate dateDeNaissance) {
    super(nom, prenom, courriel);
    this.adresse = adresse;
    this.numeroPermis = numeroPermis;
    this.numeoTelphone = numeoTelphone;
    this.note = note;
    this.dateDeNaissance = dateDeNaissance;
  }
}
