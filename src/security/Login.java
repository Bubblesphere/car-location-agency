package security;

import dao.UtilisateurDao;

import javax.swing.*;

public class Login {
    public Login() {

        String employeeInput;
        do{
            employeeInput = JOptionPane.showInputDialog(this, "Entrez votre numero d'employé", "Identification", JOptionPane.PLAIN_MESSAGE);
        }
        while(employeeInput.length() < 1 || !isInteger(employeeInput));

        int employeeId = Integer.parseInt(employeeInput);

        String password;
        do{
            password = JOptionPane.showInputDialog(this, "Entrez votre mot de passe", "Authentification", JOptionPane.PLAIN_MESSAGE);
        }
        while(password.length() < 4);
//TODO VALIDER ET ASSIGNER LE USER RENVOYÉ PAR UTILISATEURDAO
        if (UtilisateurDao.checkAndRetrieve(employeeId, password) != null){

        }
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
