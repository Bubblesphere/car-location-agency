package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.ClasseDao;
import dao.ClientDao;
import dao.LocationDao;
import dao.ParametreDao;
import dao.ReservationDao;
import dao.UtilisateurDao;
import dao.VehiculeDao;
import data.Classe;
import data.Client;
import data.Location;
import data.Paiement;
import data.Parametre;
import data.Reservation;
import data.Utilisateur;
import data.Vehicule;
import ui.business.form.WFormClient;
import ui.business.form.WFormLocation;
import ui.business.form.WFormParametre;
import ui.business.form.WFormReservation;
import ui.business.form.WFormRetour;
import ui.business.form.WFormVehicule;
//import security.Login;
import ui.events.Event;
import ui.events.EventEnum;
import ui.events.EventListener;
import ui.widgets.WTabFormList;
import ui.widgets.WTabFormListAdd;

public class TabbedWindow extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  
  private WTabFormListAdd<Client> tabClient;
  private WTabFormListAdd<Reservation> tabReservation;
  private WTabFormListAdd<Location> tabLocation;
  private WTabFormListAdd<Location> tabRetour;
  private WTabFormListAdd<Vehicule> tabVehicule;
  private WTabFormList<Parametre> tabParametre;
  
  private WFormClient formClient;
  private WFormReservation formReservation;
  private WFormLocation formLocation;
  private WFormRetour formRetour;
  private WFormVehicule formVehicule;
  private WFormParametre formParametre;
  
  
  ArrayList<Classe> classes;
  ArrayList<Location> retours;
  ArrayList<Reservation> reservations;
  ArrayList<Parametre> parametres;
  ArrayList<Client> clients;
  ArrayList<Location> locations;
  ArrayList<Vehicule> vehicules;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          TabbedWindow frame = new TabbedWindow();
          frame.setMinimumSize(new Dimension(860, 680));
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public TabbedWindow() {
//    setIconImage(Toolkit.getDefaultToolkit().getImage(TabbedWindow.class
  //      .getResource("/com/sun/javafx/scene/control/skin/modena/dialog-more-details@2x.png")));
    //Login login = new Login(); //TODO remettre avant la remise
    //Utilisateur user1 = login.authenticate(this);

    setTitle("Syst\u00E8me de gestion de location");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 674, 670);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);

    JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
    contentPane.add(tabbedPane, BorderLayout.CENTER);

    ArrayList<Utilisateur> users1 = (ArrayList<Utilisateur>) UtilisateurDao.retrieveAll();
    ArrayList<Utilisateur> users = users1;
   
    ArrayList<Classe> classes = (ArrayList<Classe>)ClasseDao.retrieveAll();    
    ArrayList<Location> retours = (ArrayList<Location>)LocationDao.retrieveAll(false);
    ArrayList<Reservation> reservations = (ArrayList<Reservation>)ReservationDao.retrieveAll();
    ArrayList<Parametre> parametres = (ArrayList<Parametre>)ParametreDao.retrieveAll();
    
    ArrayList<Client> clients = (ArrayList<Client>)ClientDao.retrieveAll();
    ArrayList<Location> locations = (ArrayList<Location>)LocationDao.retrieveAll();
    ArrayList<Vehicule> vehicules = (ArrayList<Vehicule>)VehiculeDao.retrieveAll();
    
    
    this.formClient = new WFormClient();
    this.formReservation = new WFormReservation();
    handleFormReservationEvents();
    this.formLocation = new WFormLocation();
    handleFormLocationEvents();
    this.formRetour = new WFormRetour();
    this.formVehicule = new WFormVehicule();
    handleFormVehiculeEvents();
    this.formParametre = new WFormParametre();
    
    this.tabClient = new WTabFormListAdd<Client>(tabbedPane, clients, this.formClient, "Client");
    handleTabClientEvents();
    this.tabReservation = new WTabFormListAdd<Reservation>(tabbedPane, reservations, this.formReservation, "R\u00E9servation");
    handleTabReservationEvents();
    this.tabLocation = new WTabFormListAdd<Location>(tabbedPane, locations, this.formLocation, "Location");
    handleTabLocationEvents();
    this.tabRetour = new WTabFormListAdd<Location>(tabbedPane, retours, this.formRetour, "Retour");
    handleTabRetourEvents();
    
    //if(user1.getRole() == 0){ //TODO add back avant la fin
      this.tabVehicule = new WTabFormListAdd<Vehicule>(tabbedPane, vehicules, this.formVehicule, "V\u00E9hicule");
      handleTabVehiculeEvents();
      this.tabParametre = new WTabFormList<Parametre>(tabbedPane, parametres, this.formParametre, "Param\u00EAtre");
      //}
  }
  
  private void handleFormReservationEvents() {
	  this.formReservation.getComboBoxClasse().events().addListener(new ui.events.EventListener() {
	      	@SuppressWarnings("rawtypes") 
        @Override
        public void handleEvent(Event evt) {
      		switch((EventEnum.FormComboBoxEvents) evt.getEventName()) {
	      		case COMBO_BOX_OPENED:
	      			formReservation.getComboBoxClasse().set((ArrayList<Classe>)ClasseDao.retrieveAll());
	      			break;
	      		default:
	      			break;
	      	}
      	}
  });
  this.formReservation.getComboBoxClient().events().addListener(new ui.events.EventListener() {
      	@SuppressWarnings("rawtypes") 
        @Override
        public void handleEvent(Event evt) {
      		switch((EventEnum.FormComboBoxEvents) evt.getEventName()) {
	      		case COMBO_BOX_OPENED:
	      			formReservation.getComboBoxClient().set((ArrayList<Client>)ClientDao.retrieveAll());
	      			break;
	      		default:
	      			break;
	      	}
      	}
  });
}
  
  private void handleFormLocationEvents() {
	  	this.formLocation.getComboBoxReservation().events().addListener(new EventListener() {      
		    	@SuppressWarnings("rawtypes") 
		      @Override
		      public void handleEvent(Event evt) {
		            switch ((EventEnum.FormComboBoxEvents) evt.getEventName()) {
		              case COMBO_BOX_OPENED:
		            	  formLocation.getComboBoxReservation().set((ArrayList<Reservation>)ReservationDao.retrieveAll(false));
		                  break;
		              default:
		                  break;
		            }
		      }   
		    });
	  	
	  	this.formLocation.getComboBoxVehicule().events().addListener(new EventListener() {      
	    	@SuppressWarnings("rawtypes") 
	      @Override
	      public void handleEvent(Event evt) {
	            switch ((EventEnum.FormComboBoxEvents) evt.getEventName()) {
	              case COMBO_BOX_OPENED:
	            	  Reservation reservation = formLocation.getComboBoxReservation().getSelected();
	            	  System.out.println(reservation.getClasseReservation().getNom());
	            	  formLocation.getComboBoxVehicule().set((ArrayList<Vehicule>)VehiculeDao
	            			  .retrieveWhereClasse(reservation.getClasseReservation()));
	                  break;
	              default:
	                  break;
	            }
	      }   
	    });
	  	this.formLocation.getButtonPay().events().addListener(new EventListener() {
			@SuppressWarnings("rawtypes")
			@Override
			public void handleEvent(Event evt) {
				 switch ((EventEnum.FormPayButtonEvents) evt.getEventName()) {
				 case DIALOG_OPENED:
					 formLocation.getButtonPay().set(new Paiement(formLocation.get().getId(), formLocation.get().getTotalPrice(), 0, ""));
					 break;
	              case PAYED:
	            	  Location location = formLocation.get();
	            	  location.addPaiement(formLocation.getButtonPay().get());
	            	  LocationDao.update(location);	
	                  break;
	              default:
	                  break;
	            }
			}
		});
	}
  
  private void handleFormVehiculeEvents() {
  	this.formVehicule.getComboBox().events().addListener(new EventListener() {      
	    	@SuppressWarnings("rawtypes") 
	      @Override
	      public void handleEvent(Event evt) {
	            switch ((EventEnum.FormComboBoxEvents) evt.getEventName()) {
	              case COMBO_BOX_OPENED:
	            	  formVehicule.getComboBox().set((ArrayList<Classe>)ClasseDao.retrieveAll());
	                  break;
	              default:
	                  break;
	            }
	      }   
	    });
}
  
  private void handleTabClientEvents() {
	  this.tabClient.events().addListener(new ui.events.EventListener() {
	      	@SuppressWarnings("rawtypes") 
	        @Override
	        public void handleEvent(Event evt) {
	      		switch((EventEnum.TabFormListEvents) evt.getEventName()) {
		      		case BUTTON_ADD_CLICKED:
		      			tabClient.add(new Client(-1, "Nouveau", "Nouveau"));
		      			break;
		      		case BUTTON_SAVE_CLICKED_NEW:
		      			tabClient.add(ClientDao.create(tabClient.getCurrentListable()));
		      			break;
		      		case BUTTON_SAVE_CLICKED_MODIFY:
		                 ClientDao.update(tabClient.getCurrentListable());
		      			break;
		      		case LIST_VALUE_CHANGED_W_UNSAVED_CONTENT_YES_NEW:
		      			tabClient.add(ClientDao.create(tabClient.getCurrentListable()));
		      			break;
		      		case LIST_VALUE_CHANGED_W_UNSAVED_CONTENT_YES_MODIFY:
		      			ClientDao.update(tabClient.getCurrentListable());
		      			break;
		      		default:
		      			break;
		      	}
	      	}
	  });
  }
  
  
  private void handleTabLocationEvents() {
	  this.tabLocation.events().addListener(new ui.events.EventListener() {
	      	@SuppressWarnings("rawtypes") 
	        @Override
	        public void handleEvent(Event evt) {
	      		switch((EventEnum.TabFormListEvents) evt.getEventName()) {
		      		case BUTTON_ADD_CLICKED:
		      			tabLocation.add(new Location(-1));
		      			break;
		      		case BUTTON_SAVE_CLICKED_NEW:
		      			tabLocation.add(LocationDao.create(tabLocation.getCurrentListable()));
		      			break;
		      		case BUTTON_SAVE_CLICKED_MODIFY:
		                 LocationDao.update(tabLocation.getCurrentListable());		                 
		      			break;
		      		case LIST_VALUE_CHANGED_W_UNSAVED_CONTENT_YES_NEW:
		      			tabLocation.add(LocationDao.create(tabLocation.getCurrentListable()));
		      			break;
		      		case LIST_VALUE_CHANGED_W_UNSAVED_CONTENT_YES_MODIFY:
		      			LocationDao.update(tabLocation.getCurrentListable());
		      			break;
		      		default:
		      			break;
		      	}
	      	}
	  });
  }
  
  private void handleTabReservationEvents() {
	  this.tabReservation.events().addListener(new ui.events.EventListener() {
	      	@SuppressWarnings("rawtypes") 
	        @Override
	        public void handleEvent(Event evt) {
	      		switch((EventEnum.TabFormListEvents) evt.getEventName()) {
		      		case BUTTON_ADD_CLICKED:
		      			tabReservation.add(new Reservation(-1));
		      			break;
		      		case BUTTON_SAVE_CLICKED_NEW:
		      			tabReservation.add(ReservationDao.create(tabReservation.getCurrentListable()));
		      			break;
		      		case BUTTON_SAVE_CLICKED_MODIFY:
		                 ReservationDao.update(tabReservation.getCurrentListable());		                 
		      			break;
		      		case LIST_VALUE_CHANGED_W_UNSAVED_CONTENT_YES_NEW:
		      			tabReservation.add(ReservationDao.create(tabReservation.getCurrentListable()));
		      			break;
		      		case LIST_VALUE_CHANGED_W_UNSAVED_CONTENT_YES_MODIFY:
		      			ReservationDao.update(tabReservation.getCurrentListable());
		      			break;
		      		default:
		      			break;
		      	}
	      	}
	  });
}

	private void handleTabRetourEvents() {
		this.tabRetour.events().addListener(new ui.events.EventListener() {
			@SuppressWarnings("rawtypes")
			@Override
			public void handleEvent(Event evt) {
				switch((EventEnum.TabFormListEvents) evt.getEventName()) {
					case BUTTON_ADD_CLICKED:
						formRetour.init();
						//tabRetour.add(new Vehicule(-1, "Nouveau", "Nouveau"));
						break;
					case BUTTON_SAVE_CLICKED_NEW: //Nouveau entièrement
						formRetour.saveNew();
						//tabRetour.add(LocationDao.create(tabVehicule.getCurrentListable()));
						break;
					case BUTTON_SAVE_CLICKED_MODIFY:
						//VehiculeDao.update(tabVehicule.getCurrentListable());
						break;
					default:
						break;
				}
			}
		});
	}

  private void handleTabVehiculeEvents() {
	  this.tabVehicule.events().addListener(new ui.events.EventListener() {
	      	@SuppressWarnings("rawtypes") 
	        @Override
	        public void handleEvent(Event evt) {
	      		switch((EventEnum.TabFormListEvents) evt.getEventName()) {
		      		case BUTTON_ADD_CLICKED:
		      			tabVehicule.add(new Vehicule(-1, "Nouveau", "Nouveau"));
		      			break;
		      		case BUTTON_SAVE_CLICKED_NEW:
		      			tabVehicule.add(VehiculeDao.create(tabVehicule.getCurrentListable()));
		      			break;
		      		case BUTTON_SAVE_CLICKED_MODIFY:
		      			VehiculeDao.update(tabVehicule.getCurrentListable());
		      			break;
		      		case LIST_VALUE_CHANGED_W_UNSAVED_CONTENT_YES_NEW:
		      			tabVehicule.add(VehiculeDao.create(tabVehicule.getCurrentListable()));
		      			break;
		      		case LIST_VALUE_CHANGED_W_UNSAVED_CONTENT_YES_MODIFY:
		      			VehiculeDao.update(tabVehicule.getCurrentListable());
		      			break;
		      		default:
		      			break;
		      	}
	      	}
	  });
  }
  

}
