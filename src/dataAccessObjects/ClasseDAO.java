package dataAccessObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.Classe;

public class ClasseDAO {
	public boolean create(Classe classe) {
		DataAccess da = new DataAccess();
		try (Connection connection = da.getConnection()) {

			String query = "INSERT INTO Classe (nom, prix_journalier) values (?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, classe.getNom());
			statement.setFloat(2, classe.getPrixJournalier());
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Classe retrieve(int classeId) {
		DataAccess da = new DataAccess();
		try (Connection connection = da.getConnection()) {

			String query = "SELECT id, nom, prix_journalier FROM Classe WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, classeId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				return new Classe(resultSet.getInt("id"), resultSet.getString("nom"),
						resultSet.getFloat("prix_journalier"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Classe> retrieveAll() {
		DataAccess da = new DataAccess();
		List<Classe> result = new ArrayList<Classe>();
		try (Connection connection = da.getConnection()) {

			String query = "SELECT id, nom, prix_journalier FROM Classe";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				result.add(new Classe(resultSet.getInt("id"), resultSet.getString("nom"),
						resultSet.getFloat("prix_journalier")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public boolean update(Classe classe) {
		DataAccess da = new DataAccess();
		try (Connection connection = da.getConnection()) {

			String query = "UPDATE Classe SET nom = ?, prix_journalier = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, classe.getNom());
			statement.setFloat(2, classe.getPrixJournalier());
			statement.setInt(3, classe.getId());
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(int classeId) {
		DataAccess da = new DataAccess();
		try (Connection connection = da.getConnection()) {

			String query = "DELETE FROM Classe WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, classeId);
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
