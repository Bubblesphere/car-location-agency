package dao;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
          + "FROM Parametres P LEFT JOIN TypesParametre T ON T.id = P.type_id "
          + "GROUP BY P.type_id "
          + "ORDER BY P.type_id ASC, P.id DESC";
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

  public static Parametre updateValue(int oldId, Parametre parametre) {
    try (Connection connection = DataAccess.getConnection()) {
      LocalDate now = LocalDate.now();

      Parametre oldParam = parametre;
      archiver(oldId);

      parametre.setDateDebut(now);
      parametre.setDateFin(null);

      create(parametre);

      return parametre;

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Méthode pour mettre à jour un paramètre
   *
   * @param pid ID du paramètre à archiver
   * @return true si l'archivage
   */
  public static Boolean archiver(int pid) {
    try (Connection connection = DataAccess.getConnection()) {
      final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


      String query = "UPDATE Parametres SET date_fin = ? WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, LocalDate.now().format(formatter));
      statement.setInt(2, pid);

      statement.executeUpdate();

      return true;

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * M?thode pour mettre ? jour un param?tre
   * 
   * @param parametre
   *          le paramètre avec les nouvelle valeurs.
   * @return si la mise à jour a fonctionnée.
   */
  public static Parametre update(Parametre parametre) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "UPDATE Parametres SET date_fin = ? WHERE id = ?";
      PreparedStatement statement = connection.prepareStatement(query);      
      statement.setObject(1, parametre.getDateFin());
      statement.setInt(2, parametre.getId());
      statement.execute();
      
      return parametre;

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
