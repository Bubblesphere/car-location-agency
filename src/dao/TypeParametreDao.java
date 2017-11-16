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
   * M�thode pour cr�� un type de param�tre
   * 
   * @param typeParametre
   *          type de param�tre � cr��.
   * @return le type de param�tre qui a �t� cr�� avec son id de mis � jour.
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
   * M�thode pour r�cup�rer un type de param�tre
   * 
   * @param typeParametreId
   *          id du type de param�tre � r�cup�rer.
   * @return le type de param�tre r�cup�rer.
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
   * M�thode pour r�cup�rer touts les type de param�tre
   * 
   * @return liste de touts les type de param�tre.
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
   * M�thode pour mettre � jour un type de param�tre
   * 
   * @param typeParametre
   *          le type de param�tre avec les nouvelle valeurs.
   * @return si la mise � jour a fonctionn�e.
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
   * M�thode pour supprimer un type de param�tre
   * 
   * @param typeParametreId
   *          le id du type de param�tre � supprimer.
   * @return si la suppression � fonctionn�e.
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
