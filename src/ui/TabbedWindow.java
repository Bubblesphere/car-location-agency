package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import data.Classe;
import data.Client;
import ui.business.form.WFormClient;
import ui.business.tab.WTabClient;
import ui.business.tab.WTabRetour;
import ui.business.tab.WTabParametre;
import ui.business.tab.WTabReservation;
import ui.business.tab.WTabVehicule;
import ui.widgets.WList;
import ui.widgets.WListAdd;
import ui.widgets.WSplitPaneTab;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSplitPane;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JTree;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import data.Location;
import data.Parametre;
import data.Reservation;
import data.TypeParametre;
import data.Utilisateur;
import data.Vehicule;

public class TabbedWindow extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField textField;
  private JTextField textField_1;
  private JTextField textField_2;

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
  	setIconImage(Toolkit.getDefaultToolkit().getImage(TabbedWindow.class.getResource("/com/sun/javafx/scene/control/skin/modena/dialog-more-details@2x.png")));
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
    users.addElement(new Utilisateur("Doe", "John", 0, "password", "dallaire.deric@gmail.com", 0, 1, false));
    
    DefaultListModel<Client> clients = new DefaultListModel<Client>();
    clients.addElement(new Client(0, "Deric", "Dallaire"));
    clients.addElement(new Client(1, "Bruno", "Hamel"));
    clients.addElement(new Client(2, "Jean", "Bob"));
    
    DefaultListModel<Classe> classes = new DefaultListModel<Classe>();
    classes.addElement(new Classe(0, "Sport", 30));
    classes.addElement(new Classe(1, "Économique", 12));
    
    DefaultListModel<Vehicule> vehicules = new DefaultListModel<Vehicule>();
    vehicules.addElement(new Vehicule(0, classes.get(0), "Tesla", "Roadster", 2017, 0, 0, "WTF FTW", false, 120, ""));
    vehicules.addElement(new Vehicule(1, classes.get(1), "Honda", "Civic", 2016, 10000, 0, "PLA QUE", false, 100, ""));
    
    DefaultListModel<Location> retours = new DefaultListModel<Location>();
    retours.addElement(new Location(0, clients.get(0), classes.get(0), LocalDate.now(), LocalDate.now(), "", users.get(0), 0, 
    		vehicules.get(0), users.get(0), LocalDate.now(), false, false, 0, 0, 100, "", 0));
    
    DefaultListModel<Reservation> reservations = new DefaultListModel<Reservation>();
    reservations.addElement(new Reservation(0, 0, clients.get(0), 1, classes.get(0), LocalDate.now(), LocalDate.now(), "", 0, users.get(0)));
    
    DefaultListModel<TypeParametre> typeParametres = new DefaultListModel<TypeParametre>();
    
    DefaultListModel<Parametre> parametres = new DefaultListModel<Parametre>();
    parametres.addElement(new Parametre(0, "Prix assurance", 0, 40, LocalDate.now(), LocalDate.now()));
    parametres.addElement(new Parametre(0, "Prix test", 1, 50, LocalDate.now(), LocalDate.now()));
    
    WTabClient tabClient = new WTabClient(tabbedPane, clients);
    WTabReservation tabReservation = new WTabReservation(tabbedPane, reservations);
    WTabRetour tabRetour = new WTabRetour(tabbedPane, retours);
    WTabVehicule tabVehicule = new WTabVehicule(tabbedPane, vehicules);
    WTabParametre tabParametre = new WTabParametre(tabbedPane, parametres);
  }
}
