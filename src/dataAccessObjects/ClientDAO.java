package dataAccessObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.Client;

public class ClientDAO {
	private DataAccess dataAccess;

	public ClientDAO() {
		super();
		dataAccess = new DataAccess();
	}

	public Client create(Client client) {
		try (Connection connection = dataAccess.getConnection()) {

			String query = "INSERT INTO Clients (prenom, nom, adresse, numero_permis, telephone, courriel, note) values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, client.getPrenom());
			statement.setString(2, client.getNom());
			statement.setString(3, client.getAdresse());
			statement.setString(4, client.getNumeroPermis());
			statement.setString(5, client.getNumeoTelphone());
			statement.setString(6, client.getCourriel());
			statement.setString(7, client.getNote());
			statement.execute();	
			
			ResultSet keys = statement.getGeneratedKeys();
			keys.next();
			client.setId(keys.getInt(1));

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return client;
	}

	public Client retrieve(int clientId) {
		try (Connection connection = dataAccess.getConnection()) {

			String query = "SELECT id, prenom, nom, adresse, numero_permis, telephone, courriel, note FROM Clients WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, clientId);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				return new Client(resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getInt("id"),
						resultSet.getString("adresse"), resultSet.getString("numero_permis"),
						resultSet.getString("telephone"), resultSet.getString("courriel"), resultSet.getString("note"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Client> retrieveAll() {
		List<Client> result = new ArrayList<Client>();
		try (Connection connection = dataAccess.getConnection()) {

			String query = "SELECT id, prenom, nom, adresse, numero_permis, telephone, courriel, note FROM Clients";
			Statement statement = connection.createStatement();			
			
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				result.add(new Client(resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getInt("id"),
						resultSet.getString("adresse"), resultSet.getString("numero_permis"),
						resultSet.getString("telephone"), resultSet.getString("courriel"),
						resultSet.getString("note")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public boolean update(Client client) {
		try (Connection connection = dataAccess.getConnection()) {

			String query = "UPDATE Clients SET prenom = ?, nom = ?, adresse = ?, numero_permis = ?, telephone = ?, courriel = ?, note = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, client.getPrenom());
			statement.setString(2, client.getNom());
			statement.setString(3, client.getAdresse());
			statement.setString(4, client.getNumeroPermis());
			statement.setString(5, client.getNumeoTelphone());
			statement.setString(6, client.getCourriel());
			statement.setString(7, client.getNote());
			statement.setInt(8, client.getId());
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(int clientId) {
		try (Connection connection = dataAccess.getConnection()) {

			String query = "DELETE FROM Clients WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, clientId);
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
