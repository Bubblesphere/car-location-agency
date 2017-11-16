package dao;

import data.TypeParametre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TypeParametreDao {

  /**
   * Méthode pour créé un type de paramètre
   * 
   * @param typeParametre
   *          type de paramètre à créé.
   * @return le type de paramètre qui a été créé avec son id de mis à jour.
   */
  public static TypeParametre create(TypeParametre typeParametre) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "INSERT INTO TypesParametre (description) values (?, ?)";
      PreparedStatement statement = connection.prepareStatement(query,
          Statement.RETURN_GENERATED_KEYS);
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

  /**
   * Méthode pour récupérer un type de paramètre
   * 
   * @param typeParametreId
   *          id du type de paramètre à récupérer.
   * @return le type de paramètre récupérer.
   */
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

  /**
   * Méthode pour récupérer touts les type de paramètre
   * 
   * @return liste de touts les type de paramètre.
   */
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

  /**
   * Méthode pour mettre à jour un type de paramètre
   * 
   * @param typeParametre
   *          le type de paramètre avec les nouvelle valeurs.
   * @return si la mise à jour a fonctionnée.
   */
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

  /**
   * Méthode pour supprimer un type de paramètre
   * 
   * @param typeParametreId
   *          le id du type de paramètre à supprimer.
   * @return si la suppression à fonctionnée.
   */
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
