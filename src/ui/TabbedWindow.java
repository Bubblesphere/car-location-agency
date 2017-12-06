package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.*;
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
import data.Utilisateur;
import data.Vehicule;
import org.sqlite.util.StringUtils;
import security.Login;
import ui.business.tab.WTabClient;
import ui.business.tab.WTabLocation;
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
    Login login = new Login();
    Utilisateur user1 = login.authenticate(this);

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
    ArrayList<Utilisateur> users = (ArrayList<Utilisateur>) users1;
   
    ArrayList<Classe> classes = (ArrayList<Classe>)ClasseDao.retrieveAll();    
    ArrayList<Location> retours = (ArrayList<Location>)LocationDao.retrieveAll();
    ArrayList<Reservation> reservations = (ArrayList<Reservation>)ReservationDao.retrieveAll();
    ArrayList<Parametre> parametres = (ArrayList<Parametre>)ParametreDao.retrieveAll();
    
    ArrayList<Client> clients = (ArrayList<Client>)ClientDao.retrieveAll();
    ArrayList<Location> locations = (ArrayList<Location>)LocationDao.retrieveAll();

    WTabClient tabClient = new WTabClient(tabbedPane, clients);
    WTabReservation tabReservation = new WTabReservation(tabbedPane, reservations);
    WTabLocation tabLocation = new WTabLocation(tabbedPane, locations);
    WTabRetour tabRetour = new WTabRetour(tabbedPane, retours);


    if(user1.getRole() == 0){
      WTabVehicule tabVehicule = new WTabVehicule(tabbedPane);
      WTabParametre tabParametre = new WTabParametre(tabbedPane, parametres);
    }

    
  }


}
