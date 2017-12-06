package security;

import dao.UtilisateurDao;
import data.Utilisateur;

import javax.swing.*;

public class Login {
    public Utilisateur authenticate(java.awt.Component context ) {
        Utilisateur attempt;
        do{
            String employeeInput;
            do{
                employeeInput = JOptionPane.showInputDialog(context, "Entrez votre numero d'employé", "Identification", JOptionPane.PLAIN_MESSAGE);
            }
            while(employeeInput.length() < 1 || !isInteger(employeeInput));

            int employeeId = Integer.parseInt(employeeInput);

            String password;
            do{
                password = JOptionPane.showInputDialog(context, "Entrez votre mot de passe", "Authentification", JOptionPane.PLAIN_MESSAGE);
            }
            while(password.length() < 4);

            String passwordAttempt = PasswordSecurity.hashPassword(password);
            attempt = UtilisateurDao.checkAndRetrieve(employeeId, passwordAttempt);
        } while(attempt == null);
        return attempt;

    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

}
