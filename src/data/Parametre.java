package data;

import java.util.Date;


public class Parametre extends TypeParametre{	
	private int Id;
	private float Valeur;
	private Date DateDebut;
	private Date DateFin;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public float getValeur() {
		return Valeur;
	}

	public void setValeur(float valeur) {
		Valeur = valeur;
	}

	public Date getDateDebut() {
		return DateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		DateDebut = dateDebut;
	}

	public Date getDateFin() {
		return DateFin;
	}

	public void setDateFin(Date dateFin) {
		DateFin = dateFin;
	}

	public Parametre(int typeId, String description, int id, float valeur, Date dateDebut, Date dateFin) {
		super(typeId, description);
		Id = id;
		Valeur = valeur;
		DateDebut = dateDebut;
		DateFin = dateFin;
	}
	
}
