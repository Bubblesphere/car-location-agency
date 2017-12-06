package data;

import security.PasswordSecurity;
import ui.utils.IListable;

public class Utilisateur extends Personne implements IListable {
  private int id;
  private String motDePasse;
  private String courriel;
  private int numEmploye;
  private int role;
  private Boolean desactive;

  /**
   * Constructor for Utilisateur on the login
   * 
   * @param motDePasse
   *          mot de passe de l'utilisateur.
   * @param numEmploye
   *          numéro d'employe de l'utilisateur
   */
  public Utilisateur(String motDePasse, int numEmploye) {
    super();
    setMotDePasse(motDePasse);
    this.numEmploye = numEmploye;
  }

  /**
   * Constructeur pour Utilisateur à la sélection
   * 
   * @param nom
   *          nom de l'utilisateur.
   * @param prenom
   *          prénom de l'utilisateur.
   * @param id
   *          id de l'utilisateur.
   * @param courriel
   *          courriel de l'utilisateur.
   * @param numEmploye
   *          numéro d'employé de l'utilisateur.
   * @param role
   *          rôle de l'utilisateur. [Employé, Superviseur]
   * @param desactive
   *          si l'utilisateur est désactivé.
   */
  public Utilisateur(String nom, String prenom, int id, String courriel, int numEmploye, int role,
      Boolean desactive) {
    super(nom, prenom);
    this.id = id;
    this.courriel = courriel;
    this.numEmploye = numEmploye;
    this.role = role;
    this.desactive = desactive;
  }

  /**
   * Constructeur pour Utilisateur
   * 
   * @param nom
   *          nom de l'utilisateur.
   * @param prenom
   *          prénom de l'utilisateur.
   * @param id
   *          id de l'utilisateur.
   * @param motDePasse
   *          mot de passe de l'utilisateur.
   * @param courriel
   *          courriel de l'utilisateur.
   * @param numEmploye
   *          numéro d'employé de l'utilisateur.
   * @param role
   *          rôle de l'utilisateur. [Employé, Superviseur]
   * @param desactive
   *          si l'utilisateur est désactivé.
   */
  public Utilisateur(String nom, String prenom, int id, String motDePasse, String courriel,
      int numEmploye, int role, Boolean desactive) {
    super(nom, prenom);
    this.id = id;
    setMotDePasse(motDePasse);
    this.courriel = courriel;
    this.numEmploye = numEmploye;
    this.role = role;
    this.desactive = desactive;
  }

  /**
   * Constructeur pour Utilisateur à la création
   * 
   * @param nom
   *          nom de l'utilisateur.
   * @param prenom
   *          prénom de l'utilisateur.
   * @param motDePasse
   *          mot de passe de l'utilisateur.
   * @param courriel
   *          courriel de l'utilisateur.
   * @param numEmploye
   *          numéro d'employé de l'utilisateur.
   * @param role
   *          rôle de l'utilisateur. [Employé, Superviseur]
   * @param desactive
   *          si l'utilisateur est désactivé.
   */
  public Utilisateur(String nom, String prenom, String motDePasse, String courriel, int numEmploye,
      int role, Boolean desactive) {
    super(nom, prenom);
    setMotDePasse(motDePasse);
    this.courriel = courriel;
    this.numEmploye = numEmploye;
    this.role = role;
    this.desactive = desactive;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCourriel() {
    return courriel;
  }

  public void setCourriel(String courriel) {
    this.courriel = courriel;
  }

  public int getNumEmploye() {
    return numEmploye;
  }

  public void setNumEmploye(int numEmploye) {
    this.numEmploye = numEmploye;
  }

  public int getRole() {
    return role;
  }

  public void setRole(int role) {
    this.role = role;
  }

  public Boolean getDesactive() {
    return desactive;
  }

  public void setDesactive(Boolean desactive) {
    this.desactive = desactive;
  }

  public String getMotDePasse() {
    return motDePasse;
  }

  public void setMotDePasse(String motDePasse) {
    this.motDePasse = PasswordSecurity.hashPassword(motDePasse);
  }

	@Override
	public String getDisplayedText() {
		return this.getPrenom();
	}

}