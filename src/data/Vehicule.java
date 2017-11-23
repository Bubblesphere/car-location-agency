package data;

public class Vehicule {
  private int id;
  private Classe classe;
  private String fabricant;
  private String marque;
  private int annee;
  private int kilometrage;
  private int etat;
  private String plaque;
  private Boolean desactive;
  private int capaciteEssence;
  private String note;

  /**
   * Constructeur pour Véhicule
   * 
   * @param Id
   *          id du véhicule.
   * @param classe
   *          classe du véhicule.
   * @param fabricant
   *          fabricant du véhicule.
   * @param marque
   *          marque du véhicule.
   * @param annee
   *          année de construction du véhicule.
   * @param kilometrage
   *          kilométrage du véhicule.
   * @param etat
   *          état du véhicule[Fonctionnel, En réparation, Retraité].
   * @param plaque
   *          plaque du véhicule.
   * @param desactive
   *          si le véhicule est désactivé.
   * @param capaciteEssence
   *          capacité d'essence du véhicule.
   * @param note
   *          commentaire.
   */
  public Vehicule(int vehiculeId, Classe classe, String fabricant, String marque, int annee,
      int kilometrage, int etat, String plaque, Boolean desactive, int capaciteEssence,
      String note) {
    super();
    this.setId(vehiculeId);
    this.classe = classe;
    this.fabricant = fabricant;
    this.marque = marque;
    this.annee = annee;
    this.kilometrage = kilometrage;
    this.etat = etat;
    this.plaque = plaque;
    this.desactive = desactive;
    this.capaciteEssence = capaciteEssence;
    this.note = note;
  }

  /**
   * Constructeur pour Véhicule sans object de classe
   * 
   * @param Id
   *          id du véhicule.
   * @param fabricant
   *          fabricant du véhicule.
   * @param marque
   *          marque du véhicule.
   * @param annee
   *          année de construction du véhicule.
   * @param kilometrage
   *          kilométrage du véhicule.
   * @param etat
   *          état du véhicule[Fonctionnel, En réparation, Retraité].
   * @param plaque
   *          plaque du véhicule.
   * @param desactive
   *          si le véhicule est désactivé.
   * @param capaciteEssence
   *          capacité d'essence du véhicule.
   * @param note
   *          commentaire.
   */
  public Vehicule(int vehiculeId, int classeId, Classe classe, String fabricant, String marque, int annee, int kilometrage,
      int etat, String plaque, Boolean desactive, int capaciteEssence, String note) {
    super();
    this.setId(vehiculeId);
    this.classe = classe;
    this.classe.setId(classeId);
    this.fabricant = fabricant;
    this.marque = marque;
    this.annee = annee;
    this.kilometrage = kilometrage;
    this.etat = etat;
    this.plaque = plaque;
    this.desactive = desactive;
    this.capaciteEssence = capaciteEssence;
    this.note = note;
  }

  /**
   * Constructeur pour Véhicule avant insertion
   *
   * @param fabricant
   *          fabricant du véhicule.
   * @param marque
   *          marque du véhicule.
   * @param annee
   *          année de construction du véhicule.
   * @param kilometrage
   *          kilométrage du véhicule.
   * @param etat
   *          état du véhicule[Fonctionnel, En réparation, Retraité].
   * @param plaque
   *          plaque du véhicule.
   * @param desactive
   *          si le véhicule est désactivé.
   * @param capaciteEssence
   *          capacité d'essence du véhicule.
   * @param note
   *          commentaire.
   */
  public Vehicule(int vehiculeId, String fabricant, String marque, int annee, int kilometrage, int etat,
      String plaque, Boolean desactive, int capaciteEssence, String note) {
	  this.id = vehiculeId;
    this.fabricant = fabricant;
    this.marque = marque;
    this.annee = annee;
    this.kilometrage = kilometrage;
    this.etat = etat;
    this.plaque = plaque;
    this.desactive = desactive;
    this.capaciteEssence = capaciteEssence;
    this.note = note;
  }
  
  public Classe getVClasse() {
    return this.classe;
  }

  public void setVClasse(Classe classe) {
    this.classe = classe;
  }

  public String getFabricant() {
    return this.fabricant;
  }

  public void setFabricant(String fabricant) {
    this.fabricant = fabricant;
  }

  public String getMarque() {
    return this.marque;
  }

  public void setMarque(String marque) {
    this.marque = marque;
  }

  public int getAnnee() {
    return this.annee;
  }

  public void setAnnee(int annee) {
    this.annee = annee;
  }

  public int getKilometrage() {
    return this.kilometrage;
  }

  public void setKilometrage(int kilometrage) {
    this.kilometrage = kilometrage;
  }

  public int getEtat() {
    return this.etat;
  }

  public void setEtat(int etat) {
    this.etat = etat;
  }

  public String getPlaque() {
    return this.plaque;
  }

  public void setPlaque(String plaque) {
    this.plaque = plaque;
  }

  public Boolean getDesactive() {
    return this.desactive;
  }

  public void setDesactive(Boolean desactive) {
    this.desactive = desactive;
  }

  public int getCapaciteEssence() {
    return this.capaciteEssence;
  }

  public void setCapaciteEssence(int capaciteEssence) {
    this.capaciteEssence = capaciteEssence;
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

  public void setId(int vehiculeId) {
    this.id = vehiculeId;
  }

}
