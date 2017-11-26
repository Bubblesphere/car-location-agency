package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.time.LocalDate;

import javax.swing.DefaultListModel;
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
import data.Parametre;
import data.Reservation;
import data.TypeParametre;
import data.Utilisateur;
import data.Vehicule;
import sun.util.resources.LocaleData;
import ui.business.tab.WTabClient;
import ui.business.tab.WTabParametre;
import ui.business.tab.WTabReservation;
import ui.business.tab.WTabRetour;
import ui.business.tab.WTabVehicule;

public class TabbedWindow extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;

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
    setIconImage(Toolkit.getDefaultToolkit().getImage(TabbedWindow.class
        .getResource("/com/sun/javafx/scene/control/skin/modena/dialog-more-details@2x.png")));
    setTitle("Syst\u00E8me de gestion de location");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 674, 670);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);

    JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
    contentPane.add(tabbedPane, BorderLayout.CENTER);

    DefaultListModel<Utilisateur> users = new DefaultListModel<Utilisateur>();
    for(Utilisateur user : UtilisateurDao.retrieveAll()){
      users.addElement(user);
    }

    DefaultListModel<Client> clients = new DefaultListModel<Client>();
    for(Client client : ClientDao.retrieveAll()){
      clients.addElement(client);
    }

    DefaultListModel<Classe> classes = new DefaultListModel<Classe>();
    for(Classe classe: ClasseDao.retrieveAll()){
      classes.addElement(classe);
    }

    DefaultListModel<Vehicule> vehicules = new DefaultListModel<Vehicule>();
    for(Vehicule vehicule : VehiculeDao.retrieveAll()){
      vehicules.addElement(vehicule);
    }    
  
    DefaultListModel<Location> retours = new DefaultListModel<Location>();  
    for(Location location:LocationDao.retrieveAll()){
      retours.addElement(location);
    }
    
    DefaultListModel<Reservation> reservations = new DefaultListModel<Reservation>();
    for(Reservation reservation: ReservationDao.retrieveAll()){
      reservations.addElement(reservation);
    }
    
    DefaultListModel<Parametre> parametres = new DefaultListModel<Parametre>();
    for(Parametre param : ParametreDao.retrieveAll()){
      parametres.addElement(param);
    }

    WTabClient tabClient = new WTabClient(tabbedPane, clients);
    WTabReservation tabReservation = new WTabReservation(tabbedPane, reservations);
    WTabRetour tabRetour = new WTabRetour(tabbedPane, retours);
    WTabVehicule tabVehicule = new WTabVehicule(tabbedPane, vehicules);
    WTabParametre tabParametre = new WTabParametre(tabbedPane, parametres);
  }
}
