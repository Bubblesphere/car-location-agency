import java.util.Date;

public class Retour {
	private int KmArrive;
	private int LitreEssenceManquant;
	private Date DateRetour;
	private Float MontantReparation;
	private Employe Receveur;
	
	public int getKmArrive() {
		return KmArrive;
	}
	public void setKmArrive(int kmArrive) {
		KmArrive = kmArrive;
	}
	public int getLitreEssenceManquant() {
		return LitreEssenceManquant;
	}
	public void setLitreEssenceManquant(int litreEssenceManquant) {
		LitreEssenceManquant = litreEssenceManquant;
	}
	public Date getDateRetour() {
		return DateRetour;
	}
	public void setDateRetour(Date dateRetour) {
		DateRetour = dateRetour;
	}
	public Float getMontantReparation() {
		return MontantReparation;
	}
	public void setMontantReparation(Float montantReparation) {
		MontantReparation = montantReparation;
	}
	public Employe getReceveur() {
		return Receveur;
	}
	public void setReceveur(Employe receveur) {
		Receveur = receveur;
	}
	
	
}
