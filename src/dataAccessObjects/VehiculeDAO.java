package dataAccessObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.Classe;
import data.Vehicule;

public class VehiculeDAO {
	private DataAccess dataAccess;

	public VehiculeDAO() {
		super();
		dataAccess = new DataAccess();
	}

	public Vehicule create(Vehicule vehicule) {
		try (Connection connection = dataAccess.getConnection()) {

			String query = "INSERT INTO Vehicules (classe_id, fabriquant, marque, annee, kilometrage, etat, plaque, desactive, capacite_essence, note) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, vehicule.getClasseId());
			statement.setString(2, vehicule.getFabricant());
			statement.setString(3, vehicule.getMarque());
			statement.setInt(4, vehicule.getAnnee());
			statement.setInt(5, vehicule.getKilometrage());
			statement.setInt(6, vehicule.getEtat());
			statement.setString(7, vehicule.getPlaque());
			statement.setBoolean(8, vehicule.getDesactive());
			statement.setString(9, vehicule.getNote());
			statement.execute();

			ResultSet keys = statement.getGeneratedKeys();
			keys.next();
			vehicule.setId(keys.getInt(1));

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return vehicule;
	}

	public Vehicule retrieve(int vehiculeId) {
		try (Connection connection = dataAccess.getConnection()) {

			String query = "SELECT V.id, V.classe_id, V.fabriquant, V.marque, V.annee, V.kilometrage, V.etat, "
					+ "V.plaque, V.desactive, V.capacite_essence, V.note , C.nom AS nom_classe, "
					+ "C.prix_journalier AS prix_classe FROM Vehicules V LEFT JOIN Classes C ON V.classe_id = C.id WHERE V.id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, vehiculeId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				Classe classe = new Classe(resultSet.getInt("classe_id"), resultSet.getString("nom_classe"),
						resultSet.getFloat("prix_classe"));
				Vehicule vehicule = new Vehicule(resultSet.getInt("id"), classe.getId(), classe,
						resultSet.getString("fabriquant"), resultSet.getString("marque"), resultSet.getInt("annee"),
						resultSet.getInt("kilometrage"), resultSet.getInt("etat"), resultSet.getString("plaque"),
						resultSet.getBoolean("desactive"), resultSet.getInt("capacite_essence"),
						resultSet.getString("note"));
				return vehicule;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Vehicule> retrieveAll() {
		List<Vehicule> result = new ArrayList<Vehicule>();
		try (Connection connection = dataAccess.getConnection()) {

			String query = "SELECT V.id, V.classe_id, V.fabriquant, V.marque, V.annee, V.kilometrage, V.etat, "
					+ "V.plaque, V.desactive, V.capacite_essence, V.note , C.nom AS nom_classe, "
					+ "C.prix_journalier AS prix_classe FROM Vehicules V LEFT JOIN Classes C ON C.id = V.classe_id";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Classe classe = new Classe(resultSet.getInt("classe_id"), resultSet.getString("nom_classe"),
						resultSet.getFloat("prix_classe"));
				Vehicule vehicule = new Vehicule(resultSet.getInt("id"), classe.getId(), classe,
						resultSet.getString("fabriquant"), resultSet.getString("marque"), resultSet.getInt("annee"),
						resultSet.getInt("kilometrage"), resultSet.getInt("etat"), resultSet.getString("plaque"),
						resultSet.getBoolean("desactive"), resultSet.getInt("capacite_essence"),
						resultSet.getString("note"));

				result.add(vehicule);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public boolean update(Vehicule vehicule) {
		try (Connection connection = dataAccess.getConnection()) {

			String query = "UPDATE Vehicules SET classe_id = ?, fabriquant = ?, marque = ?, annee = ?, kilometrage = ?, etat = ?, plaque = ?, desactive = ?, capacite_essence = ?, note = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, vehicule.getClasseId());
			statement.setString(2, vehicule.getFabricant());
			statement.setString(3, vehicule.getMarque());
			statement.setInt(4, vehicule.getAnnee());
			statement.setInt(5, vehicule.getKilometrage());
			statement.setInt(6, vehicule.getEtat());
			statement.setString(7, vehicule.getPlaque());
			statement.setBoolean(8, vehicule.getDesactive());
			statement.setInt(9, vehicule.getCapaciteEssence());
			statement.setString(10, vehicule.getNote());
			statement.setInt(11, vehicule.getId());
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(int vehiculeId) {
		try (Connection connection = dataAccess.getConnection()) {

			String query = "DELETE FROM Vehicules WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, vehiculeId);
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
