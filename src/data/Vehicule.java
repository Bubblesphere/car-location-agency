package data;

import ui.utils.IListable;

public class Vehicule implements IListable {
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
   * Constructeur pour V�hicule
   * 
   * @param Id
   *          id du v�hicule.
   * @param classe
   *          classe du v�hicule.
   * @param fabricant
   *          fabricant du v�hicule.
   * @param marque
   *          marque du v�hicule.
   * @param annee
   *          ann�e de construction du v�hicule.
   * @param kilometrage
   *          kilom�trage du v�hicule.
   * @param etat
   *          �tat du v�hicule[Fonctionnel, En r�paration, Retrait�].
   * @param plaque
   *          plaque du v�hicule.
   * @param desactive
   *          si le v�hicule est d�sactiv�.
   * @param capaciteEssence
   *          capacit� d'essence du v�hicule.
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
   * Constructeur pour V�hicule sans object de classe
   * 
   * @param Id
   *          id du v�hicule.
   * @param fabricant
   *          fabricant du v�hicule.
   * @param marque
   *          marque du v�hicule.
   * @param annee
   *          ann�e de construction du v�hicule.
   * @param kilometrage
   *          kilom�trage du v�hicule.
   * @param etat
   *          �tat du v�hicule[Fonctionnel, En r�paration, Retrait�].
   * @param plaque
   *          plaque du v�hicule.
   * @param desactive
   *          si le v�hicule est d�sactiv�.
   * @param capaciteEssence
   *          capacit� d'essence du v�hicule.
   * @param note
   *          commentaire.
   */
  public Vehicule(int vehiculeId, int classeId, Classe classe, String fabricant, String marque,
      int annee, int kilometrage, int etat, String plaque, Boolean desactive, int capaciteEssence,
      String note) {
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
   * Constructeur pour V�hicule avant insertion
   *
   * @param fabricant
   *          fabricant du v�hicule.
   * @param marque
   *          marque du v�hicule.
   * @param annee
   *          ann�e de construction du v�hicule.
   * @param kilometrage
   *          kilom�trage du v�hicule.
   * @param etat
   *          �tat du v�hicule[Fonctionnel, En r�paration, Retrait�].
   * @param plaque
   *          plaque du v�hicule.
   * @param desactive
   *          si le v�hicule est d�sactiv�.
   * @param capaciteEssence
   *          capacit� d'essence du v�hicule.
   * @param note
   *          commentaire.
   */
  public Vehicule(int vehiculeId, String fabricant, String marque, int annee, int kilometrage,
      int etat, String plaque, Boolean desactive, int capaciteEssence, String note) {
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
  
  /**
   * Constructeur pour V�hicule avant insertion
   *
   * @param fabricant
   *          fabricant du v�hicule.
   * @param marque
   *          marque du v�hicule.
 
   * @param desactive
   *          si le v�hicule est d�sactiv�.
   * @param capaciteEssence
   *          capacit� d'essence du v�hicule.
   * @param note
   *          commentaire.
   */
  public Vehicule(int vehiculeId, String fabricant, String marque) {
    this.id = vehiculeId;
    this.fabricant = fabricant;
    this.marque = marque;
    this.annee = 2017;
    this.kilometrage = 0;
    this.etat = 1;
    this.plaque = "";
    this.desactive = true;
    this.capaciteEssence = 0;
    this.note = "";
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

  @Override
public int getId() {
    return this.id;
  }

  public void setId(int vehiculeId) {
    this.id = vehiculeId;
  }

  @Override
  public String getDisplayedText() {
    String display = this.getFabricant() + " " + this.getMarque();
    if(this.getId() != -1){
      display = this.getId() + "-" + display;
    }
    return display;
  }

}
