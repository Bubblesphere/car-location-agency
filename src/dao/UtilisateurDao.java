package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.Utilisateur;

public class UtilisateurDao {

    /**
     * Méthode pour créé un utilisateur
     *
     * @param utilisateur utilisateur à créé.
     * @return l'utilisateur qui a été créé avec son id de mis à jour.
     */
    public static Utilisateur create(Utilisateur utilisateur) {
        try (Connection connection = DataAccess.getConnection()) {

            String query = "INSERT INTO Utilisateurs (mot_de_passe, courriel, prenom, "
                    + "nom, num_employe, role, desactive) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, utilisateur.getMotDePasse());
            statement.setString(2, utilisateur.getCourriel());
            statement.setString(3, utilisateur.getPrenom());
            statement.setString(4, utilisateur.getNom());
            statement.setInt(5, utilisateur.getNumEmploye());
            statement.setInt(6, utilisateur.getRole());
            statement.setBoolean(7, utilisateur.getDesactive());
            statement.execute();

            ResultSet keys = statement.getGeneratedKeys();
            keys.next();
            utilisateur.setId(keys.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return utilisateur;
    }

    /**
     * Méthode pour vérifier une combinaison d'un numéro d'employé et un mot de
     * passe pour un utilisateur
     *
     * @param utilisateur utilisateur à vérifier.
     * @return l'utilisateur qui a été vérifier ou null.
     */
    public static Utilisateur checkAndRetrieve(Utilisateur utilisateur) {
        try (Connection connection = DataAccess.getConnection()) {

            String query = "SELECT id FROM Utilisateurs WHERE num_employe = ? "
                    + "AND mot_de_passe = ? AND NOT desactive";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, utilisateur.getNumEmploye());
            statement.setString(2, utilisateur.getMotDePasse());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return retrieve(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Méthode pour vérifier une combinaison d'un numéro d'employé et un mot de
     * passe pour un utilisateur
     *
     * @param employeeId utilisateur à vérifier.
     * @param password   password à valider
     * @return l'utilisateur qui a été vérifier ou null.
     */
    public static Utilisateur checkAndRetrieve(int employeeId, String password) {
        try (Connection connection = DataAccess.getConnection()) {

            String query = "SELECT id FROM Utilisateurs WHERE num_employe = ? "
                    + "AND mot_de_passe = ? AND NOT desactive";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return retrieve(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Méthode pour récupérer un utilisateur
     *
     * @param utilisateurId id du utilisateur à récupérer.
     * @return l'utilisateur récupérer.
     */
    public static Utilisateur retrieve(int utilisateurId) {
        try (Connection connection = DataAccess.getConnection()) {

            String query = "SELECT id, courriel, prenom, nom, num_employe, role, "
                    + "desactive FROM Utilisateurs WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, utilisateurId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Utilisateur(resultSet.getString("nom"), resultSet.getString("prenom"),
                        resultSet.getInt("id"), resultSet.getString("courriel"),
                        resultSet.getInt("num_employe"), resultSet.getInt("role"),
                        resultSet.getBoolean("desactive"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Méthode pour récupérer toutes les classes
     *
     * @return liste de touts les utilisateurs.
     */
    public static List<Utilisateur> retrieveAll() {
        List<Utilisateur> result = new ArrayList<Utilisateur>();
        try (Connection connection = DataAccess.getConnection()) {

            String query = "SELECT id, courriel, prenom, nom, num_employe, "
                    + "role, desactive FROM Utilisateurs";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                result.add(new Utilisateur(resultSet.getString("nom"), resultSet.getString("prenom"),
                        resultSet.getInt("id"), resultSet.getString("courriel"),
                        resultSet.getInt("num_employe"), resultSet.getInt("role"),
                        resultSet.getBoolean("desactive")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    /**
     * Méthode pour mettre à jour un utilisateur
     *
     * @param utilisateur l'utilisateur avec les nouvelle valeurs.
     * @return si la mise à jour a fonctionnée.
     */
    public static boolean update(Utilisateur utilisateur) {
        try (Connection connection = DataAccess.getConnection()) {

            String query = "UPDATE Utilisateurs SET mot_de_passe = ?, courriel = ?, "
                    + "prenom = ?, nom = ?, num_employe = ?, role = ?, desactive = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, utilisateur.getMotDePasse());
            statement.setString(2, utilisateur.getCourriel());
            statement.setString(3, utilisateur.getPrenom());
            statement.setString(4, utilisateur.getNom());
            statement.setInt(5, utilisateur.getNumEmploye());
            statement.setInt(6, utilisateur.getRole());
            statement.setBoolean(7, utilisateur.getDesactive());
            statement.setInt(8, utilisateur.getId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Méthode pour supprimer un utilisateur
     *
     * @param utilisateurId le id de la l'utilisateur à supprimer.
     * @return si la suppression à fonctionnée.
     */
    public static boolean delete(int utilisateurId) {
        try (Connection connection = DataAccess.getConnection()) {

            String query = "DELETE FROM Utilisateurs WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, utilisateurId);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

}