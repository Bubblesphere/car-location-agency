package Data;

public class Paiement {
	private int Id;
	private int LocationId;
	private float Montant;
	private int Methode;
	private String Note;

	public Paiement(int id, int locationId, float montant, int methode, String note) {
		super();
		Id = id;
		LocationId = locationId;
		Montant = montant;
		Methode = methode;
		Note = note;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getLocationId() {
		return LocationId;
	}

	public void setLocationId(int locationId) {
		LocationId = locationId;
	}

	public float getMontant() {
		return Montant;
	}

	public void setMontant(float montant) {
		Montant = montant;
	}

	public int getMethode() {
		return Methode;
	}

	public void setMethode(int methode) {
		Methode = methode;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	@Override
	public String toString() {
		return "Paiement [Id=" + Id + ", LocationId=" + LocationId + ", Montant=" + Montant + ", Methode=" + Methode
				+ ", Note=" + Note + "]";
	}
}
