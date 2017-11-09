import java.util.Date;

public class Location {
	private int Id;
	private Boolean HasInsurance;
	private Date DateReservation;
	private Date DatePriseDePosession;	
	private int KmDepart;
	private Retour RetourLocation;
	private Boolean KmIllimite;
	private Client Locateur;
	private Employe Locataire;
	private float Balance;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Boolean getHasInsurance() {
		return HasInsurance;
	}
	public void setHasInsurance(Boolean hasInsurance) {
		HasInsurance = hasInsurance;
	}
	public Date getDateReservation() {
		return DateReservation;
	}
	public void setDateReservation(Date dateReservation) {
		DateReservation = dateReservation;
	}
	public Date getDatePriseDePosession() {
		return DatePriseDePosession;
	}
	public void setDatePriseDePosession(Date datePriseDePosession) {
		DatePriseDePosession = datePriseDePosession;
	}
	public int getKmDepart() {
		return KmDepart;
	}
	public void setKmDepart(int kmDepart) {
		KmDepart = kmDepart;
	}
	public Retour getRetourLocation() {
		return RetourLocation;
	}
	public void setRetourLocation(Retour retourLocation) {
		RetourLocation = retourLocation;
	}
	public Boolean getKmIllimite() {
		return KmIllimite;
	}
	public void setKmIllimite(Boolean kmIllimite) {
		KmIllimite = kmIllimite;
	}
	public Client getLocateur() {
		return Locateur;
	}
	public void setLocateur(Client locateur) {
		Locateur = locateur;
	}
	public Employe getLocataire() {
		return Locataire;
	}
	public void setLocataire(Employe locataire) {
		Locataire = locataire;
	}
	public float getBalance() {
		return Balance;
	}
	public void setBalance(float balance) {
		Balance = balance;
	}
	
	
	
}
