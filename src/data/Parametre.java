package data;

import java.time.LocalDate;

public class Parametre extends TypeParametre {
	private int Id;
	private float Valeur;
	private LocalDate DateDebut;
	private LocalDate DateFin;

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

	public LocalDate getDateDebut() {
		return DateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		DateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return DateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		DateFin = dateFin;
	}

	public Parametre(int typeId, String description, int id, float valeur, LocalDate dateDebut, LocalDate dateFin) {
		super(typeId, description);
		Id = id;
		Valeur = valeur;
		DateDebut = dateDebut;
		DateFin = dateFin;
	}

}
