package dao;

import data.Classe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClasseDao {

  /**
   * Méthode pour créé une classe
   * 
   * @param classe
   *          classe à créé.
   * @return la classe qui a été créé avec son id de mis à jour.
   */
  public static Classe create(Classe classe) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "INSERT INTO Classes (nom, prix_journalier) values (?, ?)";
      PreparedStatement statement = connection.prepareStatement(query,
          Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, classe.getNom());
      statement.setFloat(2, classe.getPrixJournalier());
      statement.execute();

      ResultSet keys = statement.getGeneratedKeys();
      keys.next();
      classe.setId(keys.getInt(1));

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return classe;
  }

  /**
   * Méthode pour récupérer une classe
   * 
   * @param classeId
   *          id de la classe à récupérer.
   * @return la classe récupérer.
   */
  public static Classe retrieve(int classeId) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "SELECT id, nom, prix_journalier FROM Classes WHERE id = ?";
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

  /**
   * Méthode pour récupérer toutes les classes
   * 
   * @return liste de toutes les classes.
   */
  public static List<Classe> retrieveAll() {
    List<Classe> result = new ArrayList<Classe>();
    try (Connection connection = DataAccess.getConnection()) {

      String query = "SELECT id, nom, prix_journalier FROM Classes";
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

  /**
   * Méthode pour mettre à jour une classe
   * 
   * @param classe
   *          la classe avec les nouvelle valeurs.
   * @return si la mise à jour a fonctionnée.
   */
  public static boolean update(Classe classe) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "UPDATE Classes SET nom = ?, prix_journalier = ? WHERE id = ?";
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

  /**
   * Méthode pour supprimer une classe
   * 
   * @param classeId
   *          le id de la classe à supprimer.
   * @return si la suppression à fonctionnée.
   */
  public static boolean delete(int classeId) {
    try (Connection connection = DataAccess.getConnection()) {

      String query = "DELETE FROM Classes WHERE id = ?";
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
