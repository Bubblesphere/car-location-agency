package dataAccessObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.TypeParametre;

public class TypeParametreDAO {

	public static TypeParametre create(TypeParametre typeParametre) {
		try (Connection connection = DataAccess.getConnection()) {

			String query = "INSERT INTO TypesParametre (description) values (?, ?)";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, typeParametre.getDescription());
			statement.execute();

			ResultSet keys = statement.getGeneratedKeys();
			keys.next();
			typeParametre.setTypeId(keys.getInt(1));

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return typeParametre;
	}

	public static TypeParametre retrieve(int typeParametreId) {
		try (Connection connection = DataAccess.getConnection()) {

			String query = "SELECT id, description FROM TypesParametre WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, typeParametreId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				return new TypeParametre(resultSet.getInt("id"), resultSet.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<TypeParametre> retrieveAll() {
		List<TypeParametre> result = new ArrayList<TypeParametre>();
		try (Connection connection = DataAccess.getConnection()) {

			String query = "SELECT id, description FROM TypesParametre";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				result.add(new TypeParametre(resultSet.getInt("id"), resultSet.getString("description")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public static boolean update(TypeParametre typeParametre) {
		try (Connection connection = DataAccess.getConnection()) {

			String query = "UPDATE TypesParametre SET description = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, typeParametre.getDescription());
			statement.setInt(2, typeParametre.getTypeId());
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean delete(int typeParametreId) {
		try (Connection connection = DataAccess.getConnection()) {

			String query = "DELETE FROM TypesParametre WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, typeParametreId);
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
