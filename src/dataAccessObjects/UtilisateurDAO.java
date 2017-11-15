package dataAccessObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.Utilisateur;

public class UtilisateurDAO {

	public static Utilisateur create(Utilisateur utilisateur) {
		try (Connection connection = DataAccess.getConnection()) {

			String query = "INSERT INTO Utilisateurs (mot_de_passe, courriel, prenom, nom, num_employe, role, desactive) values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
	
	public static Utilisateur checkAndRetrieve(Utilisateur utilisateur) {
		try (Connection connection = DataAccess.getConnection()) {

			String query = "SELECT id FROM Utilisateurs WHERE num_employe = ? AND mot_de_passe = ? AND NOT desactive";
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

	public static Utilisateur retrieve(int utilisateurId) {
		try (Connection connection = DataAccess.getConnection()) {

			String query = "SELECT id, courriel, prenom, nom, num_employe, role, desactive FROM Utilisateurs WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, utilisateurId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				return new Utilisateur(resultSet.getString("nom"), resultSet.getString("prenom"),
						resultSet.getInt("id"), resultSet.getString("courriel"),
						resultSet.getInt("num_employe"), resultSet.getInt("role"), resultSet.getBoolean("desactive"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Utilisateur> retrieveAll() {
		List<Utilisateur> result = new ArrayList<Utilisateur>();
		try (Connection connection = DataAccess.getConnection()) {

			String query = "SELECT id, courriel, prenom, nom, num_employe, role, desactive FROM Utilisateurs";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				result.add(new Utilisateur(resultSet.getString("nom"), resultSet.getString("prenom"),
						resultSet.getInt("id"), resultSet.getString("courriel"),
						resultSet.getInt("num_employe"), resultSet.getInt("role"), resultSet.getBoolean("desactive")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public static boolean update(Utilisateur utilisateur) {
		try (Connection connection = DataAccess.getConnection()) {

			String query = "UPDATE Utilisateurs SET mot_de_passe = ?, courriel = ?, prenom = ?, nom = ?, num_employe = ?, role = ?, desactive = ? WHERE id = ?";
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
