package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.VehiculeDao;
import data.Vehicule;

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
          
          //Vehicule v = new Vehicule(1, "test", "test", 2017, 1000, 0, "test", false, 100, "test");
          Vehicule v = VehiculeDao.retrieve(1);
          System.out.println(v.getAnnee());

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
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 674, 670);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);

    JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
    contentPane.add(tabbedPane, BorderLayout.CENTER);

    JPanel panel = new JPanel();
    tabbedPane.addTab("New tab", null, panel, null);

    JPanel panel1 = new JPanel();
    tabbedPane.addTab("New tab", null, panel1, null);

    JPanel panel2 = new JPanel();
    tabbedPane.addTab("New tab", null, panel2, null);

    JPanel panel3 = new JPanel();
    tabbedPane.addTab("New tab", null, panel3, null);
  }

}
