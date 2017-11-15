package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Client;
import data.Parametre;
import data.Utilisateur;
import dataAccessObjects.ClientDAO;
import dataAccessObjects.ParametreDAO;
import dataAccessObjects.UtilisateurDAO;

import javax.swing.JTabbedPane;

public class TabbedWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabbedWindow frame = new TabbedWindow();

					/*Client c = new Client("Hamel", "Bruno", "8196641781", "");
					Utilisateur u = new Utilisateur("Dallaire", "Déric", "password", "deric.dallaire@gmail.com", 54165, 0, false);
					ClientDAO.create(c);
					UtilisateurDAO.create(u);*/
					
					//Utilisateur u = new Utilisateur("Dallaire", "Déric", "password", "deric.dallaire@gmail.com", 54165, 0, false);
					//UtilisateurDAO.create(u);
					Utilisateur u = new Utilisateur("password", 54165);
					u = UtilisateurDAO.checkAndRetrieve(u);
					System.out.println(u);
					
					

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

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
	}

}
