package data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import dao.ParametreDao;
import ui.utils.IListable;

public class Location extends Reservation implements IListable {
    private int locationId;
    private Vehicule vehicule;
    private Utilisateur utilisateurLocation;
    private LocalDateTime dateDeRetour;
    private boolean assurance;
    private boolean usureJournalier;
    private int essenceManquant;
    private int departKm;
    private int retourKm;
    private String noteLocation;
    private String noteRetour;
    private float estimationReparation;
    private List<Paiement> paiements;

    public void addPaiement(Paiement paiement) {
        this.paiements.add(paiement);
    }

    public float getCurrentSubTotal() {
        float subTotal = 0;
        for (Paiement p : this.paiements) {
            subTotal += p.getMontant();
        }
        return subTotal;
    }

    public float getTotalPrice() {
		Boolean hasDepot = false;
		for(Paiement p:this.paiements) {
			if(p.getMethode() == 0) {
				hasDepot = true;
				break;
			}
		}
		int distanceTotal = this.departKm - this.retourKm;
		 float prixRetard = 0;
		 float prixDeLocation = 0;
		 float prixUsure = 0;
		 float prixAssurance = 0;
		 float prixEssence = 0;
		
		if (this.dateDeRetour != null){
		    int numberOfDays = (int) ChronoUnit.DAYS.between(this.startDate, this.dateDeRetour);
	        int hoursOver = Math.min((int) ChronoUnit.HOURS.between(this.finDate, this.dateDeRetour), 10);
	        
	        prixAssurance = this.assurance ? ParametreDao.retrieveByType(1).getValeur() : 0;	        
	        
	        if(this.usureJournalier) {
	            prixUsure = numberOfDays * ParametreDao.retrieveByType(3).getValeur();
	        }else {
	            prixUsure = Math.max(0, distanceTotal - 400) * ParametreDao.retrieveByType(2).getValeur();
	        }               
	        prixDeLocation = numberOfDays * this.vehicule.getVClasse().getPrixJournalier();
	        prixRetard = hoursOver * (this.vehicule.getVClasse().getPrixJournalier() / 10); 
	        prixEssence = this.essenceManquant * ParametreDao.retrieveByType(4).getValeur();
		}
		
		float creditDepot = hasDepot ? ParametreDao.retrieveByType(5).getValeur() : 0;
		
		return this.estimationReparation + prixRetard + prixDeLocation + prixUsure + prixAssurance + prixEssence - creditDepot;	
    }

    /**
     * Constructeur pour une Location
     * @param reservationId         id de la reservation.
     * @param clientReservation     client de la reservation
     * @param classeReservation     classe de vehicule désirée par le locataire
     * @param startDate             date de depart prevue par le locataire.
     * @param finDate               date de retour prevue par le locataire.
     * @param noteLocation          Note au moment de la location
     * @param utilisateurReservation utilisateur ayant enregistré la reservation
     * @param locationId            id de la location
     * @param vehicule              v�hicule de la locaiton.
     * @param utilisateurLocation   utilisateur ayant confirmé la location
     * @param dateDeRetour          date réelle de retour
     * @param assurance             si le locataire a prit l'assurance.
     * @param usureJournalier       si le locataire a prit le forfait d'usure journalier.
     * @param essenceManquant       essence manquant en litre.
     * @param departKm              kilom�trage du v�hicule au d�part.
     * @param retourKm              kilom�trage du v�hicule au retour.
     * @param noteReservation       note au moment de la reservation
     * @param estimationReparation  estimation de r�paration � faire au v�hicule au retour.
     * @param noteRetour            note au moment du retour
     */
    public Location(int reservationId, Client clientReservation, Classe classeReservation, LocalDateTime startDate,
                    LocalDateTime finDate, String noteLocation, Utilisateur utilisateurReservation, int locationId,
                    Vehicule vehicule, Utilisateur utilisateurLocation, LocalDateTime dateDeRetour, boolean assurance,
                    boolean usureJournalier, int essenceManquant, int departKm, int retourKm, String noteReservation,
                    float estimationReparation, String noteRetour) {
        super(reservationId, clientReservation, classeReservation, startDate, finDate, noteReservation,
                utilisateurReservation);
        this.locationId = locationId;
        this.vehicule = vehicule;
        this.utilisateurLocation = utilisateurLocation;
        this.dateDeRetour = dateDeRetour;
        this.assurance = assurance;
        this.usureJournalier = usureJournalier;
        this.essenceManquant = essenceManquant;
        this.departKm = departKm;
        this.retourKm = retourKm;
        this.noteLocation = noteLocation;
        this.estimationReparation = estimationReparation;
        this.noteRetour = noteRetour;
        this.paiements = new ArrayList<Paiement>();
    }

