package data;

public class Vehicule {
	private int Id;
	private int ClasseId;
	private Classe VClasse;
	private String Fabricant;
	private String Marque;
	private int Annee;
	private int Kilometrage;
	private int Etat;
	private String Plaque;
	private Boolean Desactive;
	private int CapaciteEssence;
	private String Note;

	@Override
	public String toString() {
		return "Vehicule [Id=" + Id + ", ClasseId=" + ClasseId + ", VClasse=" + VClasse + ", Fabricant=" + Fabricant
				+ ", Marque=" + Marque + ", Annee=" + Annee + ", Kilometrage=" + Kilometrage + ", Etat=" + Etat
				+ ", Plaque=" + Plaque + ", Desactive=" + Desactive + ", CapaciteEssence=" + CapaciteEssence + ", Note="
				+ Note + "]";
	}

	public Vehicule(int id, int classeId, Classe vClasse, String fabricant, String marque, int annee, int kilometrage,
			int etat, String plaque, Boolean desactive, int capaciteEssence, String note) {
		super();
		Id = id;
		ClasseId = classeId;
		VClasse = vClasse;
		Fabricant = fabricant;
		Marque = marque;
		Annee = annee;
		Kilometrage = kilometrage;
		Etat = etat;
		Plaque = plaque;
		Desactive = desactive;
		CapaciteEssence = capaciteEssence;
		Note = note;
	}

	public int getClasseId() {
		return ClasseId;
	}

	public void setClasseId(int classeId) {
		ClasseId = classeId;
	}

	public Classe getVClasse() {
		return VClasse;
	}

	public void setVClasse(Classe vClasse) {
		VClasse = vClasse;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getFabricant() {
		return Fabricant;
	}

	public void setFabricant(String fabricant) {
		Fabricant = fabricant;
	}

	public String getMarque() {
		return Marque;
	}

	public void setMarque(String marque) {
		Marque = marque;
	}

	public int getAnnee() {
		return Annee;
	}

	public void setAnnee(int annee) {
		Annee = annee;
	}

	public int getKilometrage() {
		return Kilometrage;
	}

	public void setKilometrage(int kilometrage) {
		Kilometrage = kilometrage;
	}

	public int getEtat() {
		return Etat;
	}

	public void setEtat(int etat) {
		Etat = etat;
	}

	public String getPlaque() {
		return Plaque;
	}

	public void setPlaque(String plaque) {
		Plaque = plaque;
	}

	public Boolean getDesactive() {
		return Desactive;
	}

	public void setDesactive(Boolean desactive) {
		Desactive = desactive;
	}

	public int getCapaciteEssence() {
		return CapaciteEssence;
	}

	public void setCapaciteEssence(int capaciteEssence) {
		CapaciteEssence = capaciteEssence;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

}
