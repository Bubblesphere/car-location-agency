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
import ui.buttons.impl.WidgetButtonSaveClient;
import ui.form.WFormClient;
import ui.panel.WClientTab;
import ui.panel.WSplitPaneTab;
import ui.widgets.list.WList;
import ui.widgets.listAdd.WListAdd;

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
          /*
           * Client c = new Client("Hamel", "Bruno", "8196641781", "");
           * Utilisateur u = new Utilisateur("Dallaire", "Déric", "password",
           * "deric.dallaire@gmail.com", 54165, 0, false); ClientDao.create(c);
           * UtilisateurDao.create(u);
           */

          // Utilisateur u = new Utilisateur("Dallaire", "Déric",
          // "password", "deric.dallaire@gmail.com", 54165, 0, false);
          // UtilisateurDao.create(u);
          // Utilisateur u = new Utilisateur("password", 54165);
          // u = UtilisateurDao.checkAndRetrieve(u);
          // System.out.println(u);

          // Vehicule v = new Vehicule(1, "test", "test", 2017, 1000, 0, "test",
          // false, 100, "test");
          /*
           * Vehicule v = VehiculeDao.retrieve(1);
           * System.out.println(v.getAnnee());
           */

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
    
    DefaultListModel<Location> locations = new DefaultListModel<Location>();
    locations.addElement(new Location(0, clients.get(0), classes.get(0), LocalDate.now(), LocalDate.now(), "", users.get(0), 0, 
    		vehicules.get(0), users.get(0), LocalDate.now(), false, false, 0, 0, 100, "", 0));
    
    DefaultListModel<TypeParametre> typeParametres = new DefaultListModel<TypeParametre>();
    
    DefaultListModel<Parametre> parametres = new DefaultListModel<Parametre>();
    parametres.addElement(new Parametre(0, "Prix assurance", 0, 40, LocalDate.now(), LocalDate.now()));
    parametres.addElement(new Parametre(0, "Prix test", 1, 50, LocalDate.now(), LocalDate.now()));
    
    WClientTab tabClient = new WClientTab(tabbedPane, clients);
    WSplitPaneTab tabReservation = new WSplitPaneTab(tabbedPane, "Réservation");
    WSplitPaneTab tabRetour = new WSplitPaneTab(tabbedPane, "Retour");
    WSplitPaneTab tabVehicule = new WSplitPaneTab(tabbedPane, "Véhicule");
    WSplitPaneTab tabParametre = new WSplitPaneTab(tabbedPane, "Paramètre");
    

	/*
	
	ListHashMapAddPanel addListLocation = new ListHashMapAddPanel(locations);
	tabReservation.setLeftComponent(addListLocation);
	
	ListHashMapAddPanel addListVehicule = new ListHashMapAddPanel(vehicules);
	tabVehicule.setLeftComponent(addListVehicule);
	
	ListHashMapAddPanel addListParametre = new ListHashMapAddPanel(parametres);
	tabParametre.setLeftComponent(addListParametre);
	*/
	
    /*
    JPanel rightPanel = new JPanel();
    tabClient.setRightComponent(rightPanel);
    GridBagLayout gbl_panel_2 = new GridBagLayout();
    gbl_panel_2.columnWidths = new int[]{0, 0};
    gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
    gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    rightPanel.setLayout(gbl_panel_2);
    
    JLabel lblInformationSurLe = new JLabel("Information sur le client");
    lblInformationSurLe.setFont(new Font("Segoe UI", Font.BOLD, 16));
    GridBagConstraints gbc_lblInformationSurLe = new GridBagConstraints();
    gbc_lblInformationSurLe.anchor = GridBagConstraints.LINE_START;
    gbc_lblInformationSurLe.insets = new Insets(32, 32, 5, 32);
    gbc_lblInformationSurLe.gridx = 0;
    gbc_lblInformationSurLe.gridy = 2;
    rightPanel.add(lblInformationSurLe, gbc_lblInformationSurLe);
    
    JLabel lblNewLabel = new JLabel("Nom");
    lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
    gbc_lblNewLabel.anchor = GridBagConstraints.LINE_START;
    gbc_lblNewLabel.insets = new Insets(16, 32, 5, 32);
    gbc_lblNewLabel.gridx = 0;
    gbc_lblNewLabel.gridy = 3;
    panel_2.add(lblNewLabel, gbc_lblNewLabel);
    
    textField = new JTextField();
    textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    GridBagConstraints gbc_textField = new GridBagConstraints();
    gbc_textField.anchor = GridBagConstraints.LINE_START;
    gbc_textField.insets = new Insets(0, 32, 5, 32);
    gbc_textField.fill = GridBagConstraints.HORIZONTAL;
    gbc_textField.gridx = 0;
    gbc_textField.gridy = 4;
    panel_2.add(textField, gbc_textField);
    textField.setColumns(10);
    
    JLabel lblPrnom = new JLabel("Pr\u00E9nom");
    lblPrnom.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    GridBagConstraints gbc_lblPrnom = new GridBagConstraints();
    gbc_lblPrnom.anchor = GridBagConstraints.LINE_START;
    gbc_lblPrnom.insets = new Insets(16, 32, 5, 32);
    gbc_lblPrnom.gridx = 0;
    gbc_lblPrnom.gridy = 5;
    panel_2.add(lblPrnom, gbc_lblPrnom);
    
    textField_1 = new JTextField();
    textField_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    textField_1.setColumns(10);
    GridBagConstraints gbc_textField_1 = new GridBagConstraints();
    gbc_textField_1.anchor = GridBagConstraints.LINE_START;
    gbc_textField_1.insets = new Insets(0, 32, 5, 32);
    gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
    gbc_textField_1.gridx = 0;
    gbc_textField_1.gridy = 6;
    panel_2.add(textField_1, gbc_textField_1);
    
    JLabel lblPermisDeConduire = new JLabel("Permis de conduire");
    lblPermisDeConduire.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    GridBagConstraints gbc_lblPermisDeConduire = new GridBagConstraints();
    gbc_lblPermisDeConduire.anchor = GridBagConstraints.LINE_START;
    gbc_lblPermisDeConduire.insets = new Insets(16, 32, 5, 32);
    gbc_lblPermisDeConduire.gridx = 0;
    gbc_lblPermisDeConduire.gridy = 7;
    panel_2.add(lblPermisDeConduire, gbc_lblPermisDeConduire);
    
    textField_2 = new JTextField();
    textField_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    textField_2.setColumns(10);
    GridBagConstraints gbc_textField_2 = new GridBagConstraints();
    gbc_textField_2.insets = new Insets(0, 32, 5, 32);
    gbc_textField_2.anchor = GridBagConstraints.LINE_START;
    gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
    gbc_textField_2.gridx = 0;
    gbc_textField_2.gridy = 8;
    panel_2.add(textField_2, gbc_textField_2);
    
    WidgetButtonSaveClient buttonSaveClient = new WidgetButtonSaveClient();
    
    GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
    gbc_btnNewButton_1.insets = new Insets(32, 32, 5, 32);
    gbc_btnNewButton_1.anchor = GridBagConstraints.SOUTHEAST;
    gbc_btnNewButton_1.gridx = 0;
    gbc_btnNewButton_1.gridy = 9;
    panel_2.add(buttonSaveClient, gbc_btnNewButton_1);
    */
  }
}
