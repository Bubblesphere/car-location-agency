package dao;

import data.Parametre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ParametreDao {

  /**
   * M�thode pour cr�� un param�tre
   * 
   * @param parametre
   *          param�tre � cr��.
   * @return le param�tre qui a �t� cr�� avec son id de mis � jour.
   */
  public static Parametre create(Parametre parametre) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "INSERT INTO Parametres (valeur, type_id, date_debut, "
          + "date_fin) values (?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(query,
          Statement.RETURN_GENERATED_KEYS);
      statement.setFloat(1, parametre.getValeur());
      statement.setInt(2, parametre.getTypeId());
      statement.setObject(3, parametre.getDateDebut());
      statement.setObject(4, parametre.getDateFin());
      statement.execute();

      ResultSet keys = statement.getGeneratedKeys();
      keys.next();
      parametre.setId(keys.getInt(1));

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return parametre;
  }

  /**
   * M�thode pour r�cup�rer une param�tre
   * 
   * @param parametreId
   *          id du param�tre � r�cup�rer.
   * @return le param�tre r�cup�rer.
   */
  public static Parametre retrieve(int parametreId) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "SELECT P.id, P.valeur, P.type_id, P.date_debut, P.date_fin, T.description "
          + "FROM Parametres P LEFT JOIN TypesParametre T ON T.id = P.type_id WHERE P.id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, parametreId);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return new Parametre(resultSet.getInt("type_id"), resultSet.getString("description"),
            resultSet.getInt("id"), resultSet.getFloat("valeur"),
            LocalDate.parse(resultSet.getString("date_debut")),
            resultSet.getString("date_fin") != null
                ? LocalDate.parse(resultSet.getString("date_fin")) : null);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * M�thode pour r�cup�rer touts les param�tres
   * 
   * @return liste de touts les param�tres.
   */
  public static List<Parametre> retrieveAll() {
    List<Parametre> result = new ArrayList<Parametre>();
    try (Connection connection = DataAccess.getConnection()) {

      String query = "SELECT P.id, P.valeur, P.type_id, P.date_debut, P.date_fin, T.description "
          + "FROM Parametres P LEFT JOIN TypesParametre T ON T.id = P.type_id";
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        result.add(new Parametre(resultSet.getInt("type_id"), resultSet.getString("description"),
            resultSet.getInt("id"), resultSet.getFloat("valeur"),
            LocalDate.parse(resultSet.getString("date_debut")),
            resultSet.getString("date_fin") != null
                ? LocalDate.parse(resultSet.getString("date_fin")) : null));
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return result;
  }

  /**
   * M�thode pour mettre � jour un param�tre
   * 
   * @param parametre
   *          le param�tre avec les nouvelle valeurs.
   * @return si la mise � jour a fonctionn�e.
   */
  public static boolean update(Parametre parametre) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "UPDATE Parametres SET valeur = ?, type_id = ?, "
          + "date_debut = ?, date_fin = ? WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setFloat(1, parametre.getValeur());
      statement.setInt(2, parametre.getTypeId());
      statement.setObject(3, parametre.getDateDebut());
      statement.setObject(4, parametre.getDateFin());
      statement.setInt(5, parametre.getId());
      statement.execute();

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * M�thode pour supprimer un param�tre
   * 
   * @param parametreId
   *          le id du param�tre � supprimer.
   * @return si la suppression � fonctionn�e.
   */
  public static boolean delete(int parametreId) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "DELETE FROM Parametres WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, parametreId);
      statement.execute();

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

}