    public Location(int locationId) {
        super();
        this.locationId = locationId;
        this.vehicule = null;
        this.utilisateurLocation = null;
        this.dateDeRetour = null;
        this.assurance = false;
        this.usureJournalier = false;
        this.essenceManquant = 0;
        this.departKm = 0;
        this.retourKm = 0;
        this.noteLocation = "";
        this.estimationReparation = 0;
        this.paiements = new ArrayList<Paiement>();
    }

    public void setReservation(Reservation reservation) {
        this.reservationId = reservation.getReservationId();
        this.clientReservation = reservation.getClientReservation();
        this.classeReservation = reservation.getClasseReservation();
        this.startDate = reservation.getStartDate();
        this.finDate = reservation.getFinDate();
        this.noteReservation = reservation.getNoteReservation();
        this.utilisateurReservation = reservation.getUtilisateurReservation();
        this.paiements = new ArrayList<Paiement>();
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public Utilisateur getUtilisateurLocation() {
        return utilisateurLocation;
    }

    public void setUtilisateurLocation(Utilisateur utilisateurLocation) {
        this.utilisateurLocation = utilisateurLocation;
    }

    public LocalDateTime getDateDeRetour() {
        return dateDeRetour;
    }

    public void setDateDeRetour(LocalDateTime dateDeRetour) {
        this.dateDeRetour = dateDeRetour;
    }

    public boolean isAssurance() {
        return assurance;
    }

    public void setAssurance(boolean assurance) {
        this.assurance = assurance;
    }

    public boolean isUsureJournalier() {
        return usureJournalier;
    }

    public void setUsureJournalier(boolean usureJournalier) {
        this.usureJournalier = usureJournalier;
    }

    public int getEssenceManquant() {
        return essenceManquant;
    }

    public void setEssenceManquant(int essenceManquant) {
        this.essenceManquant = essenceManquant;
    }

    public int getDepartKm() {
        return departKm;
    }

    public void setDepartKm(int departKm) {
        this.departKm = departKm;
    }

    public int getRetourKm() {
        return retourKm;
    }

    public void setRetourKm(int retourKm) {
        this.retourKm = retourKm;
    }

    public String getNoteLocation() {
        return noteLocation;
    }

    public void setNoteLocation(String noteLocation) {
        this.noteLocation = noteLocation;
    }

    public String getNoteRetour() {
        return noteRetour;
    }

    public void setNoteRetour(String noteRetour) {
        this.noteRetour = noteRetour;
    }

    public float getEstimationReparation() {
        return estimationReparation;
    }

    public void setEstimationReparation(float estimationReparation) {
        this.estimationReparation = estimationReparation;
    }

    @Override
    public int getId() {
        return locationId;
    }

    public void setId(int locationId) {
        this.locationId = locationId;
    }

    @Override
    public String getDisplayedText() {
        return this.getClientReservation().getPrenom() + " " + this.getClientReservation().getNom() + " - "
                + this.getStartDate().toString();
    }

    /**
     * Affichage formaté des paiements effectuée
     * @return
     */
    public String getPaiementsDisplay() {
        List<Paiement> paiementsEffectues = this.getPaiements();

        String paiementsString = "";

        if (paiementsEffectues != null && paiementsEffectues.size() > 0) {
            for (Paiement paiement : paiementsEffectues) {
                paiementsString += paiement.getDisplayedText() + "<br />";
            }
            return "<html>Paiements<br />" + paiementsString + "</html>";
        } else {
            return "Aucun paiement enregistr\u00E9.";
        }
    }

    public List<Paiement> getPaiements() {
        return paiements;
    }

    public void setPaiements(List<Paiement> paiements) {
        this.paiements = paiements;
    }

}
