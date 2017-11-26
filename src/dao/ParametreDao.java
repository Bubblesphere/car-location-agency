package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import data.Parametre;

public class ParametreDao {
  
  /**
   * Méthode pour créé un paramètre
   * 
   * @param parametre
   *          paramètre à créé.
   * @return le paramètre qui a été créé avec son id de mis à jour.
   */
  private static Parametre create(Parametre parametre) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "INSERT INTO Parametres (valeur, type_id, date_debut) values (?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(query,
          Statement.RETURN_GENERATED_KEYS);
      statement.setFloat(1, parametre.getValeur());
      statement.setInt(2, parametre.getTypeId());
      statement.setObject(3, parametre.getDateDebut());
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
   * Méthode pour récupérer une paramètre
   * 
   * @param parametreId
   *          id du paramètre à récupérer.
   * @return le paramètre récupérer.
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
   * Méthode pour récupérer touts les paramètres
   * 
   * @return liste de touts les paramètres.
   */
  public static List<Parametre> retrieveAll() {
    return retrieveAll(false);
  }
  
  public static List<Parametre> retrieveAll(Boolean active) {
    List<Parametre> result = new ArrayList<Parametre>();
    try (Connection connection = DataAccess.getConnection()) {

      String query = "SELECT P.id, P.valeur, P.type_id, P.date_debut, P.date_fin, T.description "
          + "FROM Parametres P LEFT JOIN TypesParametre T ON T.id = P.type_id";
      if(active){
        query += " WHERE P.date_fin IS NULL";
      }
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
   * Méthode pour mettre à jour un paramètre
   * 
   * @param parametre
   *          le paramètre avec les nouvelle valeurs.
   * @return si la mise à jour a fonctionnée.
   */
  public static Parametre update(Parametre parametre) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "UPDATE Parametres SET date_fin = ? WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);      
      statement.setObject(4, parametre.getDateFin());
      statement.setInt(5, parametre.getId());
      statement.execute();
      
      return create(new Parametre(parametre.getTypeId(), parametre.getValeur()));

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }    
  }

  /**
   * Méthode pour supprimer un paramètre
   * 
   * @param parametreId
   *          le id du paramètre à supprimer.
   * @return si la suppression à fonctionnée.
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